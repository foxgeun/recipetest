package com.recipe.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.recipe.dto.MyPageDto;
import com.recipe.entity.Member;
import com.recipe.entity.Recipe;
import com.recipe.repository.MemberRepository;
import com.recipe.repository.RecipeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@RequiredArgsConstructor
@Transactional
public class MyPageService {

	private final String imgLocation = "C:/recipe/";
	private final MemberRepository memberRepository;
	private final RecipeRepository recipeRepository;
	
	

	//회원정보가져오기 
	@Transactional(readOnly = true)
	public MyPageDto getMemberInfo(Long id){
		
		Member member = memberRepository.findById(id)
										.orElseThrow(EntityNotFoundException::new);
		
		MyPageDto myPageDto = MyPageDto.of(member);


		return myPageDto;
	}
	
	
	//회원정보수정하기
	public Long editMember(MyPageDto myPageDto, MultipartFile imgFile) throws Exception {
		
		Member member = memberRepository.findById(myPageDto.getId())
										.orElseThrow(EntityNotFoundException::new);
		
		Long memberId = myPageDto.getId();
		
		updateImg(memberId,imgFile);
		member.editMember(myPageDto);
		
		
		
		return member.getId();
	}
	
	
	
	//이미지!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void saveImage(Member member,MultipartFile imgFile) throws Exception {
		String oriImgName = imgFile.getOriginalFilename();    //파일이름 -> 이미지1.jpg
		String imgUrl = "";
		String imgName = "";
		    
		    // 1. 파일을 itemImgLocation에 저장
		if(!StringUtils.isEmpty(oriImgName)) {
			//oriImgName이 빈문자열이 아니라면 이미지 파일 업로드
			imgName = uploadFile(imgLocation, 
					oriImgName, imgFile.getBytes());
			imgUrl = "images/" + imgName;
		}
		    
		member.updateImg(oriImgName, imgName, imgUrl);
		memberRepository.save(member);
	}
	
	public void updateImg(Long id, MultipartFile imgFile)
	throws Exception{
		
		if(!imgFile.isEmpty()) {
			
			Member memberId = memberRepository.findById(id)
											  .orElseThrow(EntityNotFoundException::new);
			
			//기존 이미지 파일 C:/hotel/room 폴더에서 삭제
			if(!StringUtils.isEmpty(memberId.getImgName())) {
				deleteFile(imgLocation + "/" + memberId.getImgName()); 
			} 
			//수정된 이미지 파일 C:/shop/item 에 업로드
			
			String oriImgName = imgFile.getOriginalFilename();
			String imgName = uploadFile(imgLocation, oriImgName, imgFile.getBytes());
			String imgUrl = "/images/" + imgName;
			System.out.println(imgUrl+"askjgnaskjnaskjgnasgkj");
			//update쿼리문 실행
			/* ★★★ 한번 insert를 진행하면 엔티티가 영속성 컨텍스트에 저장이 되므로 
			그 이후에는 변경감지 기능이 동작하기 때문에 개발자는 update쿼리문을 쓰지 않고
			엔티티 데이터만 변경해주면 된다. */
			memberId.updateImg(oriImgName, imgName, imgUrl);
			
		}
		
	}

	
	
	//파일업로드
	public String uploadFile(String uploadPath, String originalFileName,
			byte[] fileData) throws Exception {
		UUID uuid = UUID.randomUUID(); //중복되지 않은 이름을 만든다
		
		// 이미지1.jpg -> 이미지의 확장자 명을 구한다
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
	
		// 파일이름 생성
		String savedFileName = uuid.toString() + extension;
		
		// C:/shop/item/ERSFHG4FDGD454.jpg
		String fileUploadFullUrl = uploadPath + "/" + savedFileName;
		
		//파일업로드
		FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
		fos.write(fileData);
		fos.close();
		
		return savedFileName;
	}
	
	//파일삭제
	public void deleteFile(String filePath) throws Exception{
		// filePath ->  C:/shop/item/ERSFHG4FDGD454.jpg
		File deleteFile = new File(filePath); //파일이 저장된 경로를 이용해서 파일 객체를 생성
		
		//파일삭제
		if(deleteFile.exists()) {//해당 경로에 파일이 있으면
			deleteFile.delete(); //파일삭제
			log.info("파일을 삭제하였습니다."); //로그기록을 저장한다
		}else {
			log.info("파일이 존재하지 않습니다.");
		}
		
	}
	
	
	//회원탈퇴
	public void deleteMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
										.orElseThrow(EntityNotFoundException::new);
		
		//delete
		memberRepository.delete(member);
		
	}
	
	
	//레시피리스트
	@Transactional(readOnly = true)
	public List<Recipe> getRecipeList(Long id){
		List<Recipe> recipes = recipeRepository.findRecipe(id);
		
		return recipes;


	}
	
}
