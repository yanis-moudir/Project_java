//Yanis Moudir & Tarik Fernane
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Classe principale de test de la simulation.
 * Cette classe contient le point d'entree du programme (methode main).
 * 
 * Elle demande a l'utilisateur les parametres de la simulation 
 * (taille du terrain, nombre d'agents, nombre de ressources, nombre d'etapes),
 * cree les agents et ressources de maniere aleatoire sur le terrain, 
 * puis lance la simulation en ecrivant les resultats dans le fichier "Affichage.txt".
 */ 
public class TestSimulation {
        /**
     * Point d'entree du programme.
     * Demande a l'utilisateur les parametres de la simulation, 
     * initialise le terrain et les agents, puis lance la simulation 
     * sur le nombre d'etapes specifie.
     * 
     * Les resultats de chaque etape (statistiques et terrain) sont ecrits 
     * dans un fichier texte nomme "Affichage.txt".
     * 
     * @param args  arguments de la ligne de commande (non utilises)
     */
    public static void main(String[] args) {

       Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nombre de lignes du terrain : ");
        int nbLignes = scanner.nextInt();
        System.out.print("Entrez le nombre de colonnes du terrain : ");
        int nbColonnes = scanner.nextInt();

        System.out.println("Entrer le nombre d'agents herbivores : ");
        int nbHerbivores = scanner.nextInt();
        System.out.println("Entrer le nombre d'agents carnivores : ");      
        int nbCarnivores = scanner.nextInt();
        System.out.println("Entrer le nombre de bijoux : ");
        int nbBijoux = scanner.nextInt();       
        System.out.println("Entrer le nombre d'herbes : ");
        int nbHerbes = scanner.nextInt();
        System.out.println("Entrer le nombre d'étapes de la simulation : ");
        int nbEtapes = scanner.nextInt();       



        Simulation sim = Simulation.getInstance(nbLignes, nbColonnes);
        for (int i = 0; i < nbHerbivores; i++) {
            sim.ajouterHerbivore((AgentsHerbivores) new Lamartin("Herbivore", (int)(Math.random() * nbLignes)+1, (int)(Math.random() * nbColonnes)+1, sim.getTerrain()));
        }
        for (int i = 0; i < nbCarnivores; i++) {
            sim.ajouterCarnivore((AgentsCarnivores) new Orque((int)(Math.random() * nbLignes)+1, (int)(Math.random() * nbColonnes)+1, sim.getTerrain()));
        }
        for (int i = 0; i < nbBijoux; i++) {
            int ligAlea = (int)(Math.random() * nbLignes)+1;
            int colAlea = (int)(Math.random() * nbColonnes)+1;
            sim.ajouterRessource(new Or(1), ligAlea, colAlea);
        }
        for (int i = 0; i < nbHerbes; i++) {
            int ligAlea = (int)(Math.random() * nbLignes)+1;
            int colAlea = (int)(Math.random() * nbColonnes)+1;
            sim.ajouterRessource(new Herbiers("Herbe", 1), ligAlea, colAlea);
        }



        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Affichage.txt"))) {
    sim.lancerSimulation(nbEtapes, writer);
} catch (IOException e) {
    System.out.println(e.getMessage());
}
    }

    
}