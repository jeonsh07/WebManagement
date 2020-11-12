package com.mrc.db.dto.member;



public enum enAuthType {
	Admin(1), AdMember(10), LocalMember(20), Member(30);
	 final private Integer authType;
	 private enAuthType(Integer authType) { //enum에서 생성자 같은 역할
        this.authType = authType;
    }
	
	public static  enAuthType IntToEnum(int val)
	{
		switch(val)
		{
		case 1:
			return enAuthType.Admin;
		case 10:
			return enAuthType.AdMember;
		case 20:
			return enAuthType.LocalMember;
		default:
			return enAuthType.Member;
		}
	}
    public Integer getenAuthType() { // 문자를 받아오는 함수
        return authType;
    }
}

