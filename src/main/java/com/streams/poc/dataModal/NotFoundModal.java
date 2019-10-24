package com.streams.poc.dataModal;

public class NotFoundModal extends DataObject {
	
	final private String notFound = "Not Found";

	public String getNotFound() {
		return notFound;
	}
	
	@Override
	public String toString() {
		return this.notFound;
	}

}
