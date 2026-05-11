/**
 * Interface definisant le comporetement des agents herbivores 
 * toutes classe agent herbivore doit implémenter cette interface 
 * pour etre capable de planter et recolter .
 */
public interface OutilsHerbivores{
    /**
     * Permet a l'agent herbivore de planter un nouvel herbier 
     * sur une case vide du terrain.
     * @param lig ligne de la position sur lequ'elle herbivore plante 
     * @param col la colonne de la position pour planter 
     */
    public void planter(int lig,int col);
    /**
     * Permet a l'agent herbivore de recolter une ressource 
     * (par exemple un herbier) presente sur une case du terrain.
     * @param lig  ligne de la postition pour recolter 
     * @param col   colonne de la position poour recolter 
     */
    public void recolter(int lig,int col);

}