/**
 * Interface definissant les comportements specifiques aux agent carnivores.
 * Toute classe d'agent doit implementer cette interface 
 * pour etre capable de chasser et manger ses proies avec les deux methodes.
 */
public interface OutilsCarnivores{
    /**
     * Permet a l'agent carnivores de manger une proie situee a la position donnee.
     * L'agent se deplacer vers cette position et  mange la proie .
     * @param lig ligne de la position de la proie a manger 
     * @param col colonne de la position de la proie a manger 
     */
    public void manger(int lig,int col);
    /**
     * Met l'agent carnivore en etat de chasse 
     * Cette methode est generalement appelee avant d'effectuer un deplacement vers une proie dans le voisinage .
     */
    public void chasser();
}