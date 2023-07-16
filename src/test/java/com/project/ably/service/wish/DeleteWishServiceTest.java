package com.project.ably.service.wish;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DeleteWishService.class})
@DisplayName("찜하기 관련 서비스 테스트")
class DeleteWishServiceTest {
	/*@Autowired
	private DeleteWishService deleteWishService;

	@MockBean
	private WishCommandMapper commannd;

	@MockBean
	private SelectWishService selectWishService;

	final String email = "rennes91@daum.net";
	final Wish wish = Wish.builder()
			.folderNo(1)
			.wishNoList(List.of(1))
			.email(email)
			.build();

	@Nested
	@DisplayName("찜해제")
	class ModifyWish {
		@Test
		@DisplayName("찜해제 실패")
		void modifyWishException() {
			given(commannd.deleteWish(wish.getWishNoList())).willReturn(2);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					deleteWishService.modifyWish(wish));

			assertEquals(exception.getErrorCode(), ErrorCode.DELETE_WISH);
		}

		@Test
		@DisplayName("찜 매핑 삭제 실패")
		void modifyWishMappingException() {
			List<Integer> wishNoList = wish.getWishNoList();
			given(commannd.deleteWish(wishNoList)).willReturn(1);
			given(commannd.deleteFolderWish(wishNoList)).willReturn(2);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					deleteWishService.modifyWish(wish));

			assertEquals(exception.getErrorCode(), ErrorCode.DELETE_FOLDER_WISH);
		}

		@Test
		@DisplayName("찜해제 성공")
		void createFolderMapping() {
			List<Integer> wishNoList = wish.getWishNoList();
			given(commannd.deleteWish(wishNoList)).willReturn(1);
			given(commannd.deleteFolderWish(wishNoList)).willReturn(1);

			assertDoesNotThrow(() -> deleteWishService.modifyWish(wish));
		}
	}

	@Nested
	@DisplayName("유효성 검사")
	class Validation {
		@Test
		@DisplayName("찜해제 유효성 검사 실패")
		void notExistWish() {
			given(selectWishService.checkExistWishNo(email, wish.getWishNoList())).willReturn(List.of(1,2));

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					deleteWishService.validation(wish));

			assertEquals(exception.getErrorCode(), ErrorCode.NOT_EXIST_WISH_NO);
		}

		@Test
		@DisplayName("유효성 검사 성공")
		void validation() {
			given(selectWishService.checkExistWishNo(email, wish.getWishNoList())).willReturn(List.of(1));

			assertDoesNotThrow(() -> deleteWishService.validation(wish));
		}
	}*/
}
