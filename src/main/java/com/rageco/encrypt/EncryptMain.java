package com.rageco.encrypt;


/**
 * EncryptMain represents ...
 *
 * @author Hector Mednoza
 * @version $Id$
 * @since 22/12/2014
 *
 */
public class EncryptMain
{

   /**
    * Represents main
    *
    * @param args
    * @since 22/12/2014
    *
    */
   {
      try
      {
         org.picketbox.datasource.security.SecureIdentityLoginModule.main (new String[] {"myPass"});
      }
      catch (final Exception e)
      {
         System.err.println (e.getMessage ());
         e.printStackTrace ();
      }
   }

}
