/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.jamily.bookstoreweb.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import local.jamily.bookstoreweb.model.bean.User;

/**
 *
 * @author devsys-a
 */
@WebFilter(filterName = "AutorizaUserFilter", urlPatterns = {"/bstore/*", "/bsuser/*"})
public class AutorizaUserFilter implements Filter{

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO, "AutorizaUserFilter Iniciado!!!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        String servletPath = httpRequest.getServletPath();
        String actionPath = httpRequest.getPathInfo();
        
        //Carrega a session caso exista.
        HttpSession session = httpRequest.getSession(false);
        boolean isUsuarioLogado = (session != null && session.getAttribute("user") != null);
        boolean isUsuarioRegister = (servletPath.equals("/bsuser") && actionPath.equals("/new") || actionPath.equals("/register"));
        
        if(isUsuarioRegister){
            
            Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO, "******Entrou na exceção do cadastro*****");
            Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO, "Path SERVLET {0}", servletPath);
            Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO, "Path ACTION {0}", actionPath);
            chain.doFilter(request, response);
        }else if(isUsuarioLogado){
            //Tudo ok! Usuário com session autorizado e segue a requisição.
            User userLogado = (User) session.getAttribute("user");
            Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO, "Usuario Autentificado{0}", userLogado.getEmail());
            Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO, "Carrega proximo filtro ou servlet - chain.doFilter()");
            
            chain.doFilter(request, response);
            
        }else{
            //ops... usuario não autentificado: redirecionas para a página de login
            Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO, "Usuario NÃO autentificado!!!");
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
            dispatcher.forward(request, response);
        }
        
            Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO, "*** Pos-Filtro ***");
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO, "AutorizaUserFilter Destroi!!!");
        
    }
    
}
