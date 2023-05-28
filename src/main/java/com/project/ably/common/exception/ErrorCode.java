package com.project.ably.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입되어 있는 이메일입니다."),
	INSERT_MEMBER(HttpStatus.INTERNAL_SERVER_ERROR, "회원가입에 실패하였습니다."),
	NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 잘못되었습니다."),
	ALREADY_EXIST_FOLDER(HttpStatus.BAD_REQUEST, "이미 존재하는 찜서랍 이름입니다."),
	NOT_EXIST_FOLDER(HttpStatus.BAD_REQUEST, "존재하지 않는 찜서랍입니다."),
	NOT_REMOVE_DEFAULT_FOLDER(HttpStatus.BAD_REQUEST, "기본서랍은 삭제가 불가합니다."),
	INSERT_FOLDER(HttpStatus.INTERNAL_SERVER_ERROR, "찜서랍 생성에 실패하였습니다."),
	INSERT_MEMBER_FOLDER(HttpStatus.INTERNAL_SERVER_ERROR, "멤버의 찜서랍 매핑에 실패하였습니다."),
	DELETE_FOLDER(HttpStatus.INTERNAL_SERVER_ERROR, "찜서랍 삭제에 실패하였습니다."),
	DELETE_MEMBER_FOLDER(HttpStatus.INTERNAL_SERVER_ERROR, "멤버의 찜서랍 매핑 삭제에 실패하였습니다."),
	SELECT_MEMBER_FOLDER(HttpStatus.BAD_REQUEST, "멤버의 찜서랍이 존재하지 않습니다."),
	INSERT_WISH(HttpStatus.INTERNAL_SERVER_ERROR, "찜하기에 실패하였습니다."),
	INSERT_FOLDER_WISH(HttpStatus.INTERNAL_SERVER_ERROR, "찜서랍과 찜 매핑에 실패하였습니다."),
	DELETE_WISH(HttpStatus.INTERNAL_SERVER_ERROR, "찜해제에 실패하였습니다."),
	DELETE_FOLDER_WISH(HttpStatus.INTERNAL_SERVER_ERROR, "찜서랍과 찜 매핑 삭제에 실패하였습니다."),
	NOT_EXIST_DEFAULT_FOLDER(HttpStatus.INTERNAL_SERVER_ERROR, "기본 찜서랍이 존재하지 않습니다."),
	ALREADY_EXIST_WISH(HttpStatus.BAD_REQUEST, "이미 존재하는 찜입니다."),
	NOT_EXIST_WISH_NO(HttpStatus.BAD_REQUEST, "존재하지 않는 찜이 있습니다."),
	SELECT_WISH(HttpStatus.BAD_REQUEST, "찜 조회에 실패하였습니다."),
	;

	private final HttpStatus httpStatus;
	private final String errMsg;
}
