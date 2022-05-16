/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import de.mkammerer.argon2.Argon2Factory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author MSI
 */
public class CryptWithMD5 {
   private static MessageDigest md;

   public static String cryptWithMD5(String pass){
      
    
        

        byte[] msg = pass.getBytes();

        byte [] hash = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(msg);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder strBuilder = new StringBuilder();
        for(byte b:hash)
        {
            strBuilder.append(String.format("%02x", b));
        }
        String strHash = strBuilder.toString();
        System.out.println("The MD5 hash: "+strHash);
        return strHash;
   /* try {
        md = MessageDigest.getInstance("MD5");
        byte[] passBytes = pass.getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(CryptWithMD5.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;

*/
   }
   
   public static String mdpconvert(String p) {
       
    return Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id).hash(4, 65536, 1, p);


       
    }
   
   
               
     
    
}
