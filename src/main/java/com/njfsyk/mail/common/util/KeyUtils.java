package com.njfsyk.mail.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KeyUtils {
	
   private static String key; 
   
   static {
	   Properties p = new Properties();
	   
	   try {
		   InputStream inputStream = KeyUtils.class.getClassLoader().getResourceAsStream("key.properties");
		   p.load(inputStream);
	} catch (IOException e) {
		e.printStackTrace();
	}
	   
	   key = p.getProperty("KEY");
   }
   
   public static String getKey(){
	   return key;
   }
   
   public static void main(String[] args)
	{
		System.out.println(KeyUtils.getKey());
	}
}
