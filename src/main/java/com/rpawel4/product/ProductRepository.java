package com.rpawel4.product;

import java.util.Optional;

public interface ProductRepository {

	public Optional<Product> findByBarCode(String barcode);
}
