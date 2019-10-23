package com.streams.poc.fileController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class FileLookup {

	HashMap<String, Path> fileMap = new HashMap<>();

	private List<Path> getAllFile() {
		String cwd = System.getProperty("user.dir");
		String folder = "\\data\\";
		List<Path> filePathList = new ArrayList<Path>();
		try (Stream<Path> paths = Files.walk(Paths.get(cwd + folder))) {
			filePathList = paths.filter(Files::isRegularFile).collect(Collectors.toList());
			return filePathList;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage() + " Error occured");
			return null;
		}
	}

	public HashMap<String, Path> getFileMap() {
		List<Path> paths = getAllFile();
		paths.forEach((path) -> this.fileMap.put(path.getFileName().toString(), path));
		return this.fileMap;
	}

}
