package com.bookstore.frontoffice.component;

// SPring
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookRecord  {
	private static final Logger logger = LoggerFactory.getLogger(BookRecord.class);
	private Integer id;
    private String  description;
    private String  price;
    private Integer quantity;
    
    public BookRecord() { 
	}
    

	public BookRecord(String description, String price, Integer quantity) {
		this.description = description;		
		this.price = price;
		this.quantity = quantity;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String toString() {
    	return (this.getId()+ " "  + this.getDescription() + " " + this.getPrice() + " " + this.getQuantity());
    }
    
}
