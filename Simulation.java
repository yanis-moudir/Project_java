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
        if(((Lamartin) h).getHerbeAcote()){h.recolter(h.x, h.y);}
        else{h.planter(h.x,h.y);}
    }
}
    }


}