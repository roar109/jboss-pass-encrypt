package com.rageco.encrypt;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.math.BigInteger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author hector.mendoza 12/03/2014
 * */
public class DecryptModule
{


   /**
    * Represents main
    *
    * @param args
    * @since 08/12/2014
    *
    */
   public static void main (final String[] args)
   {
      try
      {
         // put here your string
         System.out.println (decode (args[0]));
      }
      catch (final Exception e)
      {
         System.err.println (e.getMessage ());
         e.printStackTrace ();
      }
   }


   /**
    * Taken from <a href=
    * "https://source.jboss.org/browse/PicketBox/trunk/security-jboss-sx/jbosssx/src/main/java/org/picketbox/datasource/security/SecureIdentityLoginModule.java?r=276"
    * >Jboss api</a>
    * */
   private static char[] decode (final String secret) throws NoSuchPaddingException, NoSuchAlgorithmException,
         InvalidKeyException, BadPaddingException, IllegalBlockSizeException
   {
      final byte[] kbytes = "jaas is the way".getBytes ();
      final SecretKeySpec key = new SecretKeySpec (kbytes, "Blowfish");

      final BigInteger n = new BigInteger (secret, 16);
      byte[] encoding = n.toByteArray ();

      // SECURITY-344: fix leading zeros
      if ( (encoding.length % 8) != 0)
      {
         final int length = encoding.length;
         final int newLength = ( (length / 8) + 1) * 8;
         final int pad = newLength - length; // number of leading zeros
         final byte[] old = encoding;
         encoding = new byte[newLength];
         for (int i = old.length - 1; i >= 0; i--)
         {
            encoding[i + pad] = old[i];
         }
         // SECURITY-563: handle negative numbers
         if (n.signum () == -1)
         {
            for (int i = 0; i < (newLength - length); i++)
            {
               encoding[i] = (byte) -1;
            }
         }
      }

      final Cipher cipher = Cipher.getInstance ("Blowfish");
      cipher.init (Cipher.DECRYPT_MODE, key);
      final byte[] decode = cipher.doFinal (encoding);
      return new String (decode).toCharArray ();
   }
}
