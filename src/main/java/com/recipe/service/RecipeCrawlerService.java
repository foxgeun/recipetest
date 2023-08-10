package com.recipe.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.entity.RecipeEntity;
import com.recipe.repository.RecipeRepository;

import java.io.IOException;

@Service
public class RecipeCrawlerService {
    @Autowired
    private RecipeRepository recipeRepository;

    public void crawlAndSaveRecipes() throws IOException {
    	System.out.println("222222222222222222222222222");
        String url = "https://wtable.co.kr/recipes";
        Document doc = Jsoup.connect(url).get();
        Elements recipes = doc.select(".RecipeItemstyle__SubTitle-sc-1gt7vz8-2"); // Select elements with class "card-recipe"

        for (Element recipe : recipes) {
        	String title = recipe.select(".RecipeItemstyle__SubTitle-sc-1gt7vz8-2").html();
        	System.out.println("=================================");
        	System.out.println(title);
        	System.out.println("=================================");
            RecipeEntity recipeEntity = new RecipeEntity();
            recipeEntity.setTitle(title);
          
            recipeRepository.save(recipeEntity);
        }
    }
}
