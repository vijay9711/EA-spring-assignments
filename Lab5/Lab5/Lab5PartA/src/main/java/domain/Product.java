package domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name="product_type",
		discriminatorType=DiscriminatorType.STRING
)
public class Product {

	@Id
	@GeneratedValue
	private long id;
    private String name;
    private String description;
    private double price;
    
    public Product(){}

    public Product(String name, String description, double price){
    	this.name=name;
    	this.description=description;
    	this.price=price;
    }

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
