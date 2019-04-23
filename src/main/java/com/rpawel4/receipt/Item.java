package com.rpawel4.receipt;

import java.math.BigDecimal;

import com.rpawel4.product.Product;

public class Item {

	private Product product;
	private BigDecimal quantity;
	
	public Item() {}

	public Item(Product product, BigDecimal quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Receipt [product=" + product + ", quantity=" + quantity + "]";
	};
	
}
