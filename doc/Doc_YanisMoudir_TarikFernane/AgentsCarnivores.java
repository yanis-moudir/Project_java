//Yanis Moudir & Tarik Fernane
/**
 * Classe abstraite representant un agent carnivore, c'est-a-dire un agent
 * qui chasse et mange des proies (agents herbivores).
 * 
 * Cette classe herite d'Agent et implemente l'interface OutilsCarnivores.
 * Elle sert de base aux types concrets de carnivores (par exemple Orque).
 * Chaque carnivore possede un etat de chasse qui indique s'il est 
 * actuellement en train de chasser une proie.
 */
public abstract class AgentsCarnivores extends Agent implements OutilsCarnivores{
    /** Indique si l'agent est actuellement en etat de chasse. */
    protected boolean enChasse;
    /**
     * Construit un nouvel agent carnivore a la position specifiee, 
     * sur le terrain donne. L'agent n'est initialement pas en chasse.
     * @param x la position selon les ligne de agent 
     * @param y selon les colonne de l'agent 
     * @param terrain terrain sur lequel agent evolue .
     */
    public AgentsCarnivores(int x,int y,Terrain terrain){
        super(x, y, terrain);
        enChasse=false;
    }
    /**
     * Met l'agent carnivore en etat de chasse.
     * Cette methode active simplement l'etat de chasse, sans deplacement.
     */
    public void chasser(){
        enChasse=true;
    }
    /**
     * Permet a l'agent carnivore de manger une proie en se deplacant 
     * vers sa position. L'action n'a lieu que si l'agent est en etat de chasse.
     * En cas de position invalide, un message d'erreur est affiche et 
     * l'etat de chasse est conserve.
     * 
     * @param lig  ligne de la position de la proie
     * @param col  colonne de la position de la proie
     */
    public void manger(int lig,int col){
        if(enChasse){
            try {
        seDeplacer(lig, col);
        enChasse = false;
    }   catch (PositionInvalideException e) {
        System.out.println(e.getMessage());
    }
}
    }

    /**
     * Retourne une representation textuelle de l'agent carnivore.
     * Le format inclut la position, l'etat de vie et l'etat de chasse de l'agent.
     * 
     * @return  chaine de caracteres representant l'agent carnivore
     */
    public String toString(){
        return " Position:("+x+","+y+")"+" Vie: "+enVie+" En Chasse :"+enChasse;
    }
}