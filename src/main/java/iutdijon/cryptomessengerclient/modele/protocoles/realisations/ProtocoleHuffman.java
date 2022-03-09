package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.huffman.Noeud;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author simonetma
 */
public class ProtocoleHuffman extends Protocole {

    //ETAPE 1 -- Compter les occurences de chaque caractère
    public HashMap<Character,Integer> compterCaracteres(String corpMessage) {
        HashMap<Character,Integer> c = new HashMap<Character,Integer>();
        
        return c;
    }
    
    //ETAPE 2 -- Création de l'arbre
    public PriorityQueue<Noeud> creationListeNoeuds(HashMap<Character,Integer> mapComptageCaracteres) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public Noeud creationArbre(HashMap<Character,Integer> mapComptageCaracteres) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    //ETAPE 3 -- Création du dictionnaire
    public HashMap<Character,String> creationDictionnaire(Noeud racine) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    //ETAPE 4 - Chiffrement du message
    private String chiffrerMessage(String message,HashMap<Character,String> dictionnaire) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    @Override
    public Message chiffrer(Message messageClair) {
        //TODO
        //On récupère le corp du message comme une chaine de caractères
        
        //Etape 1 - On compe les caractères
        //Etape 2 - On crée l'arbre
        //Etape 3 - On crée le dictionnaire
        //Etape 4 - Encodage avec le dictionnaire
        
        //On renvoit le message compressé
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
