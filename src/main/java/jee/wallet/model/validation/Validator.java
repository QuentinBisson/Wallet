package fr.univ_rouen.bd.model.validation;

import java.util.List;
import java.util.Map;

/**
 * Interface de base de tous les validateurs
 * Simplification des fonctions pour permettre à l'application d'être modulable 
 * @author bissoqu1
 */
public interface Validator<E> {
	
    /**
     * Méthode permettant de récupérer tous les messages de validatiosn ajoutés au cours de la validation
     * @return la liste de tous les messages sous la forme (errorName, List<String>)
     */ 
    Map<String, List<String>> getValidationMessages();

	/**
	 * Méthode retournant vrai si l'objet e est conforme au validateur
	 */
    boolean isValid(E e);

	/**
	 * Méthode permettant de mettre en place le nom du champ pour afficher un feedback à l'utilisateur en cas d'erreur
	 */
    void setFieldName(String s);
    
    /**
     * Méthode permettant de mettre le nom de l'erreur utilisable das la jsp pour le validateur
     */
    void setErrorName(String s);
}
