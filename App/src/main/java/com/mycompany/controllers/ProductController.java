/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mycompany.pojos.Product;
import com.mycompany.service.ProductService;
import com.mycompany.validator.ProductNameValidator;
import com.mycompany.validator.WebAppValidator;
import java.io.IOException;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Dell
 */
@Controller
public class ProductController {
    @Autowired
    private WebAppValidator productValidator;
    @Autowired
    private ProductService productService;
    
    @InitBinder
    public void initBinder (WebDataBinder binder){
        binder.setValidator(productValidator);
    }

    @GetMapping("/admin/products")
    public String list(Model model) {
        model.addAttribute("product", new Product());
        return "product";	
    }

    @PostMapping("/admin/products")
    public String add(Model model, @ModelAttribute(value="product") @Valid Product product,
            BindingResult result){
        if (!result.hasErrors()){
            if(this.productService.addOrUpdate(product)== true)
                return "redirect:/";
            else
                model.addAttribute("errMsg", "Something wrong!!!");
        }
           return "product";   
    }
    
    @GetMapping("/products/{productId}")
    public String detail(Model model, @PathVariable(value = "productId") int productId){
        model.addAttribute("product", this.productService.getProductById(productId));
        return "product-detail";
    }
    
}
