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
        //initialisation
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char A = 'A';
        char Z = 'Z';
        char a = 'a';
        char z = 'z';
        char stockage ;
        String resultat ="" ;
        
        //recuperation de la cle et le message
        String cle = this.getCle("CLE_SYMETRIQUE");
        Message reponse= messageClair;
        String message = reponse.getCorpsMessage();
        
        //parcours de la chaine de caractere
        for(char c:message.toCharArray()){
            //une lettre ?
            if(Character.isLetter(c)){
                //si c'est une majuscule entre A et Z 
                int i =Character.compare(c,A);
                if((i>=0)&&(Character.compare(c,Z)<=0)){
                    //on cherche la lettre
                    for(int y=0;y<alphabet.length();y++){
                       if (c==alphabet.charAt(y)) {
                           int position = y;
                           resultat += cle.charAt(y);
                       }
                    }
                    //si c'est une minuscule entre a et z
                }else if((Character.compare(c,z)<=0)&&(Character.compare(c,a)>=0)){
                    for(int y=0;y<alphabet.length();y++){
                        //passe en majuscule pour convenir à la cle
                        c=Character.toUpperCase(c);
                         //on cherche la lettre
                        if (c==alphabet.charAt(y)) {
                           int position = y;
                           stockage = cle.charAt(y);
                           
                           resultat += Character.toLowerCase(stockage);
                       }
                    }
                }
                //si ce n'est pas un caractere    
                }else{
                resultat+=c;
            }
        }
        //creation et envoie du code chiffré
        reponse.setCorpsMessage(resultat.toString());
        return reponse;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        //initialisation
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char A = 'A';
        char Z = 'Z';
        char a = 'a';
        char z = 'z';
        char stockage ;
        String resultat ="" ;
        //recuperation de la cle et le message
        String cle = this.getCle("CLE_SYMETRIQUE");
        Message reponse= messageChiffre;
        String message = reponse.getCorpsMessage();
        StringBuilder en = new StringBuilder();   
        //parcours de la chaine de caractere
        for(char c:message.toCharArray()){
            //une lettre ?
            if(Character.isLetter(c)){
                //si c'est une majuscule entre A et Z 
                int i =Character.compare(c,A);
                if((i>=0)&&(Character.compare(c,Z)<=0)){
                     //on cherche la lettre
                    for(int y=0;y<cle.length();y++){
                       if (c==cle.charAt(y)) {
                           int position = y;
                           resultat += alphabet.charAt(y);
                       }
                    }
                    //si c'est une minuscule entre a et z
                }else if((Character.compare(c,z)<=0)&&(Character.compare(c,a)>=0)){
                    for(int y=0;y<cle.length();y++){
                        //passe en majuscule pour convenir à la cle
                        c=Character.toUpperCase(c);
                        if (c==cle.charAt(y)) {
                           int position = y;
                           stockage = alphabet.charAt(y);
                           
                           resultat += Character.toLowerCase(stockage);
                       }
                    }
                }
                //si ce n'est pas un caractere     
                }else{
                resultat+=c;
            }
        }
        //creation et envoie du code chiffré
        reponse.setCorpsMessage(resultat.toString());
        return reponse;
    }
    
}
