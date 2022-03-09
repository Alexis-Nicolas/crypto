package iutdijon.cryptomessengerclient.controleur.protocoles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author alexi
 */
public class GenerateurCle {
    
    public static String genererCleCesar(){
        int i = 0;
        i= (int) (Math.random() * ( 25 ));
        return Integer.toString(i);
    }
    
    public static String genererCleTransposition(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String cle = "";
        int taille = 5+(int) (Math.random() * ( 20 -5)+1);
        for (int i=0;i<taille;i++){
            int position = (int) (Math.random() * (alphabet.length()));
            cle += alphabet.charAt(position);
        }
        return cle;
    }

    public static String genererCleVigenere(){
         String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
         String cle = "";
          int taille = 5+(int) (Math.random() * ( 20 -5)+1);
          for (int i=0;i<taille;i++){
                int position = (int) (Math.random() * (alphabet.length()));
                cle += alphabet.charAt(position);
            }
            return cle;
    }
    public static String genererCleSubstitution(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String resultat = "";

        List<String> melange = Arrays.asList(alphabet.split(""));
        Collections.shuffle(melange);

        for (String character : melange){
            resultat += character;
        }
        return resultat;
    }

    public static String genererCleRLE(){
        String s="";
        int c = 2+(int) (Math.random() * ((9-2)+1));
        s = Integer.toString(c);
        return s;
    }
}
