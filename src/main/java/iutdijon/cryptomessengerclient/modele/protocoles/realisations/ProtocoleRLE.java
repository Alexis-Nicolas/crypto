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
        String mess = messageClair.getCorpsMessage();
        int cle = Integer.valueOf(getCle("CLE_COMPRESSION"));
        String messComp="";
        char sauvChar = mess.charAt(0);
        int nbrRep =1;
        int taille =1;
        int valeurcle=cle;
        while(valeurcle/10>=1){
            valeurcle=valeurcle/10;
            taille++;
        }
        
        if(mess.charAt(mess.length()-1)=='A'){
            mess+='B';
        }
        else{
            mess+='A';
        }
        String zero = "";
        for(int i=1;i<=mess.length()-1;i++){
            if(sauvChar==mess.charAt(i)&&nbrRep<cle){
                nbrRep++;
            }
            else{
                System.out.println(nbrRep);
                int tailleNbRep = 1;
                int nbrRepBis = nbrRep;
                while(nbrRepBis/10>=1){
                    tailleNbRep++;
                    nbrRepBis/=10;
                }
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
        Message res = new Message(messageClair);
        res.setCorpsMessage(messComp);
        return res;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        String mess = messageChiffre.getCorpsMessage();
        int c = Integer.valueOf(this.getCle("CLE_COMPRESSION"));
        String messDecomp = "";
        int nbrRep;
        int taille =1;
        while(c/10>=1){
            c=c/10;
            taille++;
        }
        for(int i=0;i<mess.length();i+=taille+1){
            String s = mess.substring(i, i+(taille-1));
            
            nbrRep  = Integer.valueOf(s);
            System.out.println("s "+nbrRep);
            for(int j=0;j<nbrRep;j++){
                messDecomp+=mess.charAt(i+taille);
            }
        }
        Message mesDec = new Message(messageChiffre);
        mesDec.setCorpsMessage(messDecomp);
        return mesDec;
    }
    
}
