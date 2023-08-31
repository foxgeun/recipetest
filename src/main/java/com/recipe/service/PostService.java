package com.recipe.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.recipe.dto.MemberDto;
import com.recipe.dto.PostDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.entity.PostImg;
import com.recipe.entity.Post;
import com.recipe.entity.Post;
import com.recipe.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

	private final PostRepository postRepository;
	private final PostImgService postImgService;

	// post 테이블에 게시글 등록(insert)
	public Long savePost(PostDto postDto, List<MultipartFile> postImgFileList) throws Exception {

		// 1. 상품 등록
		Post post = postDto.createPost(); // dto -> entity
		postRepository.save(post); // insert(저장)

		// 2. 이미지 등록
		for (int i = 0; i < postImgFileList.size(); i++) {
			// ★fk키를 사용시 부모테이블에 해당하는 entity를 먼저 넣어줘야 한다.
			PostImg attFile = new PostImg();
			attFile.setPost(post);

			postImgService.savePostImg(attFile, postImgFileList.get(i));

		}

		return post.getId(); // 등록한 상품 id를 리턴
	}

	@Transactional(readOnly = true)
	public Page<PostDto> getAdminPostListPage(RecipeSearchDto recipeSearchDto, Pageable pageable) {
		Page<PostDto> postPage = postRepository.getAdminPostListPage(recipeSearchDto, pageable);
		return postPage;
	}
	
	@Transactional(readOnly = true)
	public Page<PostDto> getPostListPage(RecipeSearchDto recipeSearchDto, Pageable pageable) {
		Page<PostDto> postPage = postRepository.getPostListPage(recipeSearchDto, pageable);
		return postPage;
	}
}
