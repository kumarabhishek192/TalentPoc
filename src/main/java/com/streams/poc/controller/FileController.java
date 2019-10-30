package com.streams.poc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streams.poc.dataModal.DataModal;
import com.streams.poc.dataModal.DataObject;
import com.streams.poc.service.FileService;

@RestController
@RequestMapping("/fileData")
public class FileController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FileService service;

	@GetMapping("/fileNames")
	public ArrayList<String> getAllFile() {
		LOGGER.info("File Names are listed");
		return service.getAllFile();
	}
	
	@GetMapping("/fileData/{id}")
	public ArrayList<String> getFileData(@PathVariable(value="id") String id) throws Exception{
		LOGGER.info("File data with perticular id is listed");
		return service.getFileData(id);
	}
	
	@GetMapping("/filescsv")
	public HashMap<String,List<DataModal>> getAllFileData() {
		LOGGER.info("getting all file values");
		return service.getAllData();
	}	
	
	@GetMapping("/file/{fileName}/person/firstname/{firstName}")
	public DataObject getByFirstName(@PathVariable(value= "fileName") String fileName,@PathVariable("firstName")String firstName) {
		LOGGER.info("Getting user with file and firstname");
		fileName += ".csv";
		return service.getDataByFirstName(fileName, firstName);
	}
	
	@GetMapping("/file/{fileName}/person/lastname/{lastName}")
	public DataObject getByLastName(@PathVariable(value= "fileName") String fileName,@PathVariable("lastName")String lastName) {
		LOGGER.info("Getting user with file and lastname");
		fileName += ".csv";
		return service.getDataByLastName(fileName, lastName);
	}
	
	@GetMapping("/file/{fileName}/person/post/{post}")
	public DataObject getByPost(@PathVariable(value= "fileName") String fileName,@PathVariable("post")String post) {
		LOGGER.info("Getting user with file and post");
		fileName += ".csv";
		return service.getDataByPost(fileName, post);
	}
	
	@PostMapping("/file/add")
	public String addPersonToFile(@RequestBody String request,@RequestHeader ("fileType") String file) {
		LOGGER.info("Adding user to the file");
		service.addPersonToFile(request,file);
		return null;
	}
	
}
