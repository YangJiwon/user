package com.project.ably.service.wish;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {SelectWishService.class})
@DisplayName("찜 조회 관련 서비스 테스트")
class SelectWishServiceTest {
	/*@Autowired
	private SelectWishService selectWishService;

	@MockBean
	private WishQueryMapper query;

	@MockBean
	private ProductService productService;

	final String email = "rennes91@daum.net";

	@Test
	@DisplayName("상품 존재 여부 확인")
	void checkExistFolderByNo() {
		final int productNo = 1;
		given(query.checkExistProduct(email, productNo)).willReturn(1);

		assertEquals(1, selectWishService.checkExistProduct(email, productNo));
	}

	@Test
	@DisplayName("찜하기 번호 리스트 조회")
	void selectWishNoList() {
		final int folderNo = 1;
		final List<Integer> response = List.of(1,2);
		given(query.selectWishNoList(folderNo)).willReturn(response);

		assertEquals(response, selectWishService.selectWishNoList(folderNo));
	}

	@Test
	@DisplayName("찜하기 여부 존재 조회")
	void checkExistWishNo() {
		final List<Integer> wishNoList = List.of(1,2);
		final List<Integer> response = List.of(1,2);
		given(query.checkExistWishNo(email, wishNoList)).willReturn(response);

		assertEquals(response, selectWishService.checkExistWishNo(email, wishNoList));
	}

	@Test
	@DisplayName("찜하기 리스트 조회 실패")
	void emptyWishList() {
		given(query.selectWishList(1, 10, email)).willReturn(null);

		BusinessErrorCodeException exception = assertThrows(BusinessErrorCodeException.class, () ->
				selectWishService.selectWishList(1, 10, email));

		assertEquals(exception.getErrorCode(), ErrorCode.SELECT_WISH);
	}

	@Test
	@DisplayName("찜하기 리스트 조회")
	void selectWishList() {
		final Wish request1 = Wish.builder().folderNo(1).folderName("기본서랍").wishNo(1).productNo(1).build();
		final ProductResponse product1 = new ProductResponse(1, "test1", "thumbnail", 100);
		final Map<Integer, ProductResponse> productMap = Map.of(1, product1);
		final List<FolderWithWishResponse> response = List.of(new FolderWithWishResponse(1, "기본서랍", List.of(request1), productMap));

		given(query.selectWishList(1, 10, email)).willReturn(List.of(request1));
		given(productService.getAllProductList()).willReturn(List.of(product1));

		List<FolderWithWishResponse> responses = selectWishService.selectWishList(1, 10, email);
		assertTrue(CollectionUtils.isEqualCollection(responses, response));
	}*/
}
