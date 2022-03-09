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
        Message reponse= messageClair;
        String message = reponse.getCorpsMessage();
        StringBuilder en = new StringBuilder();   
        boolean v = false;
        for(char c:message.toCharArray()){
            if(Character.isLetter(c)){
                int i =Character.compare(c,A);
                if((i>=0)&&(Character.compare(c,Z)<=0)){
                    v = true;
                    for(int y=0;y<26;y++){
                       if (c==alphabet.charAt(y)) {
                           int position = y;
                           en.append(cle.charAt(y));
                       }
                    }
                }else if((Character.compare(c,z)<=0)&&(Character.compare(c,a)>=0)){
                    v=false;
                    for(int y=0;y<26;y++){
                        c=Character.toUpperCase(c);
                        if (c==alphabet.charAt(y)) {
                           int position = y;
                           stockage = cle.charAt(y);
                           
                           en.append(Character.toLowerCase(stockage));
                       }
                    }
                }
                    
                }else{
                en.append(c);
            }
        }
        reponse.setCorpsMessage(en.toString());
        return reponse;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char A = 'A';
        char Z = 'Z';
        char a = 'a';
        char z = 'z';
        String cle = this.getCle("CLE_SYMETRIQUE");
        char stockage ;
        String resultat ="" ;
        Message reponse= messageChiffre;
        String message = reponse.getCorpsMessage();
        StringBuilder en = new StringBuilder();   
        for(char c:message.toCharArray()){
            if(Character.isLetter(c)){
                int i =Character.compare(c,A);
                if((i>=0)&&(Character.compare(c,Z)<=0)){
                    
                    for(int y=0;y<cle.length();y++){
                       if (c==cle.charAt(y)) {
                           int position = y;
                           en.append(alphabet.charAt(y));
                       }
                    }
                }else if((Character.compare(c,z)<=0)&&(Character.compare(c,a)>=0)){
                    
                    for(int y=0;y<cle.length();y++){
                        c=Character.toUpperCase(c);
                        if (c==cle.charAt(y)) {
                           int position = y;
                           stockage = alphabet.charAt(y);
                           
                           en.append(Character.toLowerCase(stockage));
                       }
                    }
                }
                    
                }else{
                en.append(c);
            }
        }
        reponse.setCorpsMessage(en.toString());
        return reponse;
    }
    
}
