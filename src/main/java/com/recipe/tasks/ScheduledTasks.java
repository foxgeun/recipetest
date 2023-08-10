package com.recipe.tasks;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.recipe.service.RecipeCrawlerService;

@Component
public class ScheduledTasks {
	
	
	
    @Autowired
    private RecipeCrawlerService crawlerService;
    
    
    
   
    @Scheduled(fixedRate = 3600000) // Run every hour
    public void crawlAndSaveScheduled() {
        try {
        	System.out.println("dkdkdjansgkjasngkja");
            crawlerService.crawlAndSaveRecipes();
        } catch (IOException e) {
            // 예외 처리 및 로그 출력
            e.printStackTrace();
        }
    }
}

