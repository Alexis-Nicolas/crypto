package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 *
 * @author an450821
 */
public class ProtocoleVigenere extends Protocole{

    @Override
    public Message chiffrer(Message messageClair) {
        String cle = this.getCle("CLE_SYMETRIQUE");
        int taille =cle.length();
        String masque = "";
        String message = messageClair.getCorpsMessage().toUpperCase();
        String result = "";
        int fois = message.length()/taille;
        if (message.length()%taille !=0){
            fois++;
        }
        for (int i=0;i<fois;i++){
            masque += cle;
        }
        for (int i=0;i<message.length();i++){
            if(Character.isAlphabetic(message.charAt(i))){
                int LettreM = message.charAt(i);
                int LettreC = masque.charAt(i);
                char chara = (char)('A' + ((LettreM + LettreC)%26));
                result += chara;
            }
            
            else{
                result +=message.charAt(i);
            }
        }
        Message crypt  = new Message(messageClair);
        crypt.setCorpsMessage(result);
        return crypt;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        String cle = this.getCle("CLE_SYMETRIQUE");
        int taille =cle.length();
        String masque = "";
        String message = messageChiffre.getCorpsMessage().toUpperCase();
        String result = "";
        StringBuilder en = new StringBuilder();
        int fois = message.length()/taille;
        if (message.length()%taille !=0){
            fois++;
        }
        for (int i=0;i<fois;i++){
            masque += cle;
        }
        for (int i=0;i<message.length();i++){
            if(Character.isAlphabetic(message.charAt(i))){
                int LettreM = message.charAt(i);
                int LettreC = masque.charAt(i);
                char chara = (char)('A' + ((LettreM - LettreC)%26));
                if(chara<65){
                    chara+=26;
                }
                result += chara;
            }
            
            else{
                result +=message.charAt(i);
            }
        }
        Message crypt  = new Message();
        crypt.setCorpsMessage(result);
        return crypt;
    }
    
}
