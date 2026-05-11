import java.util.ArrayList;
/**
 * * Classe representant un Lamartin, un agent herbivore concret de la simulation.
 * 
 * Le Lamartin cherche des herbiers dans son voisinage immediat pour les recolter.
 * S'il trouve un herbier voisin, il memorise sa position pour aller le recolter.
 * Sinon, il choisit une case vide aleatoire du terrain pour aller y planter un 
 * nouvel herbier.
 */

public class Lamartin extends AgentsHerbivores{
    /**
     * Indique si un herbier a ete repere dans le voisinage immediat.
     */
    private boolean herbeAcote;
    /**Ligne de l'herbier cible (ou de la case aleatoire pour planter). */
    private int ligHerbe;
    /**Colonne de l'herbier cible (ou de la case aleatoire pour planter). */
    private int colHerbe;   
    /**
     *  Construit un nouveau Lamartin a la position specifiee, sur le terrain donne.
     * Le Lamartin est initialement sans herbier repere.
     * @param nom nom de lamartin 
     * @param x coordonnee de ligne de lamartin 
     * @param y coordonnee de colonne de lamartin 
     * @param terrain terrain sur lequel lamartin evolue 
     */
    public Lamartin(String nom,int x,int y,Terrain terrain){
        super(nom, x, y, terrain);
        herbeAcote=false;
    }
    /**
     * * Cherche un herbier dans le voisinage immediat du Lamartin.
     * Si un herbier est trouve a une distance inferieure ou egale a 1
     * (voisinage de 8 cases), sa position est memorisee comme cible 
     * pour la recolte.
     * Si aucun herbier n'est trouve, une case vide aleatoire du terrain est
     * choisie comme destination pour planter un nouvel herbier.
     * 
     * @param ressource liste des ressources presentes dans la simulation 
     */
    public void setHerbeAcote(ArrayList<Ressource> ressource){

        herbeAcote=false;
        for(Ressource r:ressource){
            if(r instanceof Herbiers && Math.abs(this.x-r.getLigne())<=1 && Math.abs(this.y-r.getColonne())<=1){
                herbeAcote=true;
                ligHerbe=r.getLigne();
                colHerbe=r.getColonne();    
            }
        }
        if (herbeAcote==false){
           do {
    ligHerbe = (int)(Math.random() * terrain.nbLignes)+1;
    colHerbe = (int)(Math.random() * terrain.nbColonnes)+1;
} while (!terrain.caseEstVide(ligHerbe, colHerbe));
       
}
}
/**
 * Indique si un herbier a ete repere dans le voisinage immediat.
 * 
 * @return True si un herbier est a cote de lamartin ,false sinon 
 */
 boolean getHerbeAcote(){return herbeAcote;}
    /**
     * Retourne la ligne de l'herbier cible (ou de la case aleatoire choisie).
     * @return ligne de la cible memorisee
     */
    public int getLigneHerbe(){return ligHerbe;}
    /**
     * Retourne la colonne de l'herbier cible (ou de la case aleatoire choisie).
     * @return colonne de la cible memorisee
     */
    public int getColonneHerbe(){return colHerbe;}
    /**
     * * Retourne une representation textuelle du Lamartin.
     * Le format inclut le type "Lamartin" suivi de la representation 
     * heritee de la classe parente AgentsHerbivores.
     * 
     * @return chaine de caractere representant lamartin .
     */
    public String toString(){
        return "Lamartin "+super.toString();
    }
}