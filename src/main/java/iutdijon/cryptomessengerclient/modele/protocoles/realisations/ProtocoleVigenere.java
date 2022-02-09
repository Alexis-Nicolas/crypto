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
		//on récupère la clé vigenere
        String cle = this.getCle("CLE_SYMETRIQUE");
		//on récupère la taille de la clé
        int taille =cle.length();
        String masque = "";
		//On récupère le message à chiffrer en mettant tout les caractères en majuscule
        String message = messageClair.getCorpsMessage().toUpperCase();
        String result = "";
		//Le nombre de fois que la clé devra être répétée 
        int fois = message.length()/taille;
		//S'il reste des caractères, on repète une fois la clé
        if (message.length()%taille !=0){
            fois++;
        }
		//on créé la chaine de caractère contenant la clé répétée
        for (int i=0;i<fois;i++){
            masque += cle;
        }
		//On chiffre le message
        for (int i=0;i<message.length();i++){
			//On vérifie si le caractère est bien une lettre
            if(Character.isAlphabetic(message.charAt(i))){
				//Le caractère prend la lettre du message 
                int LettreM = message.charAt(i);
				//Le caractère prend la lettre de la clé
                int LettreC = masque.charAt(i);
				//Le caractère est décalé avec la clé
                char chara = (char)('A' + ((LettreM + LettreC)%26));
                result += chara;
            }
            //Si le caractère n'est pas une lettre, on ne la chiffre pas
            else{
                result +=message.charAt(i);
            }
        }
		//On créé un nouveau message qu'on retourne
        Message crypt  = new Message(messageClair);
        crypt.setCorpsMessage(result);
        return crypt;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
		//on récupère la clé vigenere
        String cle = this.getCle("CLE_SYMETRIQUE");
		//on récupère la taille de la clé
        int taille =cle.length();
        String masque = "";
		//On récupère le message à chiffrer en mettant tout les caractères en majuscule
        String message = messageChiffre.getCorpsMessage().toUpperCase();
        String result = "";
		//Le nombre de fois que la clé devra être répétée 
        StringBuilder en = new StringBuilder();
        int fois = message.length()/taille;
		//S'il reste des caractères, on repète une fois la clé
        if (message.length()%taille !=0){
            fois++;
        }
		//on créé la chaine de caractère contenant la clé répétée
        for (int i=0;i<fois;i++){
            masque += cle;
        }
		//On déchiffre le message
        for (int i=0;i<message.length();i++){
			//On vérifie si le caractère est bien une lettre
            if(Character.isAlphabetic(message.charAt(i))){
				//Le caractère prend la lettre du message 
                int LettreM = message.charAt(i);
				//Le caractère prend la lettre de la clé
                int LettreC = masque.charAt(i);
				//Le caractère est décalé avec la clé
                char chara = (char)('A' + ((LettreM - LettreC)%26));
                if(chara<65){
                    chara+=26;
                }
                result += chara;
            }
            //Si le caractère n'est pas une lettre, on ne la déchiffre pas
            else{
                result +=message.charAt(i);
            }
        }
		//On créé un nouveau message qu'on retourne
        Message crypt  = new Message();
        crypt.setCorpsMessage(result);
        return crypt;
    }
    
}
