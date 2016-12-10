package com.servercore.photo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotoController {
	
	@RequestMapping(value = "event/{eventId}/photo", method = RequestMethod.POST)
	public void uploadPhoto(@PathVariable("eventId") String eventId, @RequestParam("file") MultipartFile file) throws Exception {
		
		if (!file.isEmpty()) {
			try {
				byte[] fileContent = file.getBytes();
				File eventDirectory = checkEventFolderOrCreateIt(eventId);
				File serverFile = new File(eventDirectory.getAbsolutePath() + File.separator + "IMG_" + 100);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(fileContent);
				stream.close();
			} catch (IOException e ) {
				throw new Exception("Failed to upload file");
			}
		}
		else {
			throw new Exception("File is empty!");
		}
	}
	
	private File checkEventFolderOrCreateIt(String eventId) {
		File dir = new File("events_photos/" + eventId);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
	
}
