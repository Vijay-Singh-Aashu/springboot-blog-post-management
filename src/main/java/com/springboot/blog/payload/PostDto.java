package com.springboot.blog.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PostDto {
	private long id;
	private String title;
	private String content;
	private String author;
	private LocalDate creationDate;
}
