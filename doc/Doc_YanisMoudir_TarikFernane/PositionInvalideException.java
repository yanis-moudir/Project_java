/**
 * Exception levee quand un agent tente de se deplacer 
 * vers une position qui n'est pas valide sur le terrain 
 * par exemple en dehors des limites de terrain .
 */
public class PositionInvalideException extends Exception{
    /**
     * Construire une nouvelle exception avec un message d'erreur .
     * @param Message message décrivant l'erreur et sa cause 
     */
    public PositionInvalideException(String Message){
        super(Message);

    }
}




