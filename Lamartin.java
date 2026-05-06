import java.util.ArrayList;

public class Lamartin extends AgentsHerbivores{

    private boolean herbeAcote;

    public Lamartin(String nom,int x,int y,Terrain terrain){
        super(nom, x, y, terrain);
        herbeAcote=false;
    }
    public void setHerbeAcote(ArrayList<Ressource> ressource){

        herbeAcote=false;
        for(Ressource r:ressource){
            if(r instanceof Herbiers && Math.abs(this.x-r.getLigne())<=1 && Math.abs(this.y-r.getColonne())<=1){
                herbeAcote=true;
            }
        }
       
    }
    public boolean getHerbeAcote(){return herbeAcote;}
    public String toString(){
        return "Lamartin "+super.toString();
    }
}