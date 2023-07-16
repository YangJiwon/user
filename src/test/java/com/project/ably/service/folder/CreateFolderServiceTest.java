package com.project.ably.service.folder;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CreateFolderService.class})
@DisplayName("찜서랍 등록 관련 서비스 테스트")
class CreateFolderServiceTest {
	/*@Autowired
	private CreateFolderService createFolderService;

	@MockBean
	private FolderCommandMapper commannd;

	@MockBean
	private SelectFolderService selectFolderService;

	final String email = "rennes91@daum.net";
	final Folder folder = Folder.builder()
			.folderNo(1)
			.folderName("기본서랍")
			.email(email)
			.build();

	@Nested
	@DisplayName("찜서랍 생성")
	class CreateFolder {
		@Test
		@DisplayName("찜 서랍 생성 실패")
		void createFolderException() {
			given(commannd.insertFolder(folder)).willReturn(2);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					createFolderService.updateFolder(folder));

			assertEquals(exception.getErrorCode(), ErrorCode.INSERT_FOLDER);
		}

		@Test
		@DisplayName("찜 서랍 매핑 실패")
		void createFolderMappingException() {
			given(commannd.insertFolder(folder)).willReturn(1);
			//given(commannd.insertMemberFolder(folder)).willReturn(2);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					createFolderService.updateFolder(folder));

			assertEquals(exception.getErrorCode(), ErrorCode.INSERT_MEMBER_FOLDER);
		}

		@Test
		@DisplayName("찜 서랍 등록 성공")
		void createFolderMapping() {
			given(commannd.insertFolder(folder)).willReturn(1);
		//	given(commannd.insertMemberFolder(folder)).willReturn(1);

			assertDoesNotThrow(() -> createFolderService.updateFolder(folder));
		}
	}

	@Test
	@DisplayName("유효성 검사 성공")
	void validation() {
		given(selectFolderService.checkExistFolderByName(folder.getEmail(), folder.getFolderName())).willReturn(null);

		assertDoesNotThrow(() -> createFolderService.validation(folder));
	}

	@Test
	@DisplayName("유효성 검사 실패")
	void validationException() {
		//given(selectFolderService.checkExistFolderByName(folder.getEmail(), folder.getFolderName())).willReturn(1);

		BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
				createFolderService.validation(folder));

		assertEquals(exception.getErrorCode(), ErrorCode.ALREADY_EXIST_FOLDER);
	}*/
}
