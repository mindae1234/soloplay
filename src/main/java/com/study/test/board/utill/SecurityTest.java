package com.study.test.board.utill;

public class SecurityTest {
	
	public static void main(String[] args) {
		
		SecurityUtil sutil = new SecurityUtil();
		String value1 = sutil.encryptSHA256("a");
		
		String value2 = sutil.encryptSHA256("a1");
		
		System.out.println(value1);
		System.out.println(value2);
	}

}
