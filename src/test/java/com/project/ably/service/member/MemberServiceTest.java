package com.project.ably.service.member;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {MemberService.class})
@DisplayName("회원 관련 서비스 테스트")
class MemberServiceTest {
	/*@Autowired
	private MemberService memberService;

	@MockBean
	private MemberQueryMapper query;

	@MockBean
	private MemberCommandMapper	command;

	@MockBean
	private FolderService folderService;

	final String email = "rennes91@daum.net";
	final String password = "qwer1234";
	final MemberRequest memberRequest = new MemberRequest(email, password);

	@Nested
	@DisplayName("회원가입")
	class SignUp {
		@Test
		@DisplayName("회원가입 이메일 중복 요청")
		void findMember() {
			Member duplicateMember = new Member(email,  password, LocalDateTime.now().toString());

			given(query.findMemberByEmail(memberRequest.getEmail())).willReturn(duplicateMember);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					memberService.signUp(memberRequest));

			assertEquals(exception.getErrorCode(), ErrorCode.DUPLICATE_EMAIL);
		}

		@Test
		@DisplayName("회원가입 실패")
		void insertMemberException() {
			given(command.insertMember(memberRequest)).willReturn(2);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					memberService.signUp(memberRequest));

			assertEquals(exception.getErrorCode(), ErrorCode.INSERT_MEMBER);
		}

		@Test
		@DisplayName("회원가입 정상")
		void insertMember() {
			given(query.findMemberByEmail(memberRequest.getEmail())).willReturn(null);
			given(command.insertMember(memberRequest)).willReturn(1);

			assertDoesNotThrow(() -> memberService.signUp(memberRequest));
		}
	}

	@Nested
	@DisplayName("로그인")
	class Login {
		@Test
		@DisplayName("멤버 정보 없음")
		void findMember(){
			given(query.findMemberByEmail(memberRequest.getEmail())).willReturn(null);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					memberService.login(memberRequest));

			assertEquals(exception.getErrorCode(), ErrorCode.NOT_EXIST_MEMBER);
		}

		@Test
		@DisplayName("비밀번호 틀림")
		void notMatchPassword(){
			Member member = new Member(email, password, LocalDateTime.now().toString());

			given(query.findMemberByEmail(memberRequest.getEmail())).willReturn(member);

			BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
					memberService.login(memberRequest));

			assertEquals(exception.getErrorCode(), ErrorCode.NOT_EXIST_MEMBER);
		}

		@Test
		@DisplayName("로그인 정상")
		void login(){
			Member member = new Member(email, new BCryptPasswordEncoder().encode(password), LocalDateTime.now().toString());

			given(query.findMemberByEmail(memberRequest.getEmail())).willReturn(member);

			assertDoesNotThrow(() -> memberService.login(memberRequest));
		}
	}

	@Test
	@DisplayName("멤버 조회")
	void findMemberByEmail(){
		Member member = new Member(email, new BCryptPasswordEncoder().encode(password), LocalDateTime.now().toString());

		given(query.findMemberByEmail(memberRequest.getEmail())).willReturn(member);

		assertEquals(memberService.findMemberByEmail(email), new MemberResponse(member));
	}*/
}
