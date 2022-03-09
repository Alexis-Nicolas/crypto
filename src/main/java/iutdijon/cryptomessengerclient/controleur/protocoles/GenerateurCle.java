package iutdijon.cryptomessengerclient.controleur.protocoles;

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
    String cle = "";
    int tab[] = null;
    boolean v = false;
       for (int i=0;i<26;i++){
           int position = (int) (Math.random() * (alphabet.length()));
           for (int y=0;y<i;y++){
               if(position!=tab[y]){
                   v=true;
                
                }else{
                   v=false;
               }
           }if (v){
               cle += alphabet.charAt(position);
                tab[i]=position;
           }else{
                i--;
            }
            
        }
       return cle;
}

public static String genererCleRLE(){
    String s="";
    int c = ((int) (Math.random() * (9-2+1))+2);
    s = Integer.toString(c);
    return s;
}
}
