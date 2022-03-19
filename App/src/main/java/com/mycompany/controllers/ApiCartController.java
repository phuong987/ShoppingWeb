/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.mycompany.utils.Utils;
import com.mycompany.pojos.Cart;
import com.mycompany.pojos.Product;
import com.mycompany.service.ProductService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */
@RestController
public class ApiCartController {
    @Autowired
    private ProductService productService;
    
    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> listProducts(){
        List<Product> products = this.productService.getProducts("", 1);
        
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @GetMapping("/api/cart/{productId}")
    public ResponseEntity<Integer> cart(@PathVariable(value = "productId") Integer productId,
            HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart==null)
            cart = new HashMap<>();
        
        if (cart.containsKey(productId) == true){
            // san pham da co trong gio
            Cart c = cart.get(productId);
            c.setCount(c.getCount()+1);
        } else {
            // san pham chua co trong gio
            Product p = this.productService.getProductById(productId);
            Cart c = new Cart();
            c.setProductId(p.getId());
            c.setName(p.getName());
            c.setPrice(p.getPrice());
            c.setCount(1);
            cart.put(productId, c);
        }
        
        session.setAttribute("cart", cart);
        return new ResponseEntity<>(Utils.countCart(cart), HttpStatus.OK);
        //System.out.println("=== CART = " + Utils.countCart(cart));
    }
    
    @PutMapping("/api/cart")
    public int updateCartItem(@RequestBody Cart params, HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart==null)
            cart = new HashMap<>();
        
        int productId = params.getProductId();
        if (cart.containsKey(productId) == true){
            // san pham da co trong gio
            if (params.getCount() <= 0)
                cart.remove(productId);
            else {
            Cart c = cart.get(productId);
            c.setCount(params.getCount());
            }
        }
        
        session.setAttribute("cart", cart);
        return Utils.countCart(cart);
    }
    
    @DeleteMapping("/api/cart/{productId}")
    public int deleteCartItem(@PathVariable(value = "productId") int productId,
            HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart != null && cart.containsKey(productId)){
            cart.remove(productId);
            session.setAttribute("cart", cart);
        }
        return Utils.countCart(cart);
    }
}
