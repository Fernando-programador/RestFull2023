package com.br.fsc.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	private Long id;
	
	private String author;
	
	private Date lauch_date;
	
	private Double prince;
	
	private String title;
}
