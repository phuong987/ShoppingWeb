/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.repository;

import com.mycompany.pojos.Category;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface CategoryRepository {
    List<Category> getCategories();
    Category getCategoryById(int cateId);
}
