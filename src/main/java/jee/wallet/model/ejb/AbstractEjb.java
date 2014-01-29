package jee.wallet.model.ejb;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractEjb {

    public static final String PROXY_HOST = "charon.olympe";
    public static final String PROXY_POST = "3128";
    public static final String PROXY_USER = "bissoqu1";
    public static final String PROXY_PASS = "Omegas27";

    static {
        System.setProperty("http.proxyHost", PROXY_HOST);
        System.setProperty("http.proxyPort", PROXY_POST);
        System.setProperty("http.proxySet", "true");
        System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");
        final String authUser = PROXY_USER;
        final String authPassword = PROXY_PASS;
        Authenticator.setDefault(new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(authUser, authPassword
                        .toCharArray());
            }
        });
        System.setProperty("http.proxyUser", authUser);
        System.setProperty("http.proxyPassword", authPassword);

    }
    @PersistenceContext
    protected EntityManager em;

    protected Query setParameters(Query q, Map<String, Object> params) {
        for (String s : params.keySet()) {
            q.setParameter(s, params.get(s));
        }
        return q;
    }

}
