package com.br.fsc.valueObject_v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id", "author", "title", "launch_date", "prince"})
public class BookVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	
	private String author;
	
	private Date launch_date;
	
	private Double prince;
	
	private String title;

	public BookVO() {
	}

	public BookVO(Long key, String author, Date launch_date, Double prince, String title) {
		super();
		this.key = key;
		this.author = author;
		this.launch_date = launch_date;
		this.prince = prince;
		this.title = title;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLaunch_date() {
		return launch_date;
	}

	public void setLaunch_date(Date launch_date) {
		this.launch_date = launch_date;
	}

	public Double getPrince() {
		return prince;
	}

	public void setPrince(Double prince) {
		this.prince = prince;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, key, launch_date, prince, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		return Objects.equals(author, other.author) && Objects.equals(key, other.key)
				&& Objects.equals(launch_date, other.launch_date) && Objects.equals(prince, other.prince)
				&& Objects.equals(title, other.title);
	}
	
	
	
	


	
	
}
