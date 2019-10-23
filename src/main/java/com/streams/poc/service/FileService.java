package com.streams.poc.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.streams.poc.fileController.FileLookup;
import com.google.gson.Gson;
import com.streams.poc.dataModal.DataModal;

@Service
public class FileService {
	
	@Autowired
	FileLookup fileLookup;
	
	HashMap<String,Path> fileMap;
	
	HashMap<String, List<DataModal>> dataModal;
	
	//Set up fileMap and dataModal
	public void serviceSetup() {
		setFileMap();
		setDataModal();
	}
	
	private void setFileMap() {
		FileLookup fileLookup = new FileLookup();
		this.fileMap = fileLookup.getFileMap();
	}
	
	private void setDataModal() {
		FileLookup fileLookup = new FileLookup();
		this.dataModal = new HashMap<>();
		String[] filePath = this.getAllFileArray();
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
	
	public String getAllFile() {
		String result = new String();
		for(Map.Entry<String, Path> entry: fileLookup.getFileMap().entrySet()) {
			result += entry.getKey() + "<br>";
		}
		return result;
	}
	
	public String[] getAllFileArray() {
		FileLookup fileLookup = new FileLookup();
		HashMap<String,Path> fileMap = fileLookup.getFileMap();
		String[] array = new String[fileMap.size()];
		int counter = 0;
		for(Map.Entry<String, Path> entry: fileMap.entrySet()) {
			array[counter++] = entry.getKey();
		}
		return array;
	}
	
	
	public String getFileData(String file) {
		file += ".csv";
		String result = new String();
		try(Stream<String> lines = Files.lines(fileLookup.getFileMap().get(file))){
			String[] line = lines.skip(1).toArray(String[]::new);
			for(String str:line) {
				result += str + "<br>";
			}
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return result;
	}
	
	public String getAllData(){
		String result = new String();
		for(Map.Entry<String,List<DataModal>> entry: this.dataModal.entrySet()) {
			result += entry.getKey() + "<br>";
			Optional<String> optional = entry.getValue().stream().map(l -> l.toString() + "<br>").reduce((str1,str2) -> str1 + str2);
			if(optional.isPresent()) {
				result += optional.get();
			}
			result += "<br>";
		}
		return result;
		
	}
	
	public String dataToJson() {
		Gson gson = new Gson();
		serviceSetup();
		String json = gson.toJson(this.dataModal);
		return json;
	}
	
//	public String getPersonalDetails(String file, String pathId) {
//		serviceSetup();
//		file += ".csv";
//		String result = new String();
//		List<DataModal> lines = this.dataModal.get(file);
//		lines.stream().
//		return result;
//	}
	
	
}
