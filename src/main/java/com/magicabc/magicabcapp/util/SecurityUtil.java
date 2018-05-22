package com.magicabc.magicabcapp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.util.Map;

/**
 * Provider,Security,MessageDigest,DigestInputStream,DigestOutputStream
 */
public class SecurityUtil {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        for (Provider p:Security.getProviders()){
            System.out.println(p);
            for (Map.Entry<Object,Object> entry : p.entrySet()){
                System.out.println("\t"+entry.getKey());
            }
        }

        byte[] input = "sha".getBytes();
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(input);
        byte[] output = sha.digest();
    }

    public void DigestInputStream() throws NoSuchAlgorithmException, IOException {
        byte[] input = "md5".getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(input),md);
        dis.read(input,0,input.length);
        byte[] output = dis.getMessageDigest().digest();
        dis.close();
    }

    public void DigestOutputStream() throws NoSuchAlgorithmException, IOException {
        byte[] input = "md5".getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestOutputStream dos = new DigestOutputStream(new ByteArrayOutputStream(),md);
        dos.write(input,0,input.length);
        byte[] output = dos.getMessageDigest().digest();
        dos.flush();
        dos.close();
    }

    public void AlgorithmParameters() throws NoSuchAlgorithmException, IOException {
        AlgorithmParameters ap = AlgorithmParameters.getInstance("DES");
        ap.init(new BigInteger("19050619766489163472469").toByteArray());
        byte[] b = ap.getEncoded();
        System.out.print(new BigInteger(b).toString());
    }
}
