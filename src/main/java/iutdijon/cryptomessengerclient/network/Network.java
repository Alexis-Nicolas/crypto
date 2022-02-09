package iutdijon.cryptomessengerclient.network;

import iutdijon.cryptomessengerclient.modele.messages.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * TODO Classe à coder par l'étudiant (donner le squelette)
 * @author simonetma
 */
public class Network {
    private static Network instance;
    private Socket socket;
    private BufferedReader fluxEntrant;
    private PrintWriter fluxSortant;
    private boolean connecte;
    
    private Network() {
        this.connecte = false;
    }
    
    /**
     * Getter du singleton
     * @return l'instance
     */
    private static Network get() {
        if (Network.instance==null){
            Network.instance = new Network();
        }
        return Network.instance;
    }
    
    /**
     * Création de la socket
     * @throws IOException 
     */
    private void creationSocket() throws IOException {
        get().socket = new Socket("127.0.0.1",1234);
    }
    
    /**
     * Création des flux
     * @throws IOException 
     */
    private void creationFlux() throws IOException {
        InputStreamReader isReader= new InputStreamReader(get().socket.getInputStream());
        get().fluxEntrant = new BufferedReader(isReader);
        get().fluxSortant = new PrintWriter(get().socket.getOutputStream(),true);
    }
    
    /**
     * Connexion
     * @throws IOException 
     */
    public static void connexion() throws Exception {
        
        get().creationSocket();
        get().creationFlux();
        if (get().recevoirLigne().equals("Saisir votre NOM")){
            get().envoyerLigne(Settings.getNomUtilisateur());
        }
        if (get().recevoirLigne().equals("Connexion établie")){
            get().connecte=true;
        }
        
    }
    
    /**
     * Envoyer une ligne
     * @param message le message à envoyer 
     */
    private static void envoyerLigne(String message) {
        System.out.println(">"+message);
        instance.fluxSortant.println(message);
    }
    
    /**
     * Envoyer un message
     * @param message le message à envoyer au serveur
     */
    public static void envoyer(Message message) {
        if (message != null){
            System.out.println(">ENVOI");
            get().envoyerLigne("ENVOI");
            get().envoyerLigne(message.getDestinataire());
            get().envoyerLigne(message.getProtocoleUtilise().toString());
            get().envoyerLigne(message.getCorpsMessage().replaceAll("m", Character.toString(31)));
        }
    }
    
    /**
     * Recevoir un message
     * @return le message reçu
     * @throws IOException 
     */
    private static String recevoirLigne() throws Exception {
        String message = get().fluxEntrant.readLine();
        System.out.println("<"+message);
        return message;
    }
    
    public static void recevoir(Message message) {
        get().envoyerLigne("RECEPTION"); 
        try{
        message.setExpediteur(recevoirLigne());
        message.setDestinataire(Settings.getNomUtilisateur());
        message.setProtocoleUtilise(recevoirLigne());
        message.setCorpsMessage(recevoirLigne().replaceAll(Character.toString(31), "\n"));
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
    } 
    
    /**
     * Le client est-il connecté ?
     * @return est-ce que le client est connecté ou non.
     */
    public static boolean estConnecte() {
        boolean b = false ;
        if(get().connecte){
            b = true;
        }
        return b;
    }
}
