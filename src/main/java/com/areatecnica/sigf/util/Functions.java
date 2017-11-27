package com.areatecnica.sigf.util;

/**
 *
 * @author Omer Faruk KURT 
 * @e-mail kurtomerfaruk@gmail.com
 * @blog http://kurtomerfaruk.com
 */
public class Functions {

    public Functions() {
    }
   
     public static String classNameParts(String name) {
        String[] parts = name.split("models.");
        return parts[1];
    }
     
     public static String firstUpperCase(String text){
         text= text.replace(text.substring(0, 1), text.substring(0, 1).toUpperCase());
         return text.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");
     }
}
