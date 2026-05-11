//Yanis Moudir & Tarik Fernane
/**
 * Classe representant un herbier, un type concret d'herbe pouvant etre 
 * place sur le terrain de la simulation.
 * 
 * L'herbier peut evoluer au fil du temps : a chaque etape, sa quantite 
 * peut augmenter selon une probabilite definie dans la classe parente Herbes.
 * Les herbiers sont la principale ressource alimentaire des agents herbivores.
 */
public class Herbiers extends Herbes{
    /**
     * Construit un nouvel herbier avec le type et la quantite specifies.
     * @param type type de herbier par exemple : Herbiers ou Herbe 
     * @param quantite quantite initial de l'herbier 
     */
    public Herbiers(String type,int quantite){
        super(type, quantite);
    }
    /**
     * Fait evoluer l'herbier.
     * Avec une probabilite definie dans la classe parente (probGeneration),
     * la quantite de l'herbier augmente de 1, simulant ainsi sa croissance.
     */
    public void evoluer(){
        if(Math.random()<probGeneration){
            this.setQuantite(this.getQuantite()+1);
        }
    }
    /**
     * Cree une copie independante de cet herbier.
     * La copie a le meme type et la meme quantite que l'original.

     * @return un nouvel objet herbier avec les meme caractéristiques
     */
    public Herbiers clone(){
        return new Herbiers(this.type,this.getQuantite());
    }
    /**
     * Retourne une representation textuelle de l'herbier.
     * Le format inclut le type et la quantite de l'herbier.
     * @return chaine de caractere representant l'herbier.
     */

    public String toString(){
        return "type: "+type+" Qauntite: "+getQuantite();
    }
}