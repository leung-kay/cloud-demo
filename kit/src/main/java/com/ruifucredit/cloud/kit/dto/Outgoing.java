package com.ruifucredit.cloud.kit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
@AllArgsConstructor()
public class Outgoing<T> {
	
	private Integer code;
	private T result;
	private String message;
	
	public Outgoing(T result) {
		this(StatusCode.OK.code, result, StatusCode.OK.message);
	}

	public Outgoing(StatusCode statusCode) {
        this(statusCode.code, null, statusCode.message);
    }

    public Outgoing(StatusCode statusCode, T result) {
        this(statusCode.code, result, statusCode.message);
    }

	public Outgoing() {
		this(StatusCode.OK.code, null, StatusCode.OK.message);
	}

	public boolean isSuccess() {
		return new Integer(StatusCode.OK.code).equals(code);
	}
	
}
