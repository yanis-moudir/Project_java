public class Herbiers extends Herbes{

    public Herbiers(String type,int quantite){
        super(type, quantite);
    }
    public void evoluer(){
        if(Math.random()<probGeneration){
            this.setQuantite(this.getQuantite()+1);
        }
    }
    public Herbiers clone(){
        return new Herbiers(this.type,this.getQuantite());
    }

    public String toString(){
        return "type: "+type+" Qauntite: "+getQuantite();
    }
}