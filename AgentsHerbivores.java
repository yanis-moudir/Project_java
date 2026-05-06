public class AgentsHerbivores extends Agent implements OutilsHerbivores{

    protected String nom;

    public AgentsHerbivores(String nom,int x,int y,Terrain terrain){
        super(x, y, terrain);
        this.nom=nom;
    }
    public void recolter(int lig,int col){

    }
    public void planter(int lig,int col){
        
    }
    public String toString(){//pas besoin de super vu qu'il sont protected 
        return "Nom: "+nom+" Position:("+x+","+y+")"+" Vie: "+enVie;
    }
}