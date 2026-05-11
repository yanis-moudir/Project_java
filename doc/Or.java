//Tarik Fernane & Yanis Moudir
/**
 * Classe representant un bijou en or, une ressource de type bijou 
 * pouvant etre placee sur le terrain de la simulation.
 * 
 * La valeur d'un bijou en or est calculee en fonction de sa quantite :
 * elle vaut 10 fois la quantite presente.
 */
public class Or extends Bijoux{

    /**
     * Construit un nouveau bijou en or avec la quantite specifiee.
     * Le type de la ressource est automatiquement defini comme "or".
     * 
     * @param quantite  quantite initiale d'or
     */
    public Or(int quantite) {
        super("or", quantite);
    }

    /**
     * Evalue la valeur du bijou en or.
     * La valeur est calculee comme dix fois la quantite d'or possedee.
     */
    public void evaluer(){
        valeur=getQuantite()*10;
    }

    /**
     * Cree une copie independante de ce bijou en or.
     * La copie a la meme quantite que l'original.
     * 
     * @return  un nouvel objet Or avec la meme quantite
     */
    public Or clone(){
        return new Or(getQuantite());
    }

    /**
     * Retourne une representation textuelle du bijou en or.
     * Le format inclut le type, la quantite et la valeur du bijou.
     * 
     * @return  chaine de caracteres representant le bijou en or
     */
    public String toString(){
        return "Type: "+type+" Quantite:"+getQuantite()+" valeur:"+valeur;
    }
}