package com.project.ably.service.folder;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {FolderService.class})
@DisplayName("찜서랍 관련 서비스 테스트")
class FolderServiceTest {
	/*@Autowired
	private FolderService folderService;

	@MockBean
	private CreateFolderService createFolderService;

	@MockBean
	private DeleteFolderService deleteFolderService;

	@MockBean
	private SelectFolderService selectFolderService;

	final String email = "rennes91@daum.net";
	final String password = "qwer1234";
	final MemberRequest memberRequest = new MemberRequest(email, password);

	@Test
	@DisplayName("멤버 찜서랍 리스트 조회 정상")
	void selectFolderList() {
		Folder folder = Folder.builder().folderNo(1).build();
		List<FolderResponse> folderList = List.of(new FolderResponse(folder));
		given(selectFolderService.selectFolderList(1, 10, email)).willReturn(folderList);

		List<FolderResponse> response = folderService.selectFolderList(1, 10, email);

		assertEquals(folderList.size(), response.size());
	}*/
}
