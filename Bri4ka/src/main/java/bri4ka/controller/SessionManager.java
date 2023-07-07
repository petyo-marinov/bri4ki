package bri4ka.controller;

import bri4ka.exceptions.AuthorizationException;
import bri4ka.exceptions.NotAcceptableException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;


@Component
public class SessionManager {
    static final String LOGGED_IN = "in";

    public boolean isUserLoggedIn(HttpSession session){
        if(session.isNew() || session.getAttribute(LOGGED_IN) == null) {
            return false;
        }
        return true;
    }

    public void userLogsIn(HttpSession session, int userId){
        session.setAttribute(LOGGED_IN, userId);
    }

    public void userLogsOut(HttpSession session){
        if(session.getAttribute(LOGGED_IN ) == null){
            throw new NotAcceptableException("You already logged out.");
        }
        session.setAttribute(LOGGED_IN, null);
    }

    public int authorizeLogin(HttpSession session, int userId, String msg){
        if(!isUserLoggedIn(session)){
            throw new AuthorizationException("you must log in first.");
        }
        if((int)session.getAttribute(LOGGED_IN) != userId){
            throw new AuthorizationException(msg);
        }
        return (int) session.getAttribute(LOGGED_IN);
    }
    public int authorizeLogin(HttpSession session){
        if(!isUserLoggedIn(session)){
            throw new AuthorizationException("you must log in first.");
        }
        return (int) session.getAttribute(LOGGED_IN);
    }


    public Object getAttribute(HttpSession session) {
        return session.getAttribute(LOGGED_IN);
    }
}

