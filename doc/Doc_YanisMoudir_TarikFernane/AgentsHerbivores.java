//Tarik Fernane & Yanis Moudir
/**
 * Classe abstraite representant un agent herbivore, c'est-a-dire un agent
 * qui se nourrit de ressources vegetales (herbes) et peut en planter.
 * 
 * Cette classe herite d'Agent et implemente l'interface OutilsHerbivores.
 * Elle sert de base aux types concrets d'herbivores (par exemple Lamartin).
 * Chaque herbivore possede un nom et peut recolter ou planter des herbiers
 * sur le terrain.
 */
public abstract class AgentsHerbivores extends Agent implements OutilsHerbivores{
    /** le nom de l'agent herbivore . */
    protected String nom;
    /**
     * Construit un nouvel agent herbivore avec le nom et la position specifies.
     * @param nom non de agent herbivore.
     * @param x  coordonnee en ligne de cette agent 
     * @param y coordonnee en colonne de cette agent 
     * @param terrain terrain sur lequel l'agent evolue 
     */
    public AgentsHerbivores(String nom,int x,int y,Terrain terrain){
        super(x, y, terrain);
        this.nom=nom;
    }
    /**
     * Recolte une ressource (herbier) presente sur la case specifiee.
     * Si la case contient un herbier, sa quantite est decrementee de 1
     * et le compteur global de ressources collectees est incremente.
     * 
     * @param lig  ligne de la case a recolter
     * @param col  colonne de la case a recolter
     */
    public void recolter(int lig,int col){
       Ressource r=terrain.getCase(lig, col);
       if(r instanceof Herbiers){
            r.setQuantite(r.getQuantite() - 1);
           Statistiques.incrementerRessources();
        

    }
    }
    /**
     * Plante un nouvel herbier sur la case specifiee.
     * L'herbier n'est plante que si la case est vide.
     * 
     * @param lig  ligne de la case ou planter
     * @param col  colonne de la case ou planter
     */
    public void planter(int lig,int col){
         Ressource r=terrain.getCase(lig, col);
         if(terrain.caseEstVide(lig, col)){
             terrain.setCase(lig, col, new Herbiers("Herbiers",1));
             
      
    }
    }
    /**
     * Retourne une representation textuelle de l'agent herbivore.
     * Le format inclut le nom, la position et l'etat de vie de l'agent.
     * 
     * @return  chaine de caracteres representant l'agent herbivore
     */
    public String toString(){//pas besoin de super vu qu'il sont protected 
        return "Nom: "+nom+" Position:("+x+","+y+")"+" Vie: "+enVie;
    }
}