/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.mycompany.pojos.Cart;
import com.mycompany.pojos.Category;
import com.mycompany.pojos.User;
import com.mycompany.service.CategoryService;
import com.mycompany.service.ProductService;
import com.mycompany.utils.Utils;
import java.util.Map;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Dell
 */
@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @ModelAttribute
    public void commonAttr(Model model, HttpSession session){
        model.addAttribute("categories", this.categoryService.getCategories());
        model.addAttribute("cartCounter", Utils.countCart((Map<Integer, Cart>) 
                session.getAttribute("cart")));
        model.addAttribute("currentUser", session.getAttribute("currentUser"));
    }
    
    @GetMapping("/")
    public String index(Model model,@RequestParam(required = false) Map<String, String> params,
            HttpSession session){
        String kw = params.getOrDefault("kw", null);
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        String cateId = params.get("CateId");
        if(cateId==null){
            model.addAttribute("products", this.productService.getProducts(kw, page));   
        } else{
            Category c = this.categoryService.getCategoryById(Integer.parseInt(cateId));
            model.addAttribute("products", c.getProducts());
        }
        model.addAttribute("counter", this.productService.countProduct());
        model.addAttribute("disProducts", this.productService.getMostDiscussProducts(6));
        //model.addAttribute("currentUser", session.getAttribute("currentUser"));
        return "index";
    }
    @RequestMapping("/hello/{name}")
    public  String hello(Model model, @PathVariable(value = "name") String x){
        model.addAttribute("message","Welcome " + x );
        return "hello";
    }
    
    @RequestMapping(path="/hello-post", method=RequestMethod.POST)
    public String show(Model model, @ModelAttribute(value="user") User user){
        model.addAttribute("fullName", user.getFirstName() + " " + user.getLastName());
        return "index";
        
    }
    
    @RequestMapping(path="/cart")
    public String cart(Model model){
        return "cart";
        
    }
}
