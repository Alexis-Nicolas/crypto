package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import static java.lang.Integer.parseInt;

/**
 *
 * @author alexi
 */
public class ProtocoleCesar extends Protocole {

    @Override
    public Message chiffrer(Message messageClair) {
        //recupération de la cle et du message
        String cle = this.getCle("CLE_SYMETRIQUE");
        Message reponse= messageClair;
        String message = reponse.getCorpsMessage();
        StringBuilder en = new StringBuilder();
        
        //normalisation de la cle en modulo 26
        int moduloCle = Integer.valueOf(cle);
        moduloCle = moduloCle % 26;
        
        //parcours de la chaine de caractere
        for(char c:message.toCharArray()){
            //si c'est une lettre 
            if(Character.isLetter(c)){
                int val = c;
                //Pour une lettre minuscule
                if ((val <= 90 )&&( val >= 65))
            {
                val = val + moduloCle;
                
                //si apres le changement le caractere depasse le z on revient à a
                    if(val > 90){
                        val = val - 26;
                    } 
            //Pour une lettre majuscule
            }else if(val <= 122 && val >= 97)
            {
                val = val + moduloCle;
                if(val > 122){
                    
                    //si apres le changement le caractere depasse le Z on revient à A
                        val = val - 26;
                 } 
            }
                //ajouté la valeur val à en
                en.append((char)val);
                
                //ajoute à en un caractere qui n'est pas une lettre
            }else {
                en.append(c);
            }
        }
        //creation et envoie du code chiffré
        reponse.setCorpsMessage(en.toString());
        return reponse;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        //recupération de la cle et du message
        String cle = this.getCle("CLE_SYMETRIQUE");
        Message MC= messageChiffre;
        String message = MC.getCorpsMessage();
         StringBuilder en = new StringBuilder();
         
         //normalisation de la cle en modulo 26
        int moduloCle = Integer.valueOf(cle);
        moduloCle = moduloCle % 26;
        
        //parcours de la chaine de caractere 
        for(char c:message.toCharArray()){
            //si c'est une lettre 
            if(Character.isLetter(c)){
                int val = c;
                c = (char) (val-moduloCle);
                en.append(c);
            }
        }
        MC.setCorpsMessage(en.toString());
        return MC;
    }
        
    
}
