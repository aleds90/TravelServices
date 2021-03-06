package com.ds.travel.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Tag generated by hbm2java
 */
public class Tag implements java.io.Serializable {

	private Integer id;
	private String name;
	@JsonIgnore private Set travelTags = new HashSet(0);

	public Tag() {
	}

	public Tag(String name, Set travelTags) {
		this.name = name;
		this.travelTags = travelTags;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getTravelTags() {
		return this.travelTags;
	}

	public void setTravelTags(Set travelTags) {
		this.travelTags = travelTags;
	}

}
