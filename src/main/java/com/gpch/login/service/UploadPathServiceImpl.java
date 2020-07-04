package com.gpch.login.service;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class UploadPathServiceImpl implements UploadPathService{
	
	@Autowired ServletContext servletContext;
	public File getFilePath(String modifiedName, String path) {
		
		boolean exists = new File(servletContext.getRealPath("/"+path+"/")).exists();
		if(!exists)
		{
			new File(servletContext.getRealPath("/path"+path+"/")).mkdir();
		}
		String modifiedFilePath = servletContext.getRealPath("/path"+path+"/"+File.separator+modifiedName);
		File file = new File(modifiedFilePath);
		return file;
	}

}
