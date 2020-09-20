package com.study.test.board.utill;

import java.util.HashMap;

public class RsaTest {
    

    public static void main(String[] args) {
       RSAUtil rSAUtil = new RSAUtil();
       
        HashMap<String, String> rsaKeyPair = rSAUtil.createKeypairAsString();//Ű���� ����Ű,����Ű����
        String publicKey = rsaKeyPair.get("publicKey");//����Ű �ް�
        String privateKey = rsaKeyPair.get("privateKey");//����Ű �ް�

        System.out.println("������� ����Ű:" + publicKey);
        System.out.println("������� ����Ű:" + privateKey);

        String plainText = "�÷��� �ؽ�Ʈ";
        System.out.println("��: " + plainText);

        String encryptedText = rSAUtil.encode(plainText, publicKey);//encode ��ȣȭ�ϰٴ� 
        System.out.println("��ȣȭ: " + encryptedText);

        String decryptedText = rSAUtil.decode(encryptedText, privateKey);//decode ��ȣȭ
        System.out.println("��ȣȭ: " + decryptedText);
    }

   
}