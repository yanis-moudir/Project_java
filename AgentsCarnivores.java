public class AgentsCarnivores extends Agent implements OutilsCarnivores{

    protected boolean enChasse;

    public AgentsCarnivores(int x,int y,Terrain terrain){
        super(x, y, terrain);
        enChasse=false;
    }
    public void chasser(){
        enChasse=true;
    }
    public void manger(int lig,int col){
        if(enChasse){
            try {
        seDeplacer(lig, col);
        enChasse = false;
    }   catch (PositionInvalideException e) {
        System.out.println(e.getMessage());
    }
}
    }

    public String toString(){
        return " Position:("+x+","+y+")"+" Vie: "+enVie+" En Chasse :"+enChasse;
    }
}