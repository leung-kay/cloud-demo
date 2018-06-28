package com.ruifucredit.cloud.kit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@AllArgsConstructor()
public class Outcoming<T> {
	
	public static final int OK_CODE = 2000;
	public static final int ERROR_CODE = 4000;
	public static final int FAIL_CODE = 5000;
	
	private Integer code;
	private T result;
	private String message;
	
	public Outcoming(Integer code, T result) {
		this(code, result, null);
	}
	
	public Outcoming(T result) {
		this(OK_CODE, result, null);
	}
	
	public Outcoming() {
		this(OK_CODE, null, null);
	}

	public boolean isSuccess() {
		return new Integer(OK_CODE).equals(code);
	}
	
}
