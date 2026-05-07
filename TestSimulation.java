import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;





public class TestSimulation {
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