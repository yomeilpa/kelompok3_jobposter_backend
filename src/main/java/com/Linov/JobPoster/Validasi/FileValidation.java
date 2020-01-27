package com.Linov.JobPoster.Validasi;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileValidation {
	
	public void fileSizeCheck(MultipartFile[] file) throws Exception{
		if(file[0].getSize() > 1048576) {
			throw new Exception("File Maximum Size is 1MB");
		}
		
	}

}
