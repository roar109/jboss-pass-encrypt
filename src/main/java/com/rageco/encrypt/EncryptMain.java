/*
 * Copyright © 2000 - 2008 24 Hour Fitness. All rights reserved.
 */
package com.rageco.encrypt;


/**
 * EncryptMain represents ...
 *
 * @author <a href="mailto:hector.mendoza@24hourfit.com">hector.mendoza</a>
 * @version $Id$
 * @since 22/12/2014
 *
 * @todo Complete description
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
