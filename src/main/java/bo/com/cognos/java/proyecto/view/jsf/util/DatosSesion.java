/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.com.cognos.java.proyecto.view.jsf.util;

import bo.com.cognos.java.proyecto.model.Usuario;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DellPedro
 */
public class DatosSesion {

    public static Usuario getDatosUsuario(ServletRequest request) {
        Usuario sesion = (Usuario) ((HttpServletRequest) request).
                getSession().getAttribute("usuariosesion");
        return sesion;
    }

    public static Usuario getDatosUsuarioBean() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuariosesion");
        if(usuario==null || usuario.getLogin().length()==0){
            return new Usuario("admin2","admin2");
        }
        return usuario;
    }
}
