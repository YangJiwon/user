package com.project.ably.service.folder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.ably.common.exception.BusinessErrorCodeException;
import com.project.ably.common.exception.ErrorCode;
import com.project.ably.mapper.folder.FolderQueryMapper;
import com.project.ably.model.response.FolderResponse;
import com.project.ably.model.vo.Folder;

@SpringBootTest(classes = {SelectFolderService.class})
@DisplayName("찜서랍 조회 관련 서비스 테스트")
class SelectFolderServiceTest {
	@Autowired
	private SelectFolderService selectFolderService;

	@MockBean
	private FolderQueryMapper query;

	final String email = "rennes91@daum.net";

	@Test
	@DisplayName("멤버 찜서랍 리스트 없음")
	void selectEmptyFolderList() {
		given(query.selectFolderList(1, 10, email)).willReturn(null);

		BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
				selectFolderService.selectFolderList(1, 10, email));

		assertEquals(exception.getErrorCode(), ErrorCode.SELECT_MEMBER_FOLDER);
	}

	@Test
	@DisplayName("멤버 찜서랍 리스트 조회 정상")
	void selectFolderList() {
		List<Folder> folderList = List.of(Folder.builder().folderNo(1).build());
		given(query.selectFolderList(1, 10, email)).willReturn(folderList);

		List<FolderResponse> response = selectFolderService.selectFolderList(1, 10, email);

		assertEquals(folderList.size(), response.size());
	}

	@Test
	@DisplayName("번호로 찜서랍 조회")
	void checkExistFolderByNo() {
		final int folderNo = 1;
		given(query.checkExistFolderByNo(email, folderNo)).willReturn(1);

		assertEquals(1, selectFolderService.checkExistFolderByNo(email, folderNo));
	}

	@Test
	@DisplayName("이름으로 찜서랍 조회")
	void checkExistFolderByName() {
		final String folderName = "folderName";
		given(query.checkExistFolderByName(email, folderName)).willReturn(1);

		assertEquals(1, selectFolderService.checkExistFolderByName(email, folderName));
	}

	@Test
	@DisplayName("기본 찜서랍 조회")
	void checkDefaultFolder() {
		final int folderNo = 1;
		given(query.checkDefaultFolder(email, folderNo)).willReturn(1);

		assertEquals(1, selectFolderService.checkDefaultFolder(email, folderNo));
	}

	@Test
	@DisplayName("기본 찜서랍 번호 조회")
	void selectDefaultFolderNo() {
		given(query.selectDefaultFolderNo(email)).willReturn(1);

		assertEquals(1, selectFolderService.selectDefaultFolderNo(email));
	}
}
