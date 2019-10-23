package com.streams.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.streams.poc.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fileExplorer")
public class FileController {

	@Autowired
	FileService service;

	@GetMapping("/fileNames")
	public String getAllFile() {
		return service.getAllFile();
	}
	
	@GetMapping("/fileData/{id}")
	public String getFileData(@PathVariable(value="id") String id) throws Exception{
		return service.getFileData(id);
	}
	
	@GetMapping("/filescsv")
	public String getAllFileData() {
		service.serviceSetup();
		return service.getAllData();
	}
	
	@GetMapping("/filesjson")
	public String getJson() {
		return service.dataToJson();
	}
	
//	@GetMapping("fileData/{id}/post/{postId}")
//	public String getPersonDetails(@PathVariable(value = "id") String id,@PathVariable(value = "postId") String postId) {
//		return service.getPersonalDetails(id,pathId);
//	}
	

	
}
