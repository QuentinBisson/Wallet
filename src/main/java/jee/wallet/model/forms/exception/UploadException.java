package fr.univ_rouen.bd.model.forms.exception;

public class UploadException extends RuntimeException {
    /*
     * Constructeurs
     */
    public UploadException( String message ) {
        super( message );
    }
 
    public UploadException( String message, Throwable cause ) {
        super( message, cause );
    }
 
    public UploadException( Throwable cause ) {
        super( cause );
    }
}
