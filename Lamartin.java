import java.util.ArrayList;

public class Lamartin extends AgentsHerbivores{

    private boolean herbeAcote;
    private int ligHerbe;
    private int colHerbe;   

    public Lamartin(String nom,int x,int y,Terrain terrain){
        super(nom, x, y, terrain);
        herbeAcote=false;
    }
    public void setHerbeAcote(ArrayList<Ressource> ressource){

        herbeAcote=false;
        for(Ressource r:ressource){
            if(r instanceof Herbiers && Math.abs(this.x-r.getLigne())<=1 && Math.abs(this.y-r.getColonne())<=1){
                herbeAcote=true;
                ligHerbe=r.getLigne();
                colHerbe=r.getColonne();    
            }
        }
        if (herbeAcote==false){
           do {
    ligHerbe = (int)(Math.random() * terrain.nbLignes)+1;
    colHerbe = (int)(Math.random() * terrain.nbColonnes)+1;
} while (!terrain.caseEstVide(ligHerbe, colHerbe));
       
}
}

 boolean getHerbeAcote(){return herbeAcote;}
    public int getLigneHerbe(){return ligHerbe;}
    public int getColonneHerbe(){return colHerbe;}
    public String toString(){
        return "Lamartin "+super.toString();
    }
}