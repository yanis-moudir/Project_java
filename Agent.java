public abstract class Agent{
    protected int x,y;
    protected boolean enVie;
    protected Terrain terrain;

    public Agent(int x,int y,Terrain terrain){
        this.x=x;
        this.y=y;
        enVie=true;
        this.terrain=terrain;

    }
    public double  distance(int lig,int col){
        return(Math.sqrt(Math.pow(Math.abs(x-lig), 2)+Math.pow(Math.abs(y-col), 2)));
    }
    public void seDeplacer(int lig,int col) throws PositionInvalideException{

        if (!terrain.sontValides(lig,col)){
            throw new PositionInvalideException("Position Invalide !!");
        }
        x=lig;
        y=col;
    }
}