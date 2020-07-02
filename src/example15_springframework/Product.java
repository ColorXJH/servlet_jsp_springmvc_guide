package example15_springframework;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private double price;
	private Integer weight;
	public Product() {
		super();
	}
	public Product(String name, double price, Integer weight) {
		this.name = name;
		this.price = price;
		this.weight = weight;
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
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", weight=" + weight + "]";
	}
	public static Product createInstance() {
		return new Product("BMW-FACTORY-CREATE",400000.22,2000);
	}
}
