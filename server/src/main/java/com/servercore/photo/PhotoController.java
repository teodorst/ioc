package com.servercore.photo;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.servercore.event.Event;
import com.servercore.event.EventRepository;
import com.servercore.user.User;

@RestController
public class PhotoController {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@CrossOrigin
	@RequestMapping(value = "event/{eventId}/photo", method = RequestMethod.POST)
	public void uploadPhoto(@PathVariable("eventId") Long eventId, @RequestParam("file") MultipartFile file, Principal user) throws Exception {
		
		// validate request
		
		Event event = eventRepository.findById(eventId);
		
		if (event == null) {
			throw new Exception("Event doesn't exists");
		}
		
		if (!file.isEmpty()) {
			try {
				
				//create Photo entry in db
				Photo newPhoto = new Photo();
				newPhoto.setPhotoPath("/tmp"); // poate il scoatem
				newPhoto.setUserEmail(user.getName());
				photoRepository.save(newPhoto);
				
				// save photo
				byte[] fileContent = file.getBytes();
				String localFilePath = writePhotoToLocalMemory(eventId, fileContent, newPhoto);
				
				if (localFilePath != null) {
					// update the path
					newPhoto.setPhotoPath(localFilePath);
					newPhoto.setPhotoThumbnailPath(localFilePath + "_thumbnail");
					photoRepository.save(newPhoto);
					// add entry into event_photo table
					event.getPhotos().add(newPhoto);
					eventRepository.save(event);
				}
				else {
					photoRepository.delete(newPhoto);
				}
					
				
			} catch (IOException e ) {
				throw new Exception("Failed to upload file");
			}
		}
		else {
			throw new Exception("File is empty!");
		}
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "event/{eventId}/photo/{photoId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadPhoto(@PathVariable("eventId") Long eventId, @PathVariable("photoId") Long photoId, HttpServletResponse response) throws IOException {
		
		downloadFile(photoId, response, false);
		
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "event/{eventId}/thumbnail/{photoId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadThumbnail(@PathVariable("eventId") Long eventId, @PathVariable("photoId") Long photoId, HttpServletResponse response) throws IOException {
		
		downloadFile(photoId, response, true);
		
	}
	
	private File checkEventFolderOrCreateIt(Long eventId) {
		File dir = new File("events_photos/" + eventId);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
	
	private String writePhotoToLocalMemory(Long eventId, byte[] fileContent, Photo newPhoto) {
		File eventDirectory = checkEventFolderOrCreateIt(eventId);
		String path = eventDirectory.getAbsolutePath() + File.separator + "IMG_" + newPhoto.getId();
		String thumbnailPath = eventDirectory.getAbsolutePath() + File.separator + "IMG_" + newPhoto.getId() + "_thumbnail";
		
		try {
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileContent));
			BufferedImage thumbnailImage = getThumbdanilOfPhoto(bufferedImage);
			ImageIO.write(bufferedImage, "jpg", new File(path));
			ImageIO.write(thumbnailImage, "jpg", new File(thumbnailPath));
		} catch (IOException e) {
			System.out.println("Could not write local file");
			path = null;
		}
		
		return path;
	}
	
	
	private BufferedImage getThumbdanilOfPhoto(BufferedImage originalImage) {
		int thumbnailWidth = 150;
		int w = originalImage.getWidth();
		int h = originalImage.getHeight();
		int heightToScale, widthToScale;
		
		if (w > h) {
			heightToScale = (int)(1.1 * thumbnailWidth);
			widthToScale = (int)((heightToScale * 1.0) / h * w);
		}
		else {
			widthToScale = (int)(1.1 * thumbnailWidth);
		    heightToScale = (int)((widthToScale * 1.0) / w * h);
		}

		BufferedImage resizedImage = new BufferedImage(widthToScale, heightToScale, originalImage.getType());
		
		Graphics2D g = resizedImage.createGraphics();
		 
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.drawImage(originalImage, 0, 0, widthToScale, heightToScale, null);
		g.dispose();

		int x = (resizedImage.getWidth() - thumbnailWidth) / 2;
		int y = (resizedImage.getHeight() - thumbnailWidth) / 2;
		 
		if (x < 0 || y < 0) {
		    throw new IllegalArgumentException("Width of new thumbnail is bigger than original image");
		}

		
		BufferedImage thumbnailBufferedImage = resizedImage.getSubimage(x, y, thumbnailWidth, thumbnailWidth);
		
		return thumbnailBufferedImage;
	
	}
	
	
	private void downloadFile(Long photoId, HttpServletResponse response, boolean thumbnail) throws IOException {
		Photo photo = photoRepository.findById(photoId);
		File photoFile;
		
		if (thumbnail) {
			photoFile = new File(photo.getPhotoThumbnailPath());
		}
		else {
			photoFile = new File(photo.getPhotoPath());
		}
		String mimeType = URLConnection.guessContentTypeFromName(photoFile.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + photoFile.getName() +"\""));
        response.setContentLength((int)photoFile.length());
        
        InputStream inputStream = new BufferedInputStream(new FileInputStream(photoFile));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
		
	}
	
}
