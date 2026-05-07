import java.io.File;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;

public class Simulation{

    private Terrain terrain;
    private ArrayList<AgentsCarnivores> agentsCarnivoreses;
    private ArrayList<AgentsHerbivores> agentsHerbivoreses;
    private ArrayList<Ressource> ressources;

    private static Simulation instance=null;

    private Simulation(){
        agentsCarnivoreses = new ArrayList<>();
        agentsHerbivoreses = new ArrayList<>();
        ressources = new ArrayList<>();
    }

    public static Simulation getInstance(int nbLignes, int nbColonnes){
        if(instance==null){
            instance=new Simulation();
            instance.terrain=new Terrain(nbLignes,nbColonnes);
        }
        return instance;
    }


    public void ajouterHerbivore(AgentsHerbivores a){
        agentsHerbivoreses.add(a);
    }
    public void ajouterCarnivore(AgentsCarnivores a){
        agentsCarnivoreses.add(a);
    }
    public void ajouterRessource(Ressource r, int lig, int col){
          if (terrain.setCase(lig, col, r)) {
        ressources.add(r);
    }
    }
    public void afficherTerrain() {
    String[][] grille = new String[terrain.nbLignes + 1][terrain.nbColonnes + 1];
    
    // remplir avec cases vides
    for (int i = 1; i <= terrain.nbLignes; i++)
        for (int j = 1; j <= terrain.nbColonnes; j++)
            grille[i][j] = "   ";
    
    // placer les ressources
    for (Ressource r : ressources)
        grille[r.getLigne()][r.getColonne()] = " " + r.type.substring(0,1) + " ";
    
    // placer les agents
    for (AgentsHerbivores h : agentsHerbivoreses)
        grille[h.x][h.y] = " L ";
    for (AgentsCarnivores c : agentsCarnivoreses)
        grille[c.x][c.y] = " Oq";

    // séparateur horizontal
    String sep = "+---".repeat(terrain.nbColonnes) + "+";
    
    // afficher
    for (int i = 1; i <= terrain.nbLignes; i++) {
        System.out.println(sep);
        for (int j = 1; j <= terrain.nbColonnes; j++)
            System.out.print("|" + grille[i][j]);
        System.out.println("|");
    }
    System.out.println(sep);
}
    public void etape(){
    for (AgentsHerbivores h : agentsHerbivoreses) {
        if (h instanceof Lamartin) {
            ((Lamartin) h).setHerbeAcote(ressources);
            if(((Lamartin) h).getHerbeAcote()){
                ((Lamartin) h).recolter(((Lamartin) h).getLigneHerbe(), ((Lamartin) h).getColonneHerbe());
                System.out.println(">> Lamartin a récolté un Herbier en (" + ((Lamartin)h).getLigneHerbe() + "," + ((Lamartin)h).getColonneHerbe() + ")");
                Ressource r = terrain.getCase(((Lamartin) h).getLigneHerbe(), ((Lamartin) h).getColonneHerbe());
            if (r != null && r.getQuantite() == 0) {
                terrain.viderCase(((Lamartin) h).getLigneHerbe(), ((Lamartin) h).getColonneHerbe());
                ressources.remove(r);
            }
                }
            else{((Lamartin) h).planter(((Lamartin) h).getLigneHerbe(), ((Lamartin) h).getColonneHerbe());
                System.out.println(">> Lamartin a planté un Herbier en (" + ((Lamartin)h).getLigneHerbe() + "," + ((Lamartin)h).getColonneHerbe() + ")");
                }
        }
    }


    for (AgentsCarnivores c : agentsCarnivoreses) {
    if (c instanceof Orque) {
        Orque o = (Orque) c;
        o.setProieAcote(agentsHerbivoreses);
        if (o.getProieAcote()) {
            o.chasser();
            o.manger(o.getLigneProie(), o.getColonneProie());
            System.out.println(">> Orque(" + o.x + "," + o.y + ") a tué Lamartin(" + o.getLigneProie() + "," + o.getColonneProie() + ")");
            AgentsHerbivores proie = null;
            for (AgentsHerbivores h : agentsHerbivoreses) {
                if (h.x == o.getLigneProie() && h.y == o.getColonneProie()) {
                    proie = h;
                    break;
    }
}
ArrayList<AgentsHerbivores> aSupprimer = new ArrayList<>();
if (proie != null) aSupprimer.add(proie);
// après la boucle des carnivores :
agentsHerbivoreses.removeAll(aSupprimer);
        } else {
                int ligAlea = 0;
                int colAlea = 0;
            try {
                // position aléatoire
                ligAlea = (int)(Math.random() * terrain.nbLignes)+1;
                colAlea = (int)(Math.random() * terrain.nbColonnes)+1;
                o.seDeplacer(ligAlea, colAlea);
            } catch (PositionInvalideException e) {
                System.out.println(e.getMessage());
            }
            Ressource r = terrain.getCase(ligAlea, colAlea);
            if(r instanceof Bijoux){
             Statistiques.incrementerRessources();
                terrain.viderCase(ligAlea, colAlea);
                ressources.remove(r);
            }
        }
    }
}
for (Ressource r : ressources) {
    if (r instanceof Herbiers) {
        ((Herbiers) r).evoluer();
    }
}
afficherTerrain();
System.out.println("=== Herbivores ===");
for (AgentsHerbivores h : agentsHerbivoreses) {
    System.out.println(h);
}
System.out.println("=== Carnivores ===");
for (AgentsCarnivores c : agentsCarnivoreses) {
    System.out.println(c);
}

Statistiques.afficherStats();

}


public void lancerSimulation(int nbEtapes, BufferedWriter writer) throws IOException {
    for (int i = 0; i < nbEtapes; i++) {
        writer.write("=== Étape " + (i + 1) + " ===");
        writer.newLine();
        // écrire les stats
        writer.write("Herbivores vivants: " + agentsHerbivoreses.size());
        writer.newLine();
        etape();
        
    }
}
public Terrain getTerrain(){return terrain;}
}

