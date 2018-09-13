package pm.matthews.webmail.ext;

import org.apache.log4j.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.exception.ExceptionHandler;
import pm.matthews.webmail.JwmaSession;

public class JwmaExceptionHandler implements ExceptionHandler{
    private static Logger log = Logger.getLogger(JwmaExceptionHandler.class);
    
    public void handle(Throwable ex, HttpServletRequest req, HttpServletResponse resp)
                       throws ServletException, IOException{
        if(ex.toString().equals("java.lang.NullPointerException")){
             //req.getRequestDispatcher("/index.jsp").forward(req, resp);
             resp.sendRedirect("Init.jwma");
        }
        else{
            log.error(ex);
            HttpSession session = req.getSession();
            JwmaSession jession =  (JwmaSession)session.getAttribute("jession");
            String view = "/WEB-INF/desktop/error.jsp";
            if(jession.mobile){
                view = "/WEB-INF/mobile/error.jsp";
            }
            req.getRequestDispatcher(view).forward(req, resp);
        }
    }
    
    public void init(Configuration config){ }
}
