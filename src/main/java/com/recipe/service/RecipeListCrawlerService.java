package com.recipe.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.entity.RecipeEntity;
import com.recipe.entity.RecipeListEntity;
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

            System.out.println("=================================");
            System.out.println("Detail URL: " + detailUrl);
            System.out.println("=================================");

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

        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Image URL: " + imageUrl);

        // Save the data to the database
        RecipeListEntity recipeListEntity = new RecipeListEntity();
        recipeListEntity.setTitle(title);
        recipeListEntity.setDescription(description);
        recipeListEntity.setImageUrl(imageUrl);

        recipeListRepository.save(recipeListEntity);
    }
}
