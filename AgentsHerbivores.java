public abstract class AgentsHerbivores extends Agent implements OutilsHerbivores{

    protected String nom;

    public AgentsHerbivores(String nom,int x,int y,Terrain terrain){
        super(x, y, terrain);
        this.nom=nom;
    }
    public void recolter(int lig,int col){
       Ressource r=terrain.getCase(lig, col);
       if(r instanceof Herbiers){
            r.setQuantite(r.getQuantite() - 1);
           Statistiques.incrementerRessources();
           if (r.getQuantite() == 0) {
            terrain.viderCase(lig, col); 
        }
        

    }
    }
    public void planter(int lig,int col){
         Ressource r=terrain.getCase(lig, col);
         if(terrain.caseEstVide(lig, col)){
             terrain.setCase(lig, col, new Herbiers("Herbiers",1));
             
      
    }
    }
    public String toString(){//pas besoin de super vu qu'il sont protected 
        return "Nom: "+nom+" Position:("+x+","+y+")"+" Vie: "+enVie;
    }
}