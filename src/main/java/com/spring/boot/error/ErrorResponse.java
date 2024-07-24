package com.spring.boot.error;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {
	private final int status;
	private final String code;
	private final String message;
	private List<FieldError> errors;
	
	@Builder
	public ErrorResponse(String message, String code, int status, List<FieldError> errors) {
		this.message = message;
		this.code = code;
		this.status = status;
		this.errors = initErrors(errors);
	}
	
	 private List<FieldError> initErrors(List<FieldError> errors) {
	        return (errors == null) ? new ArrayList<>() : errors;
	    }

    @Getter
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        @Builder
        public FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }
	
}
