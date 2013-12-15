package fr.univ_rouen.bd.model.forms;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Interface de tous les formulaires de l'application
 * Il est nécessaire d'implémenter la gestion des erreurs
 * @author bissoqu1
 */
public interface Form<E> {

    /**
     * Méthode retournant toutes les erreurs qui ont eut lieu lors de la validation du formulaire
     */
    Map<String, List<String>> getErrors();

    /**
     * Méthode retournant le résultat de la validation sors forme de chaîne affichage dans un message à l'écart des erreurs de validation
     */
    String getResult();
    
    /**
     * Méthode permettant de valider le contenu d'une requête pour vérifier sa conformité selon les validateurs choisit
     * @return L'objet de type E validé ou non
     */
    E validateForm(HttpServletRequest request);

	/**
	* Accesseur permettant de savoir si les informations contenues dans la requête sont valides ou non
	*/
    boolean isValid();
}
