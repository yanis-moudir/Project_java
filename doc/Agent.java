// Tarik Fernane & Yanis Moudir
/**
 * Classe abstraite representant un agent generique evoluant sur le terrain .
 * Un agent possede une position (x,y) , un etat de vie (vivant ou mort )
 * et une reference vers le terrain sur lequelle il evolue .
 * 
 * Cette classe sert de base  aux agents spécifiques de la simulation ,
 * comme les agents herbivores et carnivores.
 */
public abstract class Agent{
    /*
     * x :Cordonnee en ligne (abscisse ) de l'agent sur le terrain /
     * y :Cordonnee en colonne (ordonnee) de l'agent sur le terrain .
     */
    protected int x,y;
    /**Boolean indiquant si l'agent est vivant ou mort : (True or False) */
    protected boolean enVie;
    /**Terrain sur lequel l'agent evolue.  */
    protected Terrain terrain;
    /**
     * Constuire un nouvel agent sur le terrain a la position spécifiee.
     * et l'agent est vivant 
     * @param x coordonnee en ligne  de l'agent 
     * @param y coordonnee en colonne de l'agent 
     * @param terrain terrain sur lequel l'argent evolue 
     */
    public Agent(int x,int y,Terrain terrain){
        this.x=x;
        this.y=y;
        enVie=true;
        this.terrain=terrain;

    }
    /**Calcule  la distance euclidienne entre la position   actuelle de l'agent et un point donne sur le terrain . */
    public double  distance(int lig,int col){
        return(Math.sqrt(Math.pow(Math.abs(x-lig), 2)+Math.pow(Math.abs(y-col), 2)));
    }
    /**
     * Deplace l'agent vers une position spécifique sur le terrain.
     * Le deplacement na lieu que si  la position est valide .
     * @param lig nouvelle ligne de l'agent 
     * @param col nouvelle colonne de l'agent 
     * @throws PositionInvalideException si la position cible n'est pas valide sur le terrain .
     */
    public void seDeplacer(int lig,int col) throws PositionInvalideException{

        if (!terrain.sontValides(lig,col)){
            throw new PositionInvalideException("Position Invalide !!");
        }
        x=lig;
        y=col;
    }
}