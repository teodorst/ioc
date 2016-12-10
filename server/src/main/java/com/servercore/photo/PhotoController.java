package com.servercore.photo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.servercore.event.Event;
import com.servercore.event.EventRepository;

@RestController
public class PhotoController {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
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
					photoRepository.save(newPhoto);
					
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
		File serverFile = new File(eventDirectory.getAbsolutePath() + File.separator + "IMG_" + newPhoto.getId());
		BufferedOutputStream stream;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(fileContent);
			stream.close();
		} catch (IOException e) {
			System.out.println("Could not write local file");
			path = null;
		}
		
		return path;
	}
}
