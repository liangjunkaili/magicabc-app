package com.magicabc.magicabcapp.util;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

public class EncryptUtil {
    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md;
        String strDes;
        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }
    private static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }
    //字典序
    public static String sortForMap(Map<String,String> param){
        StringBuilder result = new StringBuilder();
        Set<String> sets = param.keySet();
        List<String> list = new ArrayList<>(sets);
        Collections.sort(list);
        for (String s : list){
            result.append(s).append("=").append(param.get(s)).append("&");
        }
        return result.toString().substring(0,result.toString().length()-1);
    }
    //统一的微信支付 Sign 签名生成方法
    public static String getSign(Map<String,String> param){
        String tmp = EncryptUtil.sortForMap(param);
        String signTmp = tmp + "&key="+WXConfiguration.key;
        String sign = EncryptUtil.Encrypt(signTmp,"MD5").toUpperCase();
        return sign;
    }
    public static String base64ForStr(String string){
//        Base64.Decoder decoder = Base64.getDecoder();
        Base64.Encoder encoder = Base64.getEncoder();
//        decoder.decode(string);
        return encoder.encodeToString(string.getBytes());
    }
    public static void main(String[] args){
        System.out.println(EncryptUtil.Encrypt("admin","Md5"));
    }

    public static final String KEY_ALGORITHM = "DES";
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    /**
     * 生成密钥
     */
    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(56);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }
    public static String initKeyString() throws NoSuchAlgorithmException {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(initKey());
    }
    /**
     * 还原密钥
     */
    public static Key toKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
//        SecretKey secretKey1 = new SecretKeySpec(key,KEY_ALGORITHM);
        return secretKey;
    }
    /**
     **加密
     */
    public static byte[] encrypt(byte[] data,byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,k);
        return cipher.doFinal(data);
    }
    /**
     * 解密
     */
    public static byte[] decrypt(byte[] data,byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,k);
        return cipher.doFinal(data);
    }
    /**
     * 初始化盐
     */
    public static byte[] initSalt(){
        SecureRandom random = new SecureRandom();
        return random.generateSeed(8);
    }
}
