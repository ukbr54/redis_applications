package com.url.shortening.technique1.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class  MD5 {

    /**
     *
     * Calculate the MD5 hash value of a given string
     *
     * @param input The given string to be hashed
     * @param repeat Rehash in the number of repeat, mainly to deal with the rare
     * 	             situations of an existing hashed value
     * @return The 128 bit or 32 character string
     */
    public static String getMD5(String input,int repeat) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest INSTANCE = MessageDigest.getInstance("MD5");

            for (int i = 0; i < repeat; i++) {
                INSTANCE.update(input.getBytes());
            }

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] digest = INSTANCE.digest();

            // force the value to be positive,
            BigInteger number = new BigInteger(1, digest);

            //Radix parameter decides on which number base (Binary, octal, hex etc) it should return the string
            String hash = number.toString(16);

            return hash;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
