/**
 * classe qui centralise les compteurs globaux de la simulation 
 * Elle permet de suivrent le nombre de herbivores et de carnivors vivants,
 * Suivre le nombre de ressources collecter au cours de la simulation 
 * 
 * Tous les methodes et les attributs sont statiques car il n'existe qu'un seul jeu 
 * de statique pour toutes la simulation . 
 * La classe ne peut pas etre instanciee.
 */

public class Statistiques{
    /**Nombre d'agents herbivores actuellement vivant dans la simulation  */
    private static int nbHerbivoresVivants;
    /**Nombre d'agents carnivores acutellement vivant dans la simulation  */
    private static int nbCarnivoresVivants;
    /**Nombre total des ressources collectees depuis le debut de la simulation  */
    private static int nbRessourcesCollectees;

    /**
    * Constructeur private pour empecher l'instanciation de la classe .
    * Statistique est une classe utilitaire qui ne contient que des mombres statiques.
    */
    private Statistiques() {}
    /**
    * Incrementer de 1 le nombre d'herbivores vivants.
    * A appeler lors de l'ajout d'un nouveau herbivore dans la simulation.
    */
    public static void  incrementerHerbivores(){
        nbHerbivoresVivants++;
    } 
    /**
    * Decremente de 1 le nombre d'herbivores vivants.
    * A appeler lorsqu'un herbivore meurt (par exemple manger par un carnivore).
    */
    public static void decrementerHerbivores(){
        nbHerbivoresVivants--;
    }
    
    /** Incremente de 1 le nombre de carnivores vivants.
    * A appeler si on veut ajouter un carnivore dans la simulation .
    */
    public static void incrementerCarnivores(){
        nbCarnivoresVivants++;
    } 
    /**
     * Decremente de 1 le nombre de carnivores vivants.
     * A appeler lorsqu'un carnivore meurt .
     */
    public static void decrementerCarnivores(){
        nbCarnivoresVivants--;
    } 
    /**
     * Incremente de 1 le nombre de ressource collectees.
     * A appeler lorsqu'un agent recolte une ressource (herbier,bijou,etc).
     */
    public static void incrementerRessources(){
        nbRessourcesCollectees++;
    } 
    /**
     * Afficher dans la console les statistiques actuelles de la simulation .
     * Nombre d'herbivores vivants,nombre de carnivores vivants et le nombre de ressources collectees.
     */
    public static  void afficherStats(){
        System.out.println("Nombre de herbivores vivants: "+nbHerbivoresVivants);
        System.out.println("Nombre de Carnivors vivants:"+nbCarnivoresVivants);
        System.out.println("Nombre de ressouces "+nbRessourcesCollectees);
    }


}