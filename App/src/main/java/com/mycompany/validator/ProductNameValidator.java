/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.validator;

import com.mycompany.pojos.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Dell
 */
@Component
public class ProductNameValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product p = (Product) target;
//        if (!p.getName().contains("NTP"))
//            errors.rejectValue("name", "product.name.nameErr");
    }
    
}
