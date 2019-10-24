package com.streams.poc.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.streams.poc.fileController.FileLookup;
import com.google.gson.Gson;
import com.streams.poc.dataModal.DataModal;
import com.streams.poc.dataModal.DataObject;
import com.streams.poc.dataModal.NotFoundModal;

@Service
public class FileService {
	
	@Autowired
	FileLookup fileLookup;
	
	HashMap<String,Path> fileMap;
	
	HashMap<String, List<DataModal>> dataModal;

	public void serviceSetup() {
		setFileMap();
		setDataModal();
	}
	
	//fileMap initializing
	private void setFileMap() {
		FileLookup fileLookup = new FileLookup();
		this.fileMap = fileLookup.getFileMap();
	}
	
	//dataModal initializing
	private void setDataModal() {
		FileLookup fileLookup = new FileLookup();
		this.dataModal = new HashMap<>();
		List<String> filePath = this.getAllFile();
		for(String path:filePath) {
			dataModal.put(path,new ArrayList<DataModal>());
		}
		for(String key: dataModal.keySet()) {
			try(Stream<String> lines = Files.lines(fileLookup.getFileMap().get(key))){
				List<DataModal> obj = lines.skip(1).map(l -> new DataModal(l)).collect(Collectors.toList());
				dataModal.put(key, obj);
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
	
	public ArrayList<String> getAllFile() {
		ArrayList<String> fileList = new ArrayList<>();
		for(Map.Entry<String, Path> entry: fileLookup.getFileMap().entrySet()) {
			fileList.add(entry.getKey());
		}
		return fileList;
	}
	
	public ArrayList<String> getFileData(String file) {
		file += ".csv";
		ArrayList<String> result = new ArrayList<String>();
		try(Stream<String> lines = Files.lines(fileLookup.getFileMap().get(file))){
			String[] line = lines.skip(1).toArray(String[]::new);
			for(String str:line) {
				result.add(str);
			}
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return result;
	}
	
	public HashMap<String,List<DataModal>> getAllData(){
		serviceSetup();
		return this.dataModal;
		
	}
	
	public DataObject getDataByFirstName(String file,String firstName) {
		serviceSetup();
		try {
			return  this.dataModal.get(file).stream().filter(x -> x.getFirstName().equals(firstName)).findAny().get();
		}catch(Exception e) {
			DataObject obj = new NotFoundModal();
			return obj;
		}
	}
	public DataObject getDataByLastName(String file,String firstName) {
		serviceSetup();
		try {
			return  this.dataModal.get(file).stream().filter(x -> x.getLastName().equals(firstName)).findAny().get();
		}catch(Exception e) {
			DataObject obj = new NotFoundModal();
			return obj;
		}
	}
	public DataObject getDataByPost(String file,String firstName) {
		serviceSetup();
		try {
			return  this.dataModal.get(file).stream().filter(x -> x.getPost().equals(firstName)).findAny().get();
		}catch(Exception e) {
			DataObject obj = new NotFoundModal();
			return obj;
		}
	}
	
	public void addPersonToFile(String data,String file) {
		serviceSetup();
		Gson gson = new Gson();
		file += ".csv";
		DataModal obj = gson.fromJson(data, DataModal.class);
		try { 
            BufferedWriter out = new BufferedWriter( 
                   new FileWriter(this.fileMap.get(file).toString(), true)); 
            out.write(obj.toCSV()); 
            out.close(); 
        } 
        catch (IOException e) { 
            System.out.println("exception occoured" + e); 
        } 
	}
	
}
