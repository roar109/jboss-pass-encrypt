package org.rage.jboss.utils;


/**
 * EncryptMain represents ...
 *
 * @author Hector Mendoza
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
    *
    * @since 22/12/2014
    *
    */
   public static void main (final String[] args)
   {
      try
      {
         // output is to the console
         org.picketbox.datasource.security.SecureIdentityLoginModule.main (new String[] {args[0]});
      }
      catch (final Exception e)
      {
         System.err.println (e.getMessage ());
         e.printStackTrace ();
      }
   }

}
