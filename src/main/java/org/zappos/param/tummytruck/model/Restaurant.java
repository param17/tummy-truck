package org.zappos.param.tummytruck.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="restaurantCache")
@Table(name="restaurant")
public class Restaurant implements Serializable {
    	
    @Id
    @GeneratedValue
    @Column(name="restaurant_id")
    private int id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="address")
    private String address;
    
    @Column(name="contact")
    private String contact;
    
    @Column(name="rating")
    private String rating;
    
    @OneToMany(mappedBy="restaurant")
    private List<Menu> menus;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
    
	public Restaurant() {
		
	}
	
	public Restaurant(int id, String name, String address, String contact, String rating) {
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.rating = rating;
	}
	
}
