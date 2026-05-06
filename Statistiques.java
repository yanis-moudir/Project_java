public class Statistiques{

    private static int nbHerbivoresVivants;
    private static int nbCarnivoresVivants;
    private static int nbRessourcesCollectees;


    private Statistiques() {}

    public static void  incrementerHerbivores(){
        nbHerbivoresVivants++;
    } 
    public static void decrementerHerbivores(){
        nbHerbivoresVivants--;
    } 
    public static void incrementerCarnivores(){
        nbCarnivoresVivants++;
    } 
    public static void decrementerCarnivores(){
        nbCarnivoresVivants--;
    } 
    public static void incrementerRessources(){
        nbRessourcesCollectees++;
    } 

    public static  void afficherStats(){
        System.out.println("Nombre de herbivores vivants: "+nbHerbivoresVivants);
        System.out.println("Nombre de Carnivors vivants:"+nbCarnivoresVivants);
        System.out.println("Nombre de ressouces "+nbRessourcesCollectees);
    }


}