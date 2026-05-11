import java.util.ArrayList;


/**
 * Classe representant un Orque, un agent carnivore concret de la simulation.
 * 
 * L'Orque chasse les agents herbivores (notamment les Lamartins) presents
 * dans son voisinage immediat. Il memorise la position de la proie reperee
 * pour aller la manger lors de l'etape suivante. Si aucune proie n'est a
 * proximite, il choisit une case vide aleatoire pour se deplacer.
 */
public class Orque extends AgentsCarnivores{

    /** Indique si une proie a ete reperee dans le voisinage immediat. */
    private boolean proieAcote;

    /** Ligne de la proie ciblee (ou de la case aleatoire si aucune proie). */
    private int ligProie;

    /** Colonne de la proie ciblee (ou de la case aleatoire si aucune proie). */
    private int colProie;

    /**
     * Construit un nouvel Orque a la position specifiee, sur le terrain donne.
     * L'Orque est initialement sans proie reperee.
     * 
     * @param x        coordonnee en ligne de l'Orque
     * @param y        coordonnee en colonne de l'Orque
     * @param terrain  terrain sur lequel l'Orque evolue
     */
    public Orque(int x,int y,Terrain terrain){
        super(x, y, terrain);
        proieAcote=false;
    }

    /**
     * Cherche une proie (agent herbivore) dans le voisinage immediat de l'Orque.
     * Si un herbivore est trouve a une distance inferieure ou egale a 1
     * (voisinage de 8 cases), sa position est memorisee comme cible.
     * Si aucune proie n'est trouvee, une case vide aleatoire du terrain est
     * choisie comme destination de deplacement.
     * 
     * @param herbivores  liste des agents herbivores presents dans la simulation
     */
    public void setProieAcote(ArrayList<AgentsHerbivores> herbivores){
        proieAcote = false;
        for(AgentsHerbivores h:herbivores){
            if(Math.abs(this.x-h.x)<=1 &&  Math.abs(this.y-h.y)<=1  ){
                proieAcote=true;    
                ligProie=h.x;
                colProie=h.y;       
            }
        }
        if (proieAcote==false){
            do {
                ligProie = (int)(Math.random() * terrain.nbLignes)+1;
                colProie = (int)(Math.random() * terrain.nbColonnes)+1;
            } while (!terrain.caseEstVide(ligProie, colProie));
        }
    }

    /**
     * Indique si une proie a ete reperee dans le voisinage immediat.
     * 
     * @return  true si une proie est a cote de l'Orque, false sinon
     */
    public boolean getProieAcote(){return proieAcote; }

    /**
     * Retourne la ligne de la proie ciblee (ou de la case aleatoire choisie).
     * 
     * @return  ligne de la cible memorisee
     */
    public int getLigneProie(){return ligProie;}

    /**
     * Retourne la colonne de la proie ciblee (ou de la case aleatoire choisie).
     * 
     * @return  colonne de la cible memorisee
     */
    public int getColonneProie(){return colProie;}

    /**
     * Retourne une representation textuelle de l'Orque.
     * Le format inclut le type "Orque" suivi de la representation 
     * heritee de la classe parente AgentsCarnivores.
     * 
     * @return  chaine de caracteres representant l'Orque
     */
    public String toString(){
        return "Orque "+super.toString();
    }
}