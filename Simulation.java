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
        Statistiques.incrementerHerbivores();
    }
    public void ajouterCarnivore(AgentsCarnivores a){
        agentsCarnivoreses.add(a);
        Statistiques.incrementerCarnivores();
    }
    public void ajouterRessource(Ressource r, int lig, int col){
          if (terrain.setCase(lig, col, r)) {
        ressources.add(r);
    }
    }
    public String afficherTerrain() {
    StringBuilder sb = new StringBuilder();
    String[][] grille = new String[terrain.nbLignes + 1][terrain.nbColonnes + 1];
    
    for (int i = 1; i <= terrain.nbLignes; i++)
        for (int j = 1; j <= terrain.nbColonnes; j++)
            grille[i][j] = "   ";
    
    for (Ressource r : ressources) {
    if (terrain.sontValides(r.getLigne(), r.getColonne())) {
        grille[r.getLigne()][r.getColonne()] = " " + r.type.substring(0,1) + " ";
    }
}
    for (AgentsHerbivores h : agentsHerbivoreses)
        if(terrain.sontValides(h.x, h.y)){
            grille[h.x][h.y] = " L ";
        }
    for (AgentsCarnivores c : agentsCarnivoreses)
        if(terrain.sontValides(c.x, c.y)){
        grille[c.x][c.y] = " Oq";
        }


    String sep = "+---".repeat(terrain.nbColonnes) + "+";
    
    for (int i = 1; i <= terrain.nbLignes; i++) {
        sb.append(sep).append("\n");
        for (int j = 1; j <= terrain.nbColonnes; j++)
            sb.append("|").append(grille[i][j]);
        sb.append("|\n");
    }
    sb.append(sep).append("\n");
    return sb.toString();
}
    public void etape(){
    for (AgentsHerbivores h : agentsHerbivoreses) {
        ((Lamartin) h).setHerbeAcote(ressources);
        if (h instanceof Lamartin) {
            ((Lamartin) h).setHerbeAcote(ressources);
            if(((Lamartin) h).getHerbeAcote()){
                ((Lamartin) h).recolter(((Lamartin) h).getLigneHerbe(), ((Lamartin) h).getColonneHerbe());
                
                
                Ressource r = terrain.getCase(((Lamartin) h).getLigneHerbe(), ((Lamartin) h).getColonneHerbe());
            if (r != null && r.getQuantite() == 0) {
                terrain.viderCase(((Lamartin) h).getLigneHerbe(), ((Lamartin) h).getColonneHerbe());
                ressources.remove(r);
            }
                }
            else{((Lamartin) h).planter(((Lamartin) h).getLigneHerbe(), ((Lamartin) h).getColonneHerbe());

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
Statistiques.decrementerHerbivores();
        } else {
               int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int dir = (int)(Math.random() * 4);
    int newLig = o.x + dx[dir];
    int newCol = o.y + dy[dir];
    
    if (terrain.sontValides(newLig, newCol)) {
        try {
            o.seDeplacer(newLig, newCol);
            Ressource r = terrain.getCase(newLig, newCol);
            if (r instanceof Bijoux) {
                ((Bijoux) r).evaluer();
                Statistiques.incrementerRessources();
                terrain.viderCase(newLig, newCol);
                ressources.remove(r);
                System.out.println(">> Orque a collecté de l'or en (" + newLig + "," + newCol + ")");
            }
        } catch (PositionInvalideException e) {
            System.out.println(e.getMessage());
        }
    }
        }
    }
}
// for (Ressource r : ressources) {
//     if (r instanceof Herbiers) {
//         ((Herbiers) r).evoluer();
//     }
// }
// System.out.print(afficherTerrain());
// System.out.println("=== Herbivores ===");
// for (AgentsHerbivores h : agentsHerbivoreses) {
//     System.out.println(h);
// }
// System.out.println("=== Carnivores ===");
// for (AgentsCarnivores c : agentsCarnivoreses) {
//     System.out.println(c);
// }

// Statistiques.afficherStats();

}


public void lancerSimulation(int nbEtapes, BufferedWriter writer) throws IOException {
    writer.write("=== Terrain Initial ===");
    writer.newLine();
    writer.write(afficherTerrain());
    writer.newLine();
    System.out.println("=== Terrain Initial ===");
    System.out.print(afficherTerrain());
    for (int i = 0; i < nbEtapes; i++) {
        writer.write("=== Étape " + (i + 1) + " ===");
        writer.newLine();
        // écrire les stats
        writer.write("=== Terrain  ===");
        writer.newLine();
        writer.write(afficherTerrain());
        writer.newLine();

       
        writer.write("Herbivores vivants: " + agentsHerbivoreses.size());
        writer.newLine();
        writer.write("Carnivores vivants: " + agentsCarnivoreses.size());
        writer.newLine();
        writer.write("Ressources récoltées: " + Statistiques.getNbRessourcesCollectees());
        writer.newLine();
       
        etape();
        
    }
}
public Terrain getTerrain(){return terrain;}
}

