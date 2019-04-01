package bo.com.cognos.java.proyecto.view.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.services.UsuarioService;
import bo.com.cognos.java.proyecto.services.XXXService;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
// public class UsuarioBean {
public class UsuarioBean extends XXXBean<Usuario, Integer> {

    @ManagedProperty("#{usuarioServiceImpl}")
    UsuarioService usuarioService;    
   
    public UsuarioBean() {
        super(Usuario.class);
        //this.buscar();
        log.error("constructor", null);
    }
    
    @PostConstruct
    public void init(){
        log.debug("initpostconstruct", null);
        this.buscar();
        log.debug("length::" + this.items.size(), null);
    }
    
    
    
    @Override
    XXXService<Usuario, Integer> getService() {
        return usuarioService;
    }
    // Logger log = Logger.getLogger(UsuarioBean.class);

//	private boolean muestraDetalle;
//	private boolean editando;
//	private boolean nuevo;
//	private String filtro;
//	private List<Usuario> usuarios;
//	private Usuario usuario;
//	
//	public void cancelar() {
//		this.muestraDetalle = false;
//	}
//	public void mostrar(Usuario usuario) {
//		log.trace("Mostrará usuario: " + usuario);
//		this.usuario = usuario;
//		this.muestraDetalle = true;
//		this.editando = false;
//	}
//	public void editar(Usuario usuario) {
//		this.usuario = usuario;
//		this.muestraDetalle = true;
//		this.editando = true;
//		this.nuevo = false;
//	}
//	public void crearNuevo() {
//		this.usuario = new Usuario();
//		this.muestraDetalle = true;
//		this.editando = true;
//		this.nuevo = true;
//	}
//	public void buscar() {
//		try {
//			this.usuarios = usuarioService.buscar(filtro);
//		} catch (ProyectoException e) {
//			log.error("Error al guardar usuario", e);
//			FacesContext.getCurrentInstance().
//			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
//					e.getMensajeUsuario(), e.getMensajeUsuario()));
//		}		
//	}
//	public void eliminar() {
//		try {
//			usuarioService.borrar(this.usuario.getId());
//			FacesContext.getCurrentInstance().
//			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
//					"Borrado exitoso", "Borrado exitoso"));
//			this.editando = false;
//			this.muestraDetalle = false;
//			this.buscar();
//		} catch (ProyectoException e) {
//			log.error("Error al eliminar el usuario", e);
//			FacesContext.getCurrentInstance().
//			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
//					e.getMensajeUsuario(), e.getMensajeUsuario()));
//		}		
//	}
//	
//	public void guardar() {
//		try {
//			if(log.isDebugEnabled()) {
//				log.debug("Intentará guardar: " + this.usuario);	
//			}
//			usuarioService.guardar(this.usuario);
//			FacesContext.getCurrentInstance().
//			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
//					"Operación exitosa", "Operación exitosa"));
//			this.editando = false;
//			this.muestraDetalle = false;
//			this.buscar();
//		} catch (ProyectoException e) {
//			log.error("Error al guardar usuario", e);
//			FacesContext.getCurrentInstance().
//			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
//					e.getMensajeUsuario(), e.getMensajeUsuario()));
//		}
//	}
}
