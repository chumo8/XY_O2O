package com.xyo2o.dto;

public class ResultDTO {
	private String message;
	private int status;//0--失败  1--成功
	private static ResultDTO resultDTO = new ResultDTO();
	private  ResultDTO(){
	}
	
	public static ResultDTO newInStrance(String message,int status){
		resultDTO.message = message;
		resultDTO.status = status;
		return resultDTO;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResultDTO [message=" + message + ", status=" + status + "]";
	}
}
