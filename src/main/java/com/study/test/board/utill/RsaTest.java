package com.study.test.board.utill;

import java.util.HashMap;

public class RsaTest {
    

    public static void main(String[] args) {
       RSAUtil rSAUtil = new RSAUtil();
       
        HashMap<String, String> rsaKeyPair = rSAUtil.createKeypairAsString();//키생성 개인키,공개키생성
        String publicKey = rsaKeyPair.get("publicKey");//공개키 받고
        String privateKey = rsaKeyPair.get("privateKey");//개인키 받고

        System.out.println("만들어진 공개키:" + publicKey);
        System.out.println("만들어진 개인키:" + privateKey);

        String plainText = "플레인 텍스트";
        System.out.println("평문: " + plainText);

        String encryptedText = rSAUtil.encode(plainText, publicKey);//encode 암호화하겟다 
        System.out.println("암호화: " + encryptedText);

        String decryptedText = rSAUtil.decode(encryptedText, privateKey);//decode 복호화
        System.out.println("복호화: " + decryptedText);
    }

   
}