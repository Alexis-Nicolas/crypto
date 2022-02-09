package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;

/**
 *
 * @author alexi
 */
public class ProtocoleBrut extends Protocole{

    @Override
    public Message chiffrer(Message messageClair) {
        return messageClair; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        return messageChiffre; //To change body of generated methods, choose Tools | Templates.
    }
    
}
