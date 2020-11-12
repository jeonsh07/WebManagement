package com.mrc.db.dto.common;

public enum enResultType {
	Info("정보"), Warning("경고"), Error("에러");

	final private String name;

	private enResultType(String name) { // enum에서 생성자 같은 역할
		this.name = name;
	}

	public String getenResultType() { // 문자를 받아오는 함수
		return name;
	}
}
