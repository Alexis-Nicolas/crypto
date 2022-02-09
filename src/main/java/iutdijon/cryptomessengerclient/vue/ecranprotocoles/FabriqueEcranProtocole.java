package iutdijon.cryptomessengerclient.vue.ecranprotocoles;

import iutdijon.cryptomessengerclient.modele.protocoles.NomProtocole;
import iutdijon.cryptomessengerclient.vue.ImagesManager;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecranbrut.EcranBrutEnvoi;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecranbrut.EcranBrutReception;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecransymetrique.EcranSymetriqueEnvoi;
import iutdijon.cryptomessengerclient.vue.ecranprotocoles.ecrans.ecransymetrique.EcranSymetriqueReception;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author simonetma
 */
public class FabriqueEcranProtocole {
    
    public static void create(NomProtocole protocole, String nomEcran, Scene origine) {
        //Initialisation
        Stage stage = new Stage();
        stage.setTitle("Protocole : "+protocole.getNom());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(origine.getWindow());
        stage.setResizable(false);
        stage.getIcons().add(ImagesManager.getImage("Icon"));
        
        //Choix de la scène
        switch(protocole) {
            case BRUT: 
                if(nomEcran.equals("ENVOI"))  stage.setScene(new EcranBrutEnvoi()); 
                if(nomEcran.equals("RECEPTION")) stage.setScene(new EcranBrutReception());
                break;
            case CESAR:
                if(nomEcran.equals("ENVOI")) stage.setScene(new EcranSymetriqueEnvoi(protocole));
                if(nomEcran.equals("RECEPTION")) stage.setScene(new EcranSymetriqueReception(protocole));
		break;
            case TRANSPOSITION:
                if(nomEcran.equals("ENVOI")) stage.setScene(new EcranSymetriqueEnvoi(protocole));
                if(nomEcran.equals("RECEPTION")) stage.setScene(new EcranSymetriqueReception(protocole));
                break; 
            case VIGENERE:
                if(nomEcran.equals("ENVOI")) stage.setScene(new EcranSymetriqueEnvoi(protocole));
                if(nomEcran.equals("RECEPTION")) stage.setScene(new EcranSymetriqueReception(protocole));
                break;
                
                
        }
        
        stage.show();
    }
    
}
