package iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition;

import iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition.Couple;
import java.util.Comparator;

/**
 *
 * @author an450821
 */
public class ComparateurCouple implements Comparator<Couple>{


    @Override
    public int compare(Couple arg0, Couple arg1) {
        int retour=-1;
        if(arg0.getCaractere()>arg1.getCaractere()){
            retour=1;
        }
        else if(arg0.getCaractere()==arg1.getCaractere()){
            if(arg0.getPosition()>arg1.getPosition()){
                retour=1;
            }
        }
        return retour;
    }
    
}
