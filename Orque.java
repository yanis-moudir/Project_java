import java.util.ArrayList;

public class Orque extends AgentsCarnivores{

    private boolean proieAcote;
    public Orque(int x,int y,Terrain terrain){
        super(x, y, terrain);
        proieAcote=false;

    }
    public void setProieAcote(ArrayList<AgentsHerbivores> herbivores){
        proieAcote = false;
        for(AgentsHerbivores h:herbivores){
            if(Math.abs(this.x-h.x)<=1 &&  Math.abs(this.y-h.y)<=1  ){
                proieAcote=true;
            }

        }
        
    }
    public boolean getProieAcote(){return proieAcote; }

    public String toString(){
        return "Orque "+super.toString();
    }
}