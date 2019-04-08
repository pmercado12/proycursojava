package bo.com.cognos.java.proyecto.view.jsf.filter;

import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.view.jsf.util.DatosSesion;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Verifica si ya se escribió la variable de sesión
        // "sesion"
        Usuario sesion = DatosSesion.getDatosUsuario(request);
        if (sesion != null) {
            // Autorizamos...
            chain.doFilter(request, response);
        } else {
            // Restringimos el acceso...
            String redireccion = ((HttpServletRequest) request).getContextPath()
                    + "/login.jsf";
            ((HttpServletResponse) response).sendRedirect(redireccion);
        }
    }

    public void destroy() {
        // TODO Auto-generated method stub

    }

}
