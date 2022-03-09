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
        String cle= getCle("CLE_SYMETRIQUE");
        String messageCl = messageClair.getCorpsMessage();
        String messCh = "";
        char[][] tab = this.remplirTableauChiffrement(messageCl, cle);
        ArrayList<Integer>ordreColonne=this.getOrdreColonee(cle);
        for(int i=0;i<ordreColonne.size();i++){
            for(int j=0;j<tab.length;j++){
                messCh += tab[j][ordreColonne.get(i)];
            }
        }
        Message messageCr = new Message();
        messageCr.setCorpsMessage(messCh);
        return messageCr;
    }
    

    @Override
    public Message dechiffrer(Message messageChiffre) {
        Message mes = new Message(messageChiffre);
        String messDc = "";
        String cle= getCle("CLE_SYMETRIQUE");
        char tab[][] = remplirTableauDechiffrement(messageChiffre.getCorpsMessage(), cle);
        ArrayList<Integer> ordreColonne = this.getOrdreColonee(cle);
        int taillex = cle.length();
        int tailley = messageChiffre.getCorpsMessage().length()/taillex;
        for(int i =0;i<tailley;i++){
            for(int j=0;j<taillex;j++){
                messDc+=tab[ordreColonne.get(j)][i];
            }
        }
        mes.setCorpsMessage(messDc);
        return mes;
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
    
    private char[][] remplirTableauDechiffrement(String message,String cle){
        int taillex=cle.length();
        int tailley = message.length()/taillex;
        char[][]tab = new char[taillex][tailley];
        for (int i=0;i<message.length();i++){
            tab[i%cle.length()][i%cle.length()]=message.charAt(i);
        }
        return tab;
    }
    
    private char bourrage(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int number = this.generateur.nextInt(alphabet.length()+1);
        char bourr = alphabet.charAt(number);
        return bourr;
    }
    
   private ArrayList<Integer> getOrdreColonee(String cle){
        int taille = cle.length();
        int cmpt = 1;
        ArrayList<Couple> couples = new ArrayList<>();
        for (int i=0;i<taille;i++){
            couples.add(new Couple(cle.charAt(i),cmpt));
            cmpt++;
        }
        ArrayList<Integer> result = new ArrayList<>();
        ComparateurCouple c = new ComparateurCouple();
        couples.sort(c);
        for(int i=0;i<couples.size();i++){
            result.add(couples.get(i).getPosition());
        }
        return result;
    }
    
}
