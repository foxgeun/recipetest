package com.recipe.controller;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeIngre;
import com.recipe.entity.RecipeOrder;
import com.recipe.repository.RecipeIngreRepository;
import com.recipe.repository.RecipeOrderRepository;
import com.recipe.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {
	
	@Autowired
	private final RecipeRepository recipeRepository;
	private final RecipeOrderRepository recipeOrderRepository;
	private final RecipeIngreRepository recipeIngreRepository;
	
	@GetMapping(value = "/recipe/{Id}")
	public String recipe(Model model, @PathVariable("Id") Long Id) {
		
		Recipe recipeDetail = recipeRepository.getRecipeDetailByid(Id);
		

		
		recipeRepository.setaddview(Id);
		List<RecipeOrder> recipeOrderList = recipeOrderRepository.getRecipeOrderByid(Id);
		List<RecipeIngre> recipeIngreList = recipeIngreRepository.getRecipeIngreByid(Id);
		
		
		model.addAttribute("recipeIngreList" ,recipeIngreList);
		model.addAttribute("recipeOrder", recipeOrderList);
		model.addAttribute("recipeDetail",recipeDetail);
		
		return "recipes/recipe";
	}
	
	@GetMapping("/crawl")
	public String crawlAndDisplayRecipes() {

	    // Edge 드라이버 경로 설정
	    System.setProperty("webdriver.edge.driver", "C:\\Users\\EZEN-36\\Downloads\\edgedriver_win64\\msedgedriver.exe");
	    WebDriver driver = new EdgeDriver();
	    
	    
	    // 웹 페이지 열기
	    driver.get("https://wtable.co.kr/recipes");
	    
	    for(int i = 2; i<5; i++) {
	    
	    // "한식" 클릭 (XPath를 사용하여 클릭)
	    String xpath = "//*[@id=\"__next\"]/div/main/div/div/div/section[3]/ul/li[" +i +"]";
	    WebElement koreanCuisineLink = driver.findElement(By.xpath(xpath));
	    koreanCuisineLink.click();
	    
	    
	    try {
	        Thread.sleep(1000); // 1초 동안 대기
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    // 동적으로 로드된 콘텐츠 추출

	    List<WebElement> erZvWPElements = driver.findElements(By.cssSelector(".erZvWP"));
	    
	    
	    for (WebElement erZvWPElement : erZvWPElements) {
	        WebElement duQJWIImage = erZvWPElement.findElement(By.cssSelector(".duQJWI"));
	        String srcValue = duQJWIImage.getAttribute("src");
	        WebElement LxJcTElements = erZvWPElement.findElement(By.cssSelector(".LxJcT"));
	        String subTitleValue = LxJcTElements.getText();
	        WebElement hpYiJKElements = erZvWPElement.findElement(By.cssSelector(".hpYiJK"));
	        String TitleValue = hpYiJKElements.getText();
	        
	    
		    
	        Recipe recipeObject = new Recipe();
		
	        
	
	        recipeObject.setImageUrl(srcValue);
	        recipeObject.setSubTitle(subTitleValue);
	        recipeObject.setTitle(TitleValue);
	        recipeObject.setCategory(koreanCuisineLink.getText());
	        
	        
	        
	        recipeRepository.save(recipeObject);
	        }


	    }
	    
	    
	    
	    // 웹 드라이버 종료
//	    driver.quit();

	    return "index";
	}

}
