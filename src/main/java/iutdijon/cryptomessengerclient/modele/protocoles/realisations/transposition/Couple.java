package iutdijon.cryptomessengerclient.modele.protocoles.realisations.transposition;

/**
 *
 * @author an450821
 */
public class Couple {
    private char caractere;
    private  int position;
    
    public Couple(char caractere, int position){
        this.caractere=caractere;
        this.position=position;
    }
    
    public void setCaractere (char c){
        this.caractere=c;
    }
    
    public char getCaractere(){
        return this.caractere;
    }
    
    public void setPosition(int pos){
        this.position=pos;
    }
    
    public int getPosition(){
        return this.position;
    }
}
