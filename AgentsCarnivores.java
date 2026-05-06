public class AgentsCarnivores extends Agent implements OutilsCarnivores{

    protected boolean enChasse;

    public AgentsCarnivores(int x,int y,Terrain terrain){
        super(x, y, terrain);
        enChasse=false;
    }
    public void chasser(){}
    public void manger(int lig,int col){}

    public String toString(){
        return " Position:("+x+","+y+")"+" Vie: "+enVie+" En Chasse :"+enChasse;
    }
}