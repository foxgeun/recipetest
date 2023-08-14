package com.recipe.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeList;
import com.recipe.repository.RecipeListRepository;
import com.recipe.repository.RecipeRepository;

import java.io.IOException;

@Service
public class RecipeListCrawlerService {
    @Autowired

    private RecipeListRepository recipeListRepository;

    public void crawlAndSaveRecipes() throws IOException {
        String url = "https://wtable.co.kr/recipes";
        Document doc = Jsoup.connect(url).get();
        Elements recipeElements = doc.select("a:has(.erZvWP)");
        
        for (Element recipeLink : recipeElements) {
            String detailUrl = recipeLink.attr("href");



            // Now, you can crawl and save the detail page
            crawlAndSaveDetailPage(detailUrl);
        }
    }

    private void crawlAndSaveDetailPage(String detailUrl) throws IOException {
        // Check if the URL is relative and construct the full URL
        if (!detailUrl.startsWith("http://") && !detailUrl.startsWith("https://")) {
            detailUrl = "https://wtable.co.kr" + detailUrl;
        }

        Document detailDoc = Jsoup.connect(detailUrl).get();

        // Extract data from the detail page using CSS selectors
        String description = detailDoc.select(".IdQIJ").text();
        String title = detailDoc.select(".kIVrZW").text();
        String imageUrl = detailDoc.select("img").attr("src");


        // Save the data to the database
        RecipeList recipeListEntity = new RecipeList();
        recipeListEntity.setTitle(title);
        recipeListEntity.setDescription(description);
        recipeListEntity.setImageUrl(imageUrl);

        recipeListRepository.save(recipeListEntity);
    }
}
