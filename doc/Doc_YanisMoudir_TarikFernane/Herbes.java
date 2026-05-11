//Tarik Fernane & Yanis Moudir 
/**
 * Classe abstraite representant une herbe, une ressource vegetale 
 * pouvant etre placee sur le terrain de la simulation.
 * 
 * Cette classe sert de base aux differents types d'herbes concretes 
 * (par exemple Herbiers). Toutes les herbes partagent une meme probabilite 
 * de generation (croissance), et chaque sous-classe doit definir sa propre 
 * methode d'evolution.
 */
public abstract class Herbes extends Ressource{
    /**
     * Probabilite de generation (croissance) commune a toutes les herbes.
     * Definie de maniere statique pour etre partagee par toutes les instances.
     */
    protected static double probGeneration=0.3;
    /**
     * Construit une nouvelle herbe avec le type et la quantite specifies.
     * @param type type de l'herbe 
     * @param quantite quantité initial de l'herbe .
     */
    public Herbes(String type,int quantite){
        super(type,quantite);

    }
    /**
     * Fait evoluer l'herbe.
     * Chaque sous-classe doit implementer sa propre logique d'evolution,
     * generalement en utilisant la probabilite probGeneration.
     */
    public abstract void evoluer();




} 