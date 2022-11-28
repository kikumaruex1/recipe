package com.example.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * レシピ情報
 */
@Entity
@Table(name = "RECIPES")
public class Recipe {

	@Id
    @SequenceGenerator(name = "RECIPES_ID_GENERATOR", sequenceName = "RECIPES_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECIPES_ID_GENERATOR")
    @Column(name = "ID")
	private Long id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NAME")
	private String name;

	@Column(name = "IMAGE")
    private String image;

	@Column(name = "MOVIE")
    private String movie;

	@Column(name = "TIME")
    private double time;

	@Column(name = "PRICE")
    private int price;

	@ManyToOne
    @JoinColumn(name = "SUBCATEGORY_ID")
    private Subcategory subcategory;

	@ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

	@ManyToOne
    @JoinColumn(name = "ALCOHOL_ID")
    private Alcohol alcohol;

//	@ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "RECIPES_SUBCATEGORIES",
//            joinColumns = @JoinColumn(name = "RECIPE_ID"),
//            inverseJoinColumns = @JoinColumn(name = "SUBCATEGORY_ID")
//            )
//    private Set<Subcategory> subcategories = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return "/recipe-images/" + id + "/" + image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Alcohol getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(Alcohol alcohol) {
		this.alcohol = alcohol;
	}

//	public Set<Subcategory> getSubcategories() {
//        return subcategories;
//    }
//
//    public void setSubcategories(Set<Subcategory> subcategories) {
//        this.subcategories = subcategories;
//    }
//
//    public void addSubcategory(Subcategory subcategory) {
//        this.subcategories.add(subcategory);
//    }
//
//    public boolean hasSubcategoriy(String subcategoryName) {
//        for (Subcategory subcategory : subcategories) {
//            if (subcategory.getName().equals(subcategoryName)) {
//                return true;
//            }
//        }
//        return false;
//    }


}
