package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 手順情報
 */
@Entity
@Table(name = "PROCESSES")
public class Process {

	@Id
    @SequenceGenerator(name = "PROCESSES_ID_GENERATOR", sequenceName = "PROCESSES_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCESSES_ID_GENERATOR")
	@Column(name = "ID")
    private Long id;

	@Column(name = "IMAGE")
    private String image;

	@Column(name = "PROCESS")
    private String process;

	@ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    private Recipe recipe;

	@Column(name = "RECIPESORT_ID")
    private Long recipesort_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return  "/process-images/" + id + "/" + image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Long getRecipesort_id() {
		return recipesort_id;
	}

	public void setRecipesort_id(Long recipesort_id) {
		this.recipesort_id = recipesort_id;
	}




}
