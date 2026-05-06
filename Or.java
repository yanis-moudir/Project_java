public class Or extends Bijoux{


    public Or(int quantite) {
    super("or", quantite);
}

    public void evaluer(){
        valeur=getQuantite()*10;
    }
    public Or clone(){
        return new Or(getQuantite());
    }
    public String toString(){
        return "Type: "+type+" Quantite:"+getQuantite()+" valeur:"+valeur;
    }
}