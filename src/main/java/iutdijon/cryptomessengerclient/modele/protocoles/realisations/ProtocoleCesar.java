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
        String cle = this.getCle("CLE_SYMETRIQUE");
        Message reponse= messageClair;
        String message = reponse.getCorpsMessage();
        StringBuilder en = new StringBuilder();
        
        for(char c:message.toCharArray()){
            if(Character.isLetter(c)){
                int val = c;
                c = (char) ((val+parseInt(cle)%26));
                en.append(c);
            }/*else {
                en.append(c);
            }*/
        }
        reponse.setCorpsMessage(en.toString());
        return reponse;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
         String cle = this.getCle("CLE_SYMETRIQUE");
        Message MC= messageChiffre;
        String message = MC.getCorpsMessage();
         StringBuilder en = new StringBuilder();
        for(char c:message.toCharArray()){
            if(Character.isLetter(c)){
                int val = c;
                c = (char) (val-parseInt(cle));
                en.append(c);
            }
        }
        MC.setCorpsMessage(en.toString());
        return MC;
    }
        
    
}
