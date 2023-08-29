package com.recipe.controller;

import java.util.List;
import org.openqa.selenium.NoSuchElementException;


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


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.recipe.dto.RecipeNewDto;

import jakarta.validation.Valid;
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
	@GetMapping(value = "/recipe")
	public String recipe( Model model ) {
		
		RecipeNewDto recipeNewDto = new RecipeNewDto();

		
		model.addAttribute("recipeNewDto" , recipeNewDto);
		
		return "new";
	}

	
	@PostMapping(value = "/recipe/new")
	public String recipeNew(@Valid RecipeNewDto recipeNewDto ,  
			BindingResult bindingResult , Model model, 
			@RequestParam("RecipeIngreList") List<Object> RecipeIngreList ,
			@RequestParam("recipeOrderDtoList") List<Object> recipeOrderDtoList ) {
			
		for(Object d : RecipeIngreList) {
			System.out.println("object1 = " + d);
		}
		
		for(Object d : recipeOrderDtoList) {
			System.out.println("object2 = " + d);
		}
		
		
		
		
		
		return "redirect:/";
	}
	
	@GetMapping("/crawl")
	public String crawlAndDisplayRecipes() {


	    
	    
	    // 웹 페이지 열기
	   
		int count = 0;
	 
	    for(int i= 2; i<5; i++) {
		    // Edge 드라이버 경로 설정
		    System.setProperty("webdriver.edge.driver", "C:\\Users\\EZEN-36\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		    WebDriver driver = new EdgeDriver();
	    	
	    	 driver.get("https://wtable.co.kr/recipes");
		    try {
		        Thread.sleep(3000); // 1초 동안 대기
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
	    

	    // "한식" 클릭 (XPath를 사용하여 클릭)
	    String xpath = "//*[@id=\"__next\"]/div/main/div/div/div/section[3]/ul/li[" + i +"]";
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
	    	//-------------------레시피 리스트---------------------------
	    	Recipe recipeObject = new Recipe();
	        WebElement duQJWIImage = erZvWPElement.findElement(By.cssSelector(".duQJWI"));
	        String srcValue = duQJWIImage.getAttribute("src");
	        WebElement LxJcTElements = erZvWPElement.findElement(By.cssSelector(".LxJcT"));
	        String subTitleValue = LxJcTElements.getText();
	        WebElement hpYiJKElements = erZvWPElement.findElement(By.cssSelector(".hpYiJK"));
	        String TitleValue = hpYiJKElements.getText();
	        WebElement MiXMVElements = erZvWPElement.findElement(By.cssSelector(".MiXMV"));
	        String levelValue = MiXMVElements.getText();
	        WebElement MiXMVElement = erZvWPElement.findElement(By.cssSelector(".kKXboO"));
	        String rurTimeValue = MiXMVElement.getText();
	        
	      //-------------------레시피 리스트---------------------------
	      
	        
	        
	      
	    
	            // 페이지가 새로 로딩되면서 DOM이 변경되므로 매번 요소 목록을 다시 가져와야 합니다.
	            erZvWPElements = driver.findElements(By.cssSelector(".erZvWP"));
	            
	            // 요소를 클릭하여 상세 페이지로 이동합니다.
	            erZvWPElement.click();

	            try {
	                Thread.sleep(2000); // 페이지 로딩 대기
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            // 상세 페이지에서 .IdQIJ 클래스의 텍스트를 가져옵니다.
	            WebElement idQIJElement = driver.findElement(By.cssSelector(".IdQIJ"));
	            String descriptionValue = idQIJElement.getText();
	            
	            
	         // 기본 재료 이름 저장
	            WebElement basicIngreElement = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div[2]/div[1]/div/ul/li[1]/ul"));
	            List<WebElement> basicIngreElements = basicIngreElement.findElements(By.cssSelector(".fCbbYE"));
	            count++;

	            for(WebElement ingre: basicIngreElements) {
	                RecipeIngre recipeIngre = new RecipeIngre();
	                recipeIngre.setBasicIngreName(ingre.getText());
	                recipeIngre.setRecipeCount(count);
	                recipeIngreRepository.save(recipeIngre);
	            }
	            
	            
	            

	            // 시즌 재료 이름 저장
	            try {
	                WebElement seasonIngreElement = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div[2]/div[1]/div/ul/li[2]/ul"));
	                List<WebElement> seasonIngreElements = seasonIngreElement.findElements(By.cssSelector(".fCbbYE"));

	                for(WebElement ingre: seasonIngreElements) {
	                    RecipeIngre recipeIngre = new RecipeIngre();
	                    recipeIngre.setSeasonIngreName(ingre.getText());  
	                    recipeIngre.setRecipeCount(count);
	                    recipeIngreRepository.save(recipeIngre);
	                }
	                
	                
	                
	                
	            }catch (NoSuchElementException e) {
	                System.out.println("NoSuchElementException 발생: " + e.getMessage());
	            } catch (Exception e) {
	                System.out.println("기타 예외 발생: " + e.getMessage());
	            }
	            
	            
	            
	            // 레시피 저장
	            try {

	            	List<WebElement> ihCzrNElements = driver.findElements(By.cssSelector(".ihCzrN"));
	            	
	            	for(WebElement order: ihCzrNElements) {
	            		WebElement imageElement = order.findElement(By.tagName("img"));
	            		String imageUrl = imageElement.getAttribute("src");
	            		WebElement desElement = order.findElement(By.cssSelector(".enJPxd"));
	            		
	            	RecipeOrder recipeOrder =new RecipeOrder();
	            	
	            	 recipeOrder.setImgUrl(imageUrl);
	            	 recipeOrder.setContent(desElement.getText());
	            	 recipeOrder.setOrder_number(count);
	            	 recipeOrderRepository.save(recipeOrder);
	            	
	            	
	                }
	                
	            }
	                
	                
	            catch (NoSuchElementException e) {
	                System.out.println("NoSuchElementException 발생: " + e.getMessage());
	            } catch (Exception e) {
	                System.out.println("기타 예외 발생: " + e.getMessage());
	            }
	            
	            

	            // 이전 페이지로 돌아옵니다.
	            driver.navigate().back();
	            
	            try {
	                Thread.sleep(2000); // 페이지 로딩 대기
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            
	            recipeObject.setDescription(descriptionValue);
	        


	 
		    
	    
		
	        
	
	        recipeObject.setImageUrl(srcValue);
	        recipeObject.setSubTitle(subTitleValue);
	        recipeObject.setTitle(TitleValue);
	        recipeObject.setCategory(koreanCuisineLink.getText());
	        recipeObject.setLevel(levelValue);
	        recipeObject.setDurTime(rurTimeValue);
	        
	        
	        recipeRepository.save(recipeObject);
	        }

	    
	    driver.quit();
	    try {
	        Thread.sleep(1000); // 1초 동안 대기
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	
	}
   
	
	    

	    

	    return "index";
	}
	
}
