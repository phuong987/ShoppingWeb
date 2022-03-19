/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.mycompany.pojos.Cart;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class Utils {
    public static int countCart(Map<Integer, Cart> cart){
        int count = 0;
        if (cart != null){
            for (Cart c: cart.values())
                count += c.getCount();
        }
        return count;
    }
}
