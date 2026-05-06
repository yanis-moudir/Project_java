public abstract class Herbes extends Ressource{

    protected static double probGeneration=0.3;

    public Herbes(String type,int quantite){
        super(type,quantite);

    }
    public abstract void evoluer();




} 