package com.rpawel4.product;

import java.util.Optional;
import com.rpawel4.barcode.CodeValidator;

public class ProductService {
	
	private ProductRepository productRepo;
	private CodeValidator codeValidator;
	
	
	public ProductService(ProductRepository productRepo, CodeValidator codeValidator) {
		this.productRepo = productRepo;
		this.codeValidator = codeValidator;
	}
	
	public Product getProduct(String barcode) throws InvalidCodeException, ProductNotFoundException{
		
		if(!codeValidator.isCodeValid(barcode)) {
			throw new InvalidCodeException();
		}
		
		return findProduct(barcode).orElseThrow(ProductNotFoundException::new);
	}
	
	private Optional<Product> findProduct(String barcode){
		return productRepo.findByBarCode(barcode);
	}

}
