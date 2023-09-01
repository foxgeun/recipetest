    // Initially show the '레시피' content
    
    $(document).ready(function() {
    // 초기화 코드를 여기에 배치    
    showContent('recipe');
    filterRecipes('PUBLISHED');
});

function showContent(contentId) {
    $('.content').hide();
    $('#' + contentId).show();

    $('.mypage-list').removeClass('active');
    $('.mypage-list[data-menu="' + contentId + '"]').addClass('active');
    $('.mypage-top').removeClass('active');
    
    if (contentId === 'recipe') {
        // 로컬 스토리지에서 선택한 카테고리의 상태를 가져옴
        var selectedTab = localStorage.getItem('selectedTab');
        filterRecipes(selectedTab);
    } else if(contentId === 'reviews'){
		        // 로컬 스토리지에서 선택한 카테고리의 상태를 가져옴
        var selectedTab = localStorage.getItem('selectedTab');
        toggleReviews(selectedTab);
	}
}
    
	function filterRecipes(filterType) {
	    // 모든 레시피 목록 숨기기
	    $('.mypage-content .recipe1').hide();
	    	        $('.mypage-top').removeClass('active');
	        $('.mypage-top[data-menu="' + filterType + '"]').addClass('active');

	    if (filterType === 'PUBLISHED') {
	        // 공개중인 레시피만 보이도록 설정
	        $('.mypage-content .recipe1[data-writing-status="PUBLISHED"]').show();
	        $('.mypage-top').removeClass('active');
	        $('.mypage-top[data-menu="' + filterType + '"]').addClass('active');
	    } else if (filterType === 'DRAFT') {
	        // 작성중인 레시피만 보이도록 설정
	        $('.mypage-content .recipe1[data-writing-status="DRAFT"]').show();
	        $('.mypage-top').removeClass('active');
	        $('.mypage-top[data-menu="' + filterType + '"]').addClass('active');
	    }
	    localStorage.setItem('selectedTab', filterType);
	    }

	

    function updateImagePreview(event) {
        const fileInput = event.target;
        const label = document.getElementById("imagePreviewLabel");

        if (fileInput.files && fileInput.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                label.style.backgroundImage = `url(${e.target.result})`;
            };
            reader.readAsDataURL(fileInput.files[0]);
        }
    }
    
    function checkNicknameAvailability() {
        const nicknameInput = document.getElementById("nickname");
        const nickname = nicknameInput.value;
        const nicknameFeedback = document.getElementById("nicknameFeedback");

        // Make an AJAX request to the server to check nickname availability
        // You can use libraries like Axios or jQuery for the AJAX request

        // Simulate a response from the server
        const isAvailable = true; //Replace with actual response from the server

        if (isAvailable) {
            nicknameInput.classList.remove("is-invalid");
            nicknameInput.classList.add("is-valid");
            nicknameFeedback.innerHTML = `
                <div class="valid-feedback">
                    성공! 사용 가능한 닉네임입니다.
                </div>`;
        } else {
            nicknameInput.classList.remove("is-valid");
            nicknameInput.classList.add("is-invalid");
            nicknameFeedback.innerHTML = `
                <div class="invalid-feedback">
                    죄송합니다. 해당 닉네임은 이미 사용 중입니다. 다른 것을 시도하시겠습니까?
                </div>`;
        }
    }
    // 아래 함수를 호출하여 주소 정보를 입력 폼에 설정합니다.
    function fillAddressFields(addressData) {
        document.getElementById('sample6_postcode').value = addressData.zonecode;
        document.getElementById('sample6_roadAddress').value = addressData.roadAddress;
        document.getElementById('sample6_jibunAddress').value = addressData.jibunAddress;
        
        // 예상 도로명 주소 또는 예상 지번 주소
        var guideTextBox = document.getElementById("guide");
        if (addressData.autoRoadAddress) {
            guideTextBox.innerHTML = '(예상 도로명 주소: ' + addressData.autoRoadAddress + ')';
            guideTextBox.style.display = 'block';
        } else if (addressData.autoJibunAddress) {
            guideTextBox.innerHTML = '(예상 지번 주소: ' + addressData.autoJibunAddress + ')';
            guideTextBox.style.display = 'block';
        } else {
            guideTextBox.innerHTML = '';
            guideTextBox.style.display = 'none';
        }
    }
    
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
    
    function oninputPhone(target) {
        target.value = target.value
            .replace(/[^0-9]/g, '')
            .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
    }
    
    
    function deleteMember(memberId) {
		const deleteConf = confirm("탈퇴하시겠습니까?");
		if (!deleteConf)
			return; //취소버튼 누르면

		//request URL
		var url = "/myPage/deleteMember/" + memberId;

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			url : url, //request URL
			type : "DELETE", //전송방식
			contentType : "application/json",
			beforeSend : function(xhr) {
				//데이터를 전송하기 전에 헤더에 csrf 값을 설정
				xhr.setRequestHeader(header, token);
			},
			dataType : "json",
			cache : false,
			success : function(result, status) {
				location.href = '/';
			}

		});
	}
    
    function deleteRecipe(recipeId) {
		const deleteConf = confirm("레시피를 삭제하시겠습니까?");
		if (!deleteConf)
			return; //취소버튼 누르면

		//request URL
		var url = "/myPage/deleteRecipe/" + recipeId;

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			url : url, //request URL
			type : "DELETE", //전송방식
			contentType : "application/json",
			beforeSend : function(xhr) {
				//데이터를 전송하기 전에 헤더에 csrf 값을 설정
				xhr.setRequestHeader(header, token);
			},
			dataType : "json",
			cache : false,
			success : function(result, status) {
				 location.reload();
			}

		});
	}
function deleteBookmark(bookmarkId, imgElement) {
    var action = imgElement.getAttribute('data-action') || 'delete';

    var newAction, newImageSrc;

    if (action === 'delete') {
        url = "/myPage/deleteBookmark/" + bookmarkId;
        newAction = 'undelete';
        newImageSrc = "/img/favoriteNull.png"; // "삭제" 상태의 이미지
    } else {
        url = "/myPage/undeleteBookmark/" + bookmarkId; // "삭제 취소" API의 URL을 설정해야 합니다.
        newAction = 'delete';
        newImageSrc = "/img/favorite.png"; // 원래 상태의 이미지
    }

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var methodType; // 요청 타입 설정
    if (action === 'delete') {
        methodType = "DELETE";
    } else {
        methodType = "POST"; // 복원 작업은 POST로 처리
    }

    $.ajax({
        url: url,
        type: methodType,
        contentType: "application/json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        dataType: "json",
        cache: false,
        success: function(result, status) {
            imgElement.src = newImageSrc;
            imgElement.setAttribute('data-action', newAction);
        }
    });
	}
	    function deleteComment(commentId) {
		const deleteConf = confirm("댓글을 삭제하시겠습니까?");
		if (!deleteConf)
			return; //취소버튼 누르면

		//request URL
		var url = "/myPage/deleteComment/" + commentId;

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			url : url, //request URL
			type : "DELETE", //전송방식
			contentType : "application/json",
			beforeSend : function(xhr) {
				//데이터를 전송하기 전에 헤더에 csrf 값을 설정
				xhr.setRequestHeader(header, token);
			},
			dataType : "json",
			cache : false,
			success : function(result, status) {
				 location.reload();
			}

		});
	}
	    function deleteReview(reviewId) {
		const deleteConf = confirm("후기를 삭제하시겠습니까?");
		if (!deleteConf)
			return; //취소버튼 누르면

		//request URL
		var url = "/myPage/deleteReview/" + reviewId;

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			url : url, //request URL
			type : "DELETE", //전송방식
			contentType : "application/json",
			beforeSend : function(xhr) {
				//데이터를 전송하기 전에 헤더에 csrf 값을 설정
				xhr.setRequestHeader(header, token);
			},
			dataType : "json",
			cache : false,
			success : function(result, status) {
				 location.reload();
			}

		});
	}


	toggleReviews('myReviewList');
function toggleReviews(contentId) {
    $('.mypage-top').removeClass('active');
    $('.my').addClass('active');

    if (contentId === 'myReviewList') {
        if ($('#myReviewList tbody .mytr').length > 0) {
            $('#myReviewList').css('display', '');
            $('#receivedReviewList').css('display', 'none');
            $('#myReviewListIsEmpty').css('display', 'none');
            $('#receivedReviewListIsEmpty').css('display', 'none');
        } else {
            $('#myReviewList').css('display', 'none');
            $('#receivedReviewList').css('display', 'none');
            $('#myReviewListIsEmpty').css('display', '');
            $('#receivedReviewListIsEmpty').css('display', 'none');
        }
    } else if (contentId === 'receivedReviewList') {
        if ($('#receivedReviewList tbody .receivedtr').length > 0) {
            $('#receivedReviewList').css('display', '');
            $('#myReviewList').css('display', 'none');
            $('#receivedReviewListIsEmpty').css('display', 'none');
            $('#myReviewListIsEmpty').css('display', 'none');
        } else {
            $('#receivedReviewList').css('display', 'none');
            $('#myReviewList').css('display', 'none');
            $('#receivedReviewListIsEmpty').css('display', '');
            $('#myReviewListIsEmpty').css('display', 'none');
        }

        $('.mypage-top').removeClass('active');
        $('.you').addClass('active');
         localStorage.setItem('selectedTab', contentId);
    }
}
