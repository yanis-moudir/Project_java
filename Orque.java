import java.util.ArrayList;

public class Orque extends AgentsCarnivores{

    private boolean proieAcote;
    private int ligProie;
    private int colProie;
    public Orque(int x,int y,Terrain terrain){
        super(x, y, terrain);
        proieAcote=false;

    }
    public void setProieAcote(ArrayList<AgentsHerbivores> herbivores){
        proieAcote = false;
        for(AgentsHerbivores h:herbivores){
            if(Math.abs(this.x-h.x)<=1 &&  Math.abs(this.y-h.y)<=1  ){
                proieAcote=true;    
                ligProie=h.x;
                colProie=h.y;       
            }

        }
        if (proieAcote==false){
           do {
    ligProie = (int)(Math.random() * terrain.nbLignes);
    colProie = (int)(Math.random() * terrain.nbColonnes);
} while (!terrain.caseEstVide(ligProie, colProie));
        }
}
        
    public boolean getProieAcote(){return proieAcote; }
    public int getLigneProie(){return ligProie;}
    public int getColonneProie(){return colProie;}

    public String toString(){
        return "Orque "+super.toString();
    }
}