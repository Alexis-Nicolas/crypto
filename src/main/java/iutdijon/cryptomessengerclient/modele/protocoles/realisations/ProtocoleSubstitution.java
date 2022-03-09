/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 *
 * @author alexi
 */
public class ProtocoleSubstitution extends Protocole{

    @Override
    public Message chiffrer(Message messageClair) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char A = 'A';
        char Z = 'Z';
        char a = 'a';
        char z = 'z';
        String cle = this.getCle("CLE_SYMETRIQUE");
        char stockage ;
        String resultat ="" ;
        Message reponse= messageClair;
        String message = reponse.getCorpsMessage();
        StringBuilder en = new StringBuilder();   
        boolean v = false;
        for(char c:message.toCharArray()){
            if(Character.isLetter(c)){
                int i =Character.compare(c,A);
                if((i>=0)&&(Character.compare(c,Z)<=0)){
                    v = true;
                    for(int y=0;y<=alphabet.length();y++){
                       if (c==alphabet.charAt(y)) {
                           int position = y;
                           resultat += cle.charAt(y);
                       }
                    }
                }else if((Character.compare(c,z)<=0)&&(Character.compare(c,a)>=0)){
                    v=false;
                    for(int y=0;y<=alphabet.length();y++){
                        c=Character.toUpperCase(c);
                        if (c==alphabet.charAt(y)) {
                           int position = y;
                           stockage = cle.charAt(y);
                           
                           resultat += Character.toLowerCase(stockage);
                       }
                    }
                }
                    
                }else{
                resultat+=c;
            }
        }
        reponse.setCorpsMessage(en.toString());
        return reponse;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
