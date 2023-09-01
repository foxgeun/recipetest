package com.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.recipe.dto.RecipeDto;

import com.recipe.dto.RecipeMainDto;

import com.recipe.dto.RecipeSearchDto;

import com.recipe.entity.Recipe;
import com.recipe.repository.CommentRepository;
import com.recipe.repository.RecipeListRepository;

import com.recipe.dto.RecipeNewDto;
import com.recipe.entity.RecipeIngre;
import com.recipe.entity.RecipeOrder;
import com.recipe.repository.RecipeIngreRepository;
import com.recipe.repository.RecipeOrderRepository;
import com.recipe.repository.RecipeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // @Autowired를 사용하지 않고 필드의 의존성 주입을 시켜준다
@Transactional
public class RecipeService {
	
	@Autowired
	private final RecipeRepository recipeRepository;
	private final RecipeListRepository recipeListRepository;
	private final CommentRepository commentRepository;
	private final RecipeIngreRepository recipeIngreRepository;
	private final RecipeOrderRepository recipeOrderRepository;
	
	
	public Recipe getRecipeDetailByid(Long id) {
		return recipeRepository.getRecipeDetailByid(id);
	}

	
	private final FileService fileService;
	
	private String itemImgLocation = "C:/yummy/recipe";
	
	public Long saveRecipe(RecipeNewDto recipeNewDto , MultipartFile recipeImgFile , 
			List<String> RecipeingreMaterialList , List<String> RecipeingreNameList,
			List<String> recipeOrderContentList , List<MultipartFile> recipeOrderImgFile ) 
					throws Exception{ // , String email
		

		
			Recipe recipe = recipeNewDto.createRecipe();
			
			String imgName = recipeImgFile.getOriginalFilename();
			String imgUrl = "";
			
			if(!StringUtils.isEmpty(imgName)) {
				imgName = fileService.uploadFile(itemImgLocation, 
						imgName, recipeImgFile.getBytes());
				imgUrl = "/img/recipe/" + imgName;
				
				recipe.updateRecipeImg(imgUrl, imgName);
//				recipe.setMember(member);
				recipeRepository.save(recipe);
			}
			
		for(int i=0; i<RecipeingreMaterialList.size(); i++) {
			
			RecipeIngre recipeIngre = new RecipeIngre();
			
			recipeIngre.setRecipe(recipe);
			recipeIngre.setIngreMaterial(RecipeingreMaterialList.get(i));
			recipeIngre.setIngreName(RecipeingreNameList.get(i));
			recipeIngreRepository.save(recipeIngre);
		}
		
		for(int i=0; i<recipeOrderContentList.size(); i++) {
			RecipeOrder recipeOrder = new RecipeOrder();
			
			recipeOrder.setRecipe(recipe);
			recipeOrder.setContent(recipeOrderContentList.get(i));
			recipeOrder.setOrder_number(i+1);
			
			String imgName2 = recipeOrderImgFile.get(i).getOriginalFilename();
			String imgUrl2 = "";
			
			if(!StringUtils.isEmpty(imgName2)) {
				imgName2 = fileService.uploadFile(itemImgLocation, 
						imgName2, recipeOrderImgFile.get(i).getBytes());
				imgUrl2 = "/img/recipe/" + imgName2;
				
				recipeOrder.updateRecipeOrderImg(imgUrl2, imgName2);
				recipeOrderRepository.save(recipeOrder);
			}
			
		}
			
			return recipe.getId();
	}
	
	
	
	public Page<Recipe> getRecipePage(Pageable pageable){
		Page<Recipe> recipe = recipeRepository.findAll(pageable);
		
		return recipe;
	}

//	@Transactional(readOnly = true)
//	public Page<RecipeDto> getAdminRecipePage(MemberSearchDto memberSearchDto, Pageable pageable, Long recipeId) {
//
//		if (recipeId == null) {
//			// recipeId가 null일 경우에 대한 처리
//			// 예를 들어, 예외 던지기 또는 특정 값을 반환하기 등
//			throw new IllegalArgumentException("Recipe ID must not be null.");
//		}
//
//		// 레시피 Id로 레시피 조회
//		Recipe recipe = recipeListRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);
//
//		// 작성자의 댓글 수 조회
//		Long memberId = recipe.getMember().getId();
//		int commentCount = commentRepository.countByMemberId(memberId);
//
//		System.out.println("commentCount" + commentCount);
//
//		RecipeDto recipeDto = RecipeDto.of(recipe);
//		recipeDto.setCommentCount(commentCount);
//
//		System.out.println("recipeDto.getComment" + recipeDto.getCommentCount());
//		List<RecipeDto> recipeDtoList = new ArrayList<>();
//		recipeDtoList.add(recipeDto);
//
//		// 전체 개수 계산
//		Long totalCount = recipeListRepository.count();
//
//		return new PageImpl<>(recipeDtoList, pageable, totalCount);
//		
//	}

	public Page<RecipeDto> getAdminRecipePage(RecipeSearchDto recipeSearchDto, Pageable pageable) {
		Page<RecipeDto> getAdminRecipePage = recipeListRepository.getAdminRecipePage(recipeSearchDto, pageable);
		return getAdminRecipePage;

	}
		
		@Transactional(readOnly = true)
		public List<RecipeMainDto> getRecipeNewList() {
			List<RecipeMainDto> getRecipeNewList = recipeRepository.getRecipeNewList();
			return getRecipeNewList;
		}
		
		@Transactional(readOnly = true)
		public List<RecipeMainDto> getRecipeBestList() {
			List<RecipeMainDto> getRecipeBestList = recipeRepository.getRecipeBestList();
			return getRecipeBestList; 
		}
		
		@Transactional(readOnly = true)
		public List<RecipeMainDto> getRecipeTotalList() {
			List<RecipeMainDto> getRecipeTotalList = recipeRepository.getRecipeTotalList();
			return getRecipeTotalList; 
		}
		

		
		
	
	
	




	// 레시피 삭제
	public void deleteRecipe(Long recipeId) {

		commentRepository.deleteByRecipeId(recipeId);
		// ★delete하기 전에 select를 한번 해준다
		// ->영속성 컨텍스트에 엔티티를 저장한 후 변경 감지를 하도록 하기 위해
		Recipe recipe = recipeListRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);

		// delete
		recipeListRepository.delete(recipe);
	}

}

