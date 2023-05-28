package com.project.ably.service.wish;

import com.project.ably.model.vo.Wish;

public interface WishModifiable {
	default void saveWish(Wish wish){
		wish = setDefaultFolder(wish);
		validation(wish);
		modifyWish(wish);
	}

	default Wish setDefaultFolder(Wish wish){
		return wish;
	}

	void validation(Wish wish);

	void modifyWish(Wish wish);
}
