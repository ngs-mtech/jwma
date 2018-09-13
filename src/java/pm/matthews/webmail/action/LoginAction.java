package pm.matthews.webmail.action;

import javax.servlet.ServletContext;
import pm.matthews.webmail.Init;
import javax.servlet.http.Cookie;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.log4j.Logger;
import pm.matthews.webmail.JwmaAuthenticator;
import pm.matthews.webmail.Preference;
import pm.matthews.webmail.SerialPref;
import pm.matthews.webmail.Datastore;

public class LoginAction extends BaseAction{
    private String password, username;
    private JwmaAuthenticator auth;
    private static Logger log = Logger.getLogger(LoginAction.class);
    private boolean remember, mobile;
    
    @DefaultHandler
    public Resolution login(){
        doInit();
        Resolution res;
        String  host = getSessionValue("mail_host");
        String protocol = getSessionValue("access_protocol");
        String port = getSessionValue("port");     
        auth = new JwmaAuthenticator(username, password, host, protocol, port);
        boolean authenticated = false;
        try{
            authenticated = auth.connectServer();
        }
        catch(javax.mail.NoSuchProviderException nspex){
            log.debug(nspex);
        }
        if(authenticated){
            Preference pref = null;
            String language = getSessionValue("language");
            String persistence = getSessionValue("persistence");
            if(persistence.equals("serialize")){
                String home = getSessionValue("home");
                pref = new SerialPref(home, username, language);
            }
            else{
                pref = new Datastore(persistence, username, language);
                setSessionValue("datastore", pref);
            }
            jession = auth.createUserSession(pref);
            setSessionValue("jession", jession);
            if(mobile){
                setCookie();
                jession.processor = 1;
                jession.sortOrder = 1;
                jession.mobile = true;
                res = new ForwardResolution("Folder.jwma").addParameter("folder", "INBOX");
            }
            else{
                jession.mobile = false;
                if(remember){
                    setCookie();
                }
                keepAlive = (String)getSessionValue("keep_alive");
                res = new ForwardResolution("/WEB-INF/desktop/folder.jsp");
            }
        }
        else{
           if(username != null){
                log.warn("FAILED login from " + getContext().getRequest().getRemoteHost());
           }  
           res = new RedirectResolution("/index.jsp");
        }
        return res;
    }
    
    private void doInit(){ 
        /*we try and buid a file path from WEB-INF
         *this fails if it's a non-exploded war file*/
    	ServletContext sc = context.getServletContext();
        
        String path = sc.getRealPath("/WEB-INF");
        if(path == null){
            path = "";
        }
        
        Init in = new Init(path);
        String[] conf = in.readConfig();
        for(int i = 0;i<conf.length;i++){
            setSessionValue(conf[i], conf[i+1]);
            i++;
        }
        String logLevel = (String)getSessionValue("log_level");
        in.prepareLogging(logLevel);        
    }
    
    private void setCookie(){
        log.info("trying to set COOKIE for " + username );
        Cookie userCookie = new Cookie("USER", username);
        getContext().getResponse().addCookie(userCookie);
    }
          
    public void setUsername(String u){username = u;}
    public void setPassword(String p){password = p;}
    public void setRemember(boolean r){remember = r;}
    public void setMobile(boolean m){mobile = m;}
}
