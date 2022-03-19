/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.mycompany.pojos.Cart;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Dell
 */
@Controller
public class CartController {
    @GetMapping("/cart")
    public String cart(Model model, HttpSession session){
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart != null)
            model.addAttribute("carts", cart.values());
        else
            model.addAttribute("carts", null);
        return "cart";
    }
}
