package com.project.ably.service.wish;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.ably.model.response.FolderWithWishResponse;
import com.project.ably.model.response.ProductResponse;
import com.project.ably.model.vo.Wish;

@SpringBootTest(classes = {WishService.class})
@DisplayName("찜 관련 서비스 테스트")
class WishServiceTest {
	@Autowired
	private WishService wishService;

	@MockBean
	private CreateWishService createWishService;

	@MockBean
	private DeleteWishService deleteWishService;

	@MockBean
	private SelectWishService selectWishService;

	final String email = "rennes91@daum.net";

	@Test
	@DisplayName("찜하기 번호 리스트 조회")
	void selectWishNoList() {
		final int folderNo = 1;
		final List<Integer> response = List.of(1,2);

		given(selectWishService.selectWishNoList(folderNo)).willReturn(response);

		assertEquals(response, wishService.selectWishNoList(folderNo));
	}

	@Test
	@DisplayName("찜하기 리스트 조회")
	void selectWishList() {
		final Wish request1 = Wish.builder().wishNo(1).productNo(1).build();
		final Map<Integer, ProductResponse> productMap = Map.of(1, new ProductResponse(1, "test1", "thumbnail", 100));
		final List<FolderWithWishResponse> response = List.of(new FolderWithWishResponse(1, "기본서랍", List.of(request1), productMap));

		given(selectWishService.selectWishList(1, 10, email)).willReturn(response);

		assertEquals(response, wishService.selectWishList(1, 10, email));
	}
}
