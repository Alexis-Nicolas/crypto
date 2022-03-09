package iutdijon.cryptomessengerclient.modele.protocoles.realisations;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import iutdijon.cryptomessengerclient.modele.protocoles.Protocole;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.huffman.ComparateurNoeuds;
import iutdijon.cryptomessengerclient.modele.protocoles.realisations.huffman.Noeud;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author simonetma
 */
public class ProtocoleHuffman extends Protocole {

    //ETAPE 1 -- Compter les occurences de chaque caractère
    public HashMap<Character,Integer> compterCaracteres(String corpMessage) {
        HashMap<Character,Integer> c = new HashMap<Character,Integer>();
        for(int i=0;i<corpMessage.length();i++){
            Integer valeur = c.get(corpMessage.charAt(i));
            if(valeur ==null){
                c.put(corpMessage.charAt(i),1);
            }
            else{
                c.put(corpMessage.charAt(i),+1);
            }
        }
        return c;
    }
    
    //ETAPE 2 -- Création de l'arbre
    public PriorityQueue<Noeud> creationListeNoeuds(HashMap<Character,Integer> mapComptageCaracteres) {
        ComparateurNoeuds compN = new ComparateurNoeuds();
        PriorityQueue<Noeud> p = new PriorityQueue<Noeud>(compN);
        for(Map.Entry<Character, Integer> mapEntry: mapComptageCaracteres.entrySet()){
            p.add(new Noeud(String.valueOf(mapEntry.getKey()),mapEntry.getValue()));
        }
        return p;
    }
    
    public Noeud creationArbre(HashMap<Character,Integer> mapComptageCaracteres)  {
        PriorityQueue<Noeud> p = this.creationListeNoeuds(mapComptageCaracteres);
        while(p.size()>1){
            Noeud n1= p.element();
            p.remove(n1);
            Noeud n2 = p.element();
            p.remove(n2);
            Noeud n3 = new Noeud((n1.getNom()+n2.getNom()),(n1.getNombreOccurences()+n2.getNombreOccurences()));
            n3.ajouterFils(n1);
            n3.ajouterFils(n2);
            p.add(n3);
        }
        return p.element();
    }
    
    //ETAPE 3 -- Création du dictionnaire
    public HashMap<Character,String> creationDictionnaire(Noeud racine) {
        HashMap<Character,String> dictionnaire = new HashMap<Character,String>();
        racine.calculCode(dictionnaire);
        return dictionnaire;
    }
    
    //ETAPE 4 - Chiffrement du message
    private String chiffrerMessage(String message,HashMap<Character,String> dictionnaire) {
        String s = "";
        for(int i=0;i<message.length();i++){
            s+=dictionnaire.get(message.charAt(i));
        }
        return s;
    }
    
    
    @Override
    public Message chiffrer(Message messageClair) {
        //TODO
        //On récupère le corp du message comme une chaine de caractères
        String s = messageClair.getCorpsMessage();
        //Etape 1 - On compe les caractères
        HashMap<Character,Integer> map = compterCaracteres(s);
        //Etape 2 - On crée l'arbre
        Noeud n = creationArbre(map);
        //Etape 3 - On crée le dictionnaire
        HashMap<Character,String> dictionnaire = creationDictionnaire(n);
        //Etape 4 - Encodage avec le dictionnaire
        String sc = chiffrerMessage(s, dictionnaire);
        //On renvoit le message compressé
        Message messComp = new Message(messageClair);
        messComp.setCorpsMessage(sc);
        return messComp;
    }

    @Override
    public Message dechiffrer(Message messageChiffre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
