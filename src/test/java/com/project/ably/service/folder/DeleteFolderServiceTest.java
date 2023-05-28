package com.project.ably.service.folder;

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
import com.project.ably.mapper.folder.FolderCommandMapper;
import com.project.ably.model.vo.Folder;
import com.project.ably.service.wish.WishService;

@SpringBootTest(classes = {DeleteFolderService.class})
@DisplayName("찜서랍 삭제 관련 서비스 테스트")
class DeleteFolderServiceTest {
	@Autowired
	private DeleteFolderService deleteFolderService;

	@MockBean
	private FolderCommandMapper commannd;

	@MockBean
	private SelectFolderService selectFolderService;

	@MockBean
	private WishService wishService;

	final String email = "rennes91@daum.net";
	final Folder folder = Folder.builder()
			.folderNo(1)
			.folderName("기본서랍")
			.email(email)
			.build();
	final int folderNo = folder.getFolderNo();

	@Nested
	@DisplayName("찜서랍 삭제")
	class CreateFolder {
		@Test
		@DisplayName("찜 서랍 삭제 실패")
		void deleteFolderException() {
			given(commannd.deleteFolder(folderNo)).willReturn(2);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					deleteFolderService.updateFolder(folder));

			assertEquals(exception.getErrorCode(), ErrorCode.DELETE_FOLDER);
		}

		@Test
		@DisplayName("찜 서랍 매핑 삭제 실패")
		void deleteFolderMappingException() {
			given(commannd.deleteFolder(folderNo)).willReturn(1);
			given(commannd.deleteMemberFolder(folderNo)).willReturn(2);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					deleteFolderService.updateFolder(folder));

			assertEquals(exception.getErrorCode(), ErrorCode.DELETE_MEMBER_FOLDER);
		}

		@Test
		@DisplayName("찜 서랍 등록 성공")
		void createFolderMapping() {
			given(commannd.deleteFolder(folderNo)).willReturn(1);
			given(commannd.deleteMemberFolder(folderNo)).willReturn(1);

			assertDoesNotThrow(() -> deleteFolderService.updateFolder(folder));
		}
	}

	@Nested
	@DisplayName("찜서랍 유효성 검사")
	class Validation {
		@Test
		@DisplayName("삭제할 찜서랍 없음")
		void notExistFolder() {
			given(selectFolderService.checkExistFolderByNo(folder.getEmail(), folderNo)).willReturn(null);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					deleteFolderService.validation(folder));

			assertEquals(exception.getErrorCode(), ErrorCode.NOT_EXIST_FOLDER);
		}

		@Test
		@DisplayName("기본 찜서랍은 삭제 불가")
		void defaultFolder() {
			given(selectFolderService.checkExistFolderByNo(folder.getEmail(), folderNo)).willReturn(1);
			given(selectFolderService.checkExistFolderByNo(folder.getEmail(), folderNo)).willReturn(1);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					deleteFolderService.validation(folder));

			assertEquals(exception.getErrorCode(), ErrorCode.NOT_REMOVE_DEFAULT_FOLDER);
		}


		@Test
		@DisplayName("유효성 검사 성공")
		void validation() {
			given(selectFolderService.checkExistFolderByNo(folder.getEmail(), folderNo)).willReturn(1);
			given(selectFolderService.checkDefaultFolder(folder.getEmail(), folderNo)).willReturn(null);

			assertDoesNotThrow(() -> deleteFolderService.validation(folder));
		}
	}
}
