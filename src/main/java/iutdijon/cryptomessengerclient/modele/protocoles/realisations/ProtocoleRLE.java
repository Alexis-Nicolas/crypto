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
 * @author ewanr
 */
public class ProtocoleRLE extends Protocole{

    @Override
    public Message chiffrer(Message messageClair) {
        //recupération de la cle et du message
        String mess = messageClair.getCorpsMessage();
        int cle = Integer.valueOf(getCle("CLE_COMPRESSION"));
        
        //initialisation des variables
        String messComp="";
        char sauvChar = mess.charAt(0);
        int nbrRep =1;
        int taille =1;
        int valeurcle=cle;
        
        //nombre de chiffre de la cle
        while(valeurcle/10>=1){
            valeurcle=valeurcle/10;
            taille++;
        }
        //
        if(mess.charAt(mess.length()-1)=='A'){
            mess+='B';
        }
        else{
            mess+='A';
        }
        //compte le nombre de repetition d'une lettre
        String zero = "";
        for(int i=1;i<=mess.length()-1;i++){
            if(sauvChar==mess.charAt(i)&&nbrRep<cle){
                nbrRep++;
            }
            else{
                int tailleNbRep = 1;
                int nbrRepBis = nbrRep;
                while(nbrRepBis/10>=1){
                    tailleNbRep++;
                    nbrRepBis/=10;
                }
                //ajout de 0 pour avoir le meme nombre devant la lettre
                if(tailleNbRep<taille){
                    for(int j=0;j<taille-tailleNbRep;j++){
                        zero+="0";
                    }
                }
                messComp+=zero+String.valueOf(nbrRep)+sauvChar;
                
                sauvChar=mess.charAt(i);
                nbrRep=1;
                zero ="";
            }
        }
        //creation et envoie du code compressé
        Message res = new Message(messageClair);
        res.setCorpsMessage(messComp);
        return res;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        //recupération de la cle et du message
        String mess = messageChiffre.getCorpsMessage();
        int c = Integer.valueOf(this.getCle("CLE_COMPRESSION"));
        
        //initialisation des variables
        String messDecomp = "";
        int nbrRep;
        int taille =1;
        
        //nombre de chiffre de la cle
        while(c/10>=1){
            c=c/10;
            taille++;
        }
        //parcours du message
        for(int i=0;i<mess.length();i+=taille+1){
            //recupération de la taille de la repetition
            String s = mess.substring(i, i+taille);
            nbrRep  = Integer.valueOf(s);
            //écriture du message clair
            for(int j=0;j<nbrRep;j++){
                messDecomp+=mess.charAt(i+taille);
            }
        }
        //creation et envoie du code 
        Message mesDec = new Message(messageChiffre);
        mesDec.setCorpsMessage(messDecomp);
        return mesDec;
    }
    
}
