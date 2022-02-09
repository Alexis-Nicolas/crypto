package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition.ComparateurCouple;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition.Couple;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 *
 * @author an450821
 */
public class ProtocoleTransposition extends Protocole{

    private SecureRandom generateur;
    
    @Override
    public Message chiffrer(Message messageClair) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private char[][] remplirTableauChiffrement(String message,String cle){
        ByteBuffer b = ByteBuffer.allocate(4) ;
        b.putInt((cle+message).hashCode()) ;
        this.generateur = new SecureRandom (b.array()) ;
        
        int taillex = cle.length();
        int tailley = message.length()/taillex;
        if(message.length()%taillex!=0){
            tailley++;
        }
        char[][] tab = new char[tailley][taillex];
        int cmpt = 0;
        for (int i=0;i<tailley;i++){
            for (int j=0;j<taillex;j++){
                if (cmpt == message.length()){
                    tab[i][j]=this.bourrage();
                }else{
                    tab[i][j] = message.charAt(cmpt);
                    cmpt++;
                }
            }
        }
        return tab;
    }
    
    private char bourrage(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int number = this.generateur.nextInt(alphabet.length()+1);
        char bourr = alphabet.charAt(number);
        return bourr;
    }
    
  /* private ArrayList<Integer> getOrdreColonee(String cle){
        int taille = cle.length();
        int cmpt = 1;
        ArrayList<Couple> couples = new ArrayList<>();
        for (int i=0;i<taille;i++){
            couples.add(new Couple(cle.charAt(i),cmpt));
            cmpt++;
        }
        ArrayList<Integer> result = new ArrayList<>();
        ComparateurCouple c = new ComparateurCouple();
        result.sort(c);
        return result;
    }*/
    
}
