package it.pittysoft.affetti.model;

import lombok.Data;

@Data
public class Response {
	public static final int OK = 0;

	public static final int WARN = 1;

	public static final int KO = 2;

	private int returnCode;

	private String reasonCode;
	
	public Response() {
		super();
		this.returnCode=0;
		this.reasonCode="";
	}

}
