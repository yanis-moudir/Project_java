import java.util.ArrayList;

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
    public void etape(){
    for (AgentsHerbivores h : agentsHerbivoreses) {
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
            else{((Lamartin) h).planter(((Lamartin) h).getLigneHerbe(), ((Lamartin) h).getColonneHerbe());}
        }
    }


    for (AgentsCarnivores c : agentsCarnivoreses) {
    if (c instanceof Orque) {
        Orque o = (Orque) c;
        o.setProieAcote(agentsHerbivoreses);
        if (o.getProieAcote()) {
            o.chasser();
            o.manger(o.getLigneProie(), o.getColonneProie());
            AgentsHerbivores proie = null;
            for (AgentsHerbivores h : agentsHerbivoreses) {
                if (h.x == o.getLigneProie() && h.y == o.getColonneProie()) {
                    proie = h;
                    break;
    }
}
if (proie != null) agentsHerbivoreses.remove(proie);
        } else {
            try {
                // position aléatoire
                int ligAlea = (int)(Math.random() * terrain.nbLignes);
                int colAlea = (int)(Math.random() * terrain.nbColonnes);
                o.seDeplacer(ligAlea, colAlea);
            } catch (PositionInvalideException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
for (Ressource r : ressources) {
    if (r instanceof Herbiers) {
        ((Herbiers) r).evoluer();
    }
}
terrain.afficher(1);
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


public void lancerSimulation(int nbEtapes){
    for (int i = 0; i < nbEtapes; i++) {
        System.out.println("Étape " + (i + 1));
        etape();
    }

}
}