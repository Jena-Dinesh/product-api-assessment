package com.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMaster {

	private String message;
	private String error;
	private int code;
}
