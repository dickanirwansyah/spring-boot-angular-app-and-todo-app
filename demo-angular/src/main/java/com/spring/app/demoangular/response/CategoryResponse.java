package com.spring.app.demoangular.response;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {

	private Long categoryId;
	private String categoryName;
	private boolean categoryStatus;
	private Date date;
	private String status;
	private int code;
}
