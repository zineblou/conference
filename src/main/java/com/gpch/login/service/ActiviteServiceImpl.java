package com.gpch.login.service;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gpch.login.model.UserFiles;
import com.gpch.login.repository.*;
@Service
@Transactional
public class ActiviteServiceImpl implements ActiviteService{

	@Autowired
	private ActiviteRepository activiteRepository;
	@Autowired private UploadPathService uploadPathService;
	@Autowired private UserFilesRepository userFilesRepository;

	public Activite save(Activite activite) {
	
		Activite dbActivite = activiteRepository.save(activite);
		if(dbActivite!=null && dbActivite.getFiles()!=null && dbActivite.getFiles().size()>0)
		{
			for(MultipartFile file : dbActivite.getFiles())
			{
				String fileName = file.getOriginalFilename();
				System.out.println("File name: "+fileName);
				String modifiedName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
				System.out.println("Modified file name: "+modifiedName);
				File fileStore = uploadPathService.getFilePath(modifiedName,"images");
				if(fileStore!=null)
				{
					try {
						FileUtils.writeByteArrayToFile(fileStore, file.getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				UserFiles fich = new UserFiles();
				fich.setFileName(fileName);
				fich.setFileExtension(FilenameUtils.getExtension(fileName));
				fich.setModifiedFileName(modifiedName);
				fich.setActivite(dbActivite);
				userFilesRepository.save(fich);
				
			}
			
		}
		
		
		
		return activiteRepository.save(dbActivite);

		
	}


	@Override
	public List<UserFiles> ReservationFile(Long id) {
		// TODO Auto-generated method stub
		return (List<UserFiles>) userFilesRepository.findAll();
	}


	@Override
	public Activite find(Long id) {
		// TODO Auto-generated method stub
		//
		return null;
	}


	@Override
	public List<Map<String, Object>> report() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for (Activite activite : this.findAll())
		{
			Map<String,Object> item = new HashMap<String, Object>();
			item.put("id", activite.getId());
			item.put("titre", activite.getTitre());
			item.put("theme", activite.getTheme());
			result.add(item);
		}
		return null;
	}


	@Override
	public Iterable<Activite> findAll() {
		// TODO Auto-generated method stub
		return activiteRepository.findAll();
	}
	


}
