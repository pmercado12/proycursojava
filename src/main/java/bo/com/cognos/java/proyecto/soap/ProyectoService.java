package bo.com.cognos.java.proyecto.soap;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.Token;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.services.TokenService;
import bo.com.cognos.java.proyecto.services.UsuarioService;

@WebService
@HandlerChain(file="handlers.xml")
public class ProyectoService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	TokenService tokenService;
	
	public String saludar(String nombre) {
		return "Hola: " + nombre;
	}
	
	@WebMethod(operationName="CrearUsuario")
	public Usuario crearUsuario(@WebParam(name="usuario")@XmlElement(required=true) Usuario usuario) 
			throws ProyectoException {
		return usuarioService.guardar(usuario);
	}
	
	@WebResult(name="usuarios")
	public List<Usuario> listarUsuarios(String filtro) 
			throws ProyectoException{
		return usuarioService.buscar(filtro);
	}
	
	public String borrarUsuario(Integer idUsuario) throws ProyectoException {
		usuarioService.borrar(idUsuario);
		return "OK";
	}
	
	public Usuario actualizarUsuario(Usuario usuario) throws ProyectoException {
		return usuarioService.guardar(usuario);
	}
	
	@Resource
	WebServiceContext wsContext;
	
	@PostConstruct
	@WebMethod(exclude=true)
	public void init() {
		WebApplicationContextUtils.getRequiredWebApplicationContext
		((ServletContext)wsContext.getMessageContext().
				get(MessageContext.SERVLET_CONTEXT)).getAutowireCapableBeanFactory().
		autowireBean(this); 
	}
	
	public String login(String login, String password) throws ProyectoException {
		Usuario usuario = usuarioService.login(login, password);
		Token token = tokenService.generarToken(usuario);
		return token.getToken();
	}
	
}
