package com.recipe.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeOrder;
import com.recipe.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeCrawlerService {

    @Autowired
    private final RecipeRepository recipeRepository;

    public void crawlAndSaveRecipes() throws IOException {
        String url = "https://wtable.co.kr/recipes";
        Document doc = Jsoup.connect(url).get();
        Elements recipes = doc.select(".erZvWP");

        for (Element recipe : recipes) {
            String subTitle = recipe.select(".LxJcT").text();
            String title = recipe.select(".hpYiJK").text();
            String imageUrl = recipe.select(".duQJWI").attr("src");
            String words = recipe.select(".MiXMV").text();
            String level = words.split(" ")[0];
            String time = words.split(" ")[1];

            // Create a Recipe object and set basic information
            Recipe recipeObject = new Recipe();
            recipeObject.setSubTitle(subTitle);
            recipeObject.setTitle(title);
            recipeObject.setImageUrl(imageUrl);
            recipeObject.setLevel(level);
            recipeObject.setDurTime(time);

            // Save basic information
            recipeRepository.save(recipeObject);

            // Crawl and save the detail page
            crawlAndSaveDetailPage(recipeObject, recipe.select("a").attr("href"));
        }
    }

    private void crawlAndSaveDetailPage(Recipe recipeObject, String detailUrl) throws IOException {
        if (!detailUrl.startsWith("http://") && !detailUrl.startsWith("https://")) {
            detailUrl = "https://wtable.co.kr" + detailUrl;
        }

        Document detailDoc = Jsoup.connect(detailUrl).get();

        String description = detailDoc.select(".IdQIJ").text();
        String basic = detailDoc.select(".fCbbYE").text();
        // Elements recipeImgs = detailDoc.select(".ihCzrN img");
        System.out.println(description+"asdasdasdasdasd");
        // Set additional information to the existing Recipe object
        recipeObject.setDescription(description);
        // recipeObject.setBasic(basic);
        // recipeObject.setRecipeImgs(recipeImgs);

        // Save the complete Recipe object
        recipeRepository.save(recipeObject);
    }
}