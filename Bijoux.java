public abstract class Bijoux extends Ressource{
    protected int valeur;

    public abstract void evaluer();

    public Bijoux(String type, int quantite) {
    super(type, quantite);
    valeur = 10;
}



}