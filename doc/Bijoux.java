//Yanis Moudir & Tarik Fernane
/**
 *  * Classe abstraite representant un bijou, une ressource de valeur 
 * pouvant etre placee sur le terrain de la simulation.
 * 
 * Cette classe sert de base aux differents types de bijoux concrets 
 * (par exemple Or). Chaque bijou possede une valeur, et chaque sous-classe 
 * doit definir sa propre methode d'evaluation de cette valeur.
 */
public abstract class Bijoux extends Ressource{
    /**
     * Valeur du bijou, calculee par la methode evaluer().
     */
    protected int valeur;
    /**
     * Evalue la valeur du bijou.
     * Chaque sous-classe doit implementer sa propre logique d'evaluation
     * en fonction de son type et de sa quantite.
     */
    public abstract void evaluer();
    /**
     * Construit un nouveau bijou avec le type et la quantite specifies.
     * La valeur initiale est fixee a 10 par defaut.
     * @param type type de bujou ( exemple : OR)
     * @param quantite quantité initial du bijou 
     */
    public Bijoux(String type, int quantite) {
    super(type, quantite);
    valeur = 10;
}



}