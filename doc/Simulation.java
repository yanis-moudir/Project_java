import java.io.File;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;


/**
 * Classe principale orchestrant la simulation d'un ecosysteme.
 * 
 * Cette classe gere le terrain, les agents (herbivores et carnivores) 
 * ainsi que les ressources (herbes et bijoux) presents sur le terrain.
 * Elle implemente le patron de conception Singleton : il ne peut exister
 * qu'une seule instance de Simulation a la fois.
 * 
 * A chaque etape, les agents agissent (les herbivores recoltent ou plantent
 * de l'herbe, les carnivores chassent les proies ou se deplacent aleatoirement) 
 * et les ressources evoluent (les herbiers peuvent se developper).
 */
public class Simulation{
    /** Terrain sur lequel se deroule la simulation. */
    private Terrain terrain;

    /** Liste des agents carnivores presents dans la simulation. */
    private ArrayList<AgentsCarnivores> agentsCarnivoreses;
    /** Liste des agents herbivores presents dans la simulation. */
    private ArrayList<AgentsHerbivores> agentsHerbivoreses;
    /** Liste des ressources (herbes, bijoux) presentes sur le terrain. */
    private ArrayList<Ressource> ressources;
    /** Instance unique de la simulation (patron Singleton). */ 
    private static Simulation instance=null;

    /**
     * Constructeur prive (patron Singleton).
     * Initialise les listes vides d'agents et de ressources.
     * Ne peut etre appele que depuis la methode getInstance.
     */
    private Simulation(){
        agentsCarnivoreses = new ArrayList<>();
        agentsHerbivoreses = new ArrayList<>();
        ressources = new ArrayList<>();
    }
    /**
     * Retourne l'instance unique de la simulation (patron Singleton).
     * Si aucune instance n'existe encore, une nouvelle simulation est creee
     * avec un terrain de taille specifiee. Sinon, l'instance existante est retournee.
     * @param nbLignes nombre de lignes de terrain .
     * @param nbColonnes nombre de colonnes de terrain 
     * @return l'unique instance de la simulation .
     */
    public static Simulation getInstance(int nbLignes, int nbColonnes){
        if(instance==null){
            instance=new Simulation();
            instance.terrain=new Terrain(nbLignes,nbColonnes);
        }
        return instance;
    }

    /**
     * ajoute un agent herbivore a la simulation 
     * @param a agent herbivore a ajouter 
     */
    public void ajouterHerbivore(AgentsHerbivores a){
        agentsHerbivoreses.add(a);
    } 
    /**
     * ajoute un agent carnivore a la simulation 
     * @param a agent carnivore a ajouter 
     */
    public void ajouterCarnivore(AgentsCarnivores a){
        agentsCarnivoreses.add(a);
    }
    /**
     * Ajoute une ressource sur le terrain  , et elle est valide que si son 
     * placement sur le terrain est a reussi (case valide ou libre ).
     * @param r ressource a ajouter 
     * @param lig ligne ou placer la ressource 
     * @param col colonne ou placer la ressource 
     */
    public void ajouterRessource(Ressource r, int lig, int col){
          if (terrain.setCase(lig, col, r)) {
        ressources.add(r);
    }
    }

    /**
     * Construit une representation textuelle du terrain visible sur la console .
     * les case sont séparer par des barre "|" et des ligne par des "+------".
     * Chaque case affiche soit un espace (case vide), soit la premiere lettre du type 
     * de ressource, soit "L" pour un Lamartin (herbivore), soit "Oq" pour un Orque (carnivore).
     * @return la representation textuelle .
     */
    public String afficherTerrain() {
    StringBuilder sb = new StringBuilder();
    String[][] grille = new String[terrain.nbLignes + 1][terrain.nbColonnes + 1];
    
    for (int i = 1; i <= terrain.nbLignes; i++)
        for (int j = 1; j <= terrain.nbColonnes; j++)
            grille[i][j] = "   ";
    
    for (Ressource r : ressources)
        grille[r.getLigne()][r.getColonne()] = " " + r.type.substring(0,1) + " ";
    
    for (AgentsHerbivores h : agentsHerbivoreses)
        grille[h.x][h.y] = " L ";
    for (AgentsCarnivores c : agentsCarnivoreses)
        grille[c.x][c.y] = " Oq";

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
    /**
     * Execute une etape de la simulation 
     * 
     * pour chaque herbivores : il recolte un herbier voisin si poussible 
     * sinon il plante un nouvel herbier sur une case vide aleatoire 
     * 
     * pour le carnivore : il chasse et tue un herbivore voisin si poussible .
     * sinon il se deplace aléatoirement sur une case vide et si il tombe sur un bijou il le collect 
     * 
     * Enfin : l'herbier present sur le terrain evoluent (croissance poussible )
     * et les statistiques de la simualtion sont affichees.
     */
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
System.out.print(afficherTerrain());
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

/**
 * Lance la simulation sur un etape donnee .
 * a chaque etape , les statistiques actuelles et l'etat du terrain 
 * sont ecrit dans le flux de sortie fourni , puis une etape est executee.
 * @param nbEtapes nombre d'etape a simuler 
 * @param writer flux d'ecriture  vers le ficher de sortie 
 * @throws IOException si une erreurs survient lors  de l'ecriture dans le fichier 
 */
public void lancerSimulation(int nbEtapes, BufferedWriter writer) throws IOException {
    for (int i = 0; i < nbEtapes; i++) {
        writer.write("=== Étape " + (i + 1) + " ===");
        writer.newLine();
        // écrire les stats
        writer.write("Herbivores vivants: " + agentsHerbivoreses.size());
        writer.newLine();
        writer.write("Carnivores vivants: " + agentsCarnivoreses.size());
        writer.newLine();
        writer.write("Ressources récoltées: " + ressources.size());
        writer.newLine();
        writer.write("Terrain:"+afficherTerrain());
        writer.newLine();
        etape();
        
    }
}
/**
 * Retourne le terrain de la simulation 
 * @return le terrain associe a cette   simulation 
 */
public Terrain getTerrain(){return terrain;}
}

