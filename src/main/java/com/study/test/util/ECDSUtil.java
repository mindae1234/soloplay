package com.study.test.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECDSUtil {
	private static final String SPEC = "secp256k1";
	private static final String ALGO = "SHA256withECDSA";

	public static Map<String, String> getKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException,
			IOException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {

		Map<String, String> rmap = new HashMap<String, String>();
		Security.addProvider(new BouncyCastleProvider());
		ECGenParameterSpec ecSpec = new ECGenParameterSpec(SPEC);
		KeyPairGenerator g = KeyPairGenerator.getInstance("ECDSA", "BC");
		g.initialize(ecSpec, new SecureRandom());
		KeyPair keypair = g.generateKeyPair();
		PublicKey publicKey = keypair.getPublic();
		PrivateKey privateKey = keypair.getPrivate();


		rmap.put("privateKey", CommonUtil.byteArrayToHex(privateKey.getEncoded()).replace(" ", ""));
		rmap.put("publicKey", CommonUtil.byteArrayToHex(publicKey.getEncoded()).replace(" ", ""));

		return rmap;
	}
	
	public static boolean verify(String publicKey,String extext,String rawData) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, UnsupportedEncodingException{
		Signature ecdsaVerify = Signature.getInstance(ALGO);

		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));

		KeyFactory keyFactory = KeyFactory.getInstance("EC");
		PublicKey publicKey2 = keyFactory.generatePublic(publicKeySpec);

		ecdsaVerify.initVerify(publicKey2);
		ecdsaVerify.update(rawData.getBytes("UTF-8"));
		
		boolean result = ecdsaVerify.verify(Base64.getDecoder().decode(extext));
		
		
		return result;
	}
	
}