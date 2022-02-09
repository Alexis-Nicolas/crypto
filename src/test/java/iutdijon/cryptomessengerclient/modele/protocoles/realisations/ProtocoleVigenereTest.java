package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author simonetma
 */
public class ProtocoleVigenereTest {
    
    public ProtocoleVigenereTest() {
    }

    /**
     * Test of chiffrer method, of class ProtocoleVigenere.
     */
    @Test
    public void testChiffrer() {
        System.out.println("Test du chiffrement de Vigenère");
        
        Protocole protocole = new ProtocoleVigenere();
        protocole.ajouterCle("CLE_SYMETRIQUE", "CHAT");
        Message message = new Message();
        message.setCorpsMessage("Bonjour, Comment ca va ?");
        String result = protocole.chiffrer(message).getCorpsMessage();
        
        String expResult = "DVNCQBR, JOFOLNM JA XH ?";
        
        assertEquals(expResult,result);
    }

    /**
     * Test of dechiffrer method, of class ProtocoleVigenere.
     */
    @Test
    public void testDechiffrer() {
        System.out.println("Test du déchiffrement de Vigenère");
        
        Protocole protocole = new ProtocoleVigenere();
        protocole.ajouterCle("CLE_SYMETRIQUE", "CHAT");
        Message message = new Message();
        message.setCorpsMessage("DVNCQBR, JOFOLNM JA XH ?");
        String result = protocole.dechiffrer(message).getCorpsMessage();
        
        String expResult = "BONJOUR, COMMENT CA VA ?";
        
        assertEquals(expResult,result);
    }
    
}
