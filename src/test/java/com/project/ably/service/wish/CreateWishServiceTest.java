package com.project.ably.service.wish;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.mapper.wish.WishCommandMapper;
import com.project.ably.model.vo.Wish;
import com.project.ably.service.folder.SelectFolderService;

@SpringBootTest(classes = {CreateWishService.class})
@DisplayName("찜하기 관련 서비스 테스트")
class CreateWishServiceTest {
	@Autowired
	private CreateWishService createWishService;

	@MockBean
	private WishCommandMapper commannd;

	@MockBean
	private SelectFolderService selectFolderService;

	@MockBean
	private SelectWishService selectWishService;

	final String email = "rennes91@daum.net";
	final Wish wish = Wish.builder()
			.folderNo(1)
			.wishNo(1)
			.email(email)
			.build();

	@Nested
	@DisplayName("찜하기")
	class ModifyWish {
		@Test
		@DisplayName("찜하기 실패")
		void modifyWishException() {
			given(commannd.insertWish(wish)).willReturn(2);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					createWishService.modifyWish(wish));

			assertEquals(exception.getErrorCode(), ErrorCode.INSERT_WISH);
		}

		@Test
		@DisplayName("찜 매핑 실패")
		void modifyWishMappingException() {
			given(commannd.insertWish(wish)).willReturn(1);
			given(commannd.insertFolderWish(wish)).willReturn(2);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					createWishService.modifyWish(wish));

			assertEquals(exception.getErrorCode(), ErrorCode.INSERT_FOLDER_WISH);
		}

		@Test
		@DisplayName("찜하기 성공")
		void createFolderMapping() {
			given(commannd.insertWish(wish)).willReturn(1);
			given(commannd.insertFolderWish(wish)).willReturn(1);

			assertDoesNotThrow(() -> createWishService.modifyWish(wish));
		}
	}

	@Nested
	@DisplayName("기본 찜서랍 서랍")
	class SetDefaultFolder {
		@Test
		@DisplayName("찜서랍 번호 있음")
		void notEmptyFolderNo() {
			Wish response = createWishService.setDefaultFolder(wish);

			assertEquals(response, wish);
		}

		final Wish emptyFolder = Wish.builder()
				.folderNo(0)
				.wishNo(1)
				.email(email)
				.build();

		@Test
		@DisplayName("기본 찜서랍 없음")
		void notExistDefaultFolder() {
			given(selectFolderService.selectDefaultFolderNo(email)).willReturn(0);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					createWishService.setDefaultFolder(emptyFolder));

			assertEquals(exception.getErrorCode(), ErrorCode.NOT_EXIST_DEFAULT_FOLDER);
		}

		@Test
		@DisplayName("기본 찜서랍 설정 성공")
		void setDefaultFolder() {
			int defaultFolderNo = 1;
			given(selectFolderService.selectDefaultFolderNo(email)).willReturn(defaultFolderNo);

			Wish response = createWishService.setDefaultFolder(emptyFolder);

			assertEquals(response, emptyFolder.toBuilder().folderNo(defaultFolderNo).build());
		}
	}

	@Nested
	@DisplayName("유효성 검사")
	class Validation {
		@Test
		@DisplayName("찜하기 유효성 검사 실패")
		void notExistFolder() {
			given(selectFolderService.checkExistFolderByNo(email, wish.getFolderNo())).willReturn(null);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					createWishService.validation(wish));

			assertEquals(exception.getErrorCode(), ErrorCode.NOT_EXIST_FOLDER);
		}

		@Test
		@DisplayName("찜하기 유효성 검사 실패")
		void alreadyExistFolder() {
			given(selectFolderService.checkExistFolderByNo(email, wish.getFolderNo())).willReturn(1);
			given(selectWishService.checkExistProduct(email, wish.getProductNo())).willReturn(1);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					createWishService.validation(wish));

			assertEquals(exception.getErrorCode(), ErrorCode.ALREADY_EXIST_WISH);
		}

		@Test
		@DisplayName("유효성 검사 성공")
		void validation() {
			given(selectFolderService.checkExistFolderByNo(email, wish.getFolderNo())).willReturn(1);
			given(selectWishService.checkExistProduct(email, wish.getProductNo())).willReturn(null);

			assertDoesNotThrow(() -> createWishService.validation(wish));
		}
	}
}
