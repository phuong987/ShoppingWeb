/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojos.Product;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface ProductService {
    List<Product> getProducts(String kw, int page);
    List<Object[]> getMostDiscussProducts(int num);
    Product getProductById(int productId);
    long countProduct();
    boolean addOrUpdate(Product product);
}
