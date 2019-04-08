package bo.com.cognos.java.proyecto.view.jsf;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.model.XXXModel;
import bo.com.cognos.java.proyecto.services.XXXService;
import bo.com.cognos.java.proyecto.view.jsf.util.DatosSesion;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class XXXBean<T extends XXXModel<ID>, ID extends Number> {
    
    abstract XXXService<T, ID> getService();
    T item;
    List<T> items;
    Class<T> clazz;
    boolean editando;
    boolean nuevo;
    boolean muestraDetalle;
    boolean operacionExitosa;
    String filtro;
    Date fechaInicial;
    Date fechaFinal;
    
    public XXXBean(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    Logger log = Logger.getLogger(this.getClass());
    
    public void cancelar() {
        this.muestraDetalle = false;
    }
    
    public void mostrar(T item) {
        log.trace("Mostrar� item: " + item);
        this.item = item;
        this.muestraDetalle = true;
        this.editando = false;
    }
    
    public void editar(T item) {
        this.item = item;
        this.muestraDetalle = true;
        this.editando = true;
        this.nuevo = false;
    }
    
    public void crearNuevo() {
        this.item = XXXModelInstantiator.instantiate(clazz);
        this.muestraDetalle = true;
        this.editando = true;
        this.nuevo = true;
    }
    
    public void buscar() {
        try {
            if (this.fechaInicial != null && this.fechaFinal != null) {
                this.items = getService().buscar(filtro, fechaInicial, fechaFinal);
            } else {
                this.items = getService().buscar(filtro);
            }
        } catch (ProyectoException e) {
            log.error("Error al buscar item", e);
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMensajeUsuario(), e.getMensajeUsuario()));
        }
    }
    
    public void eliminar() {
        try {
            log.debug("Intentar� eliminar: " + this.item);
            getService().borrar(this.item.getId());
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Borrado exitoso", "Borrado exitoso"));
            this.editando = false;
            this.muestraDetalle = false;
            this.buscar();
        } catch (ProyectoException e) {
            log.error("Error al eliminar el item", e);
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMensajeUsuario(), e.getMensajeUsuario()));
        }
    }
    
    public void guardar() {
        try {
            if (log.isDebugEnabled()) {
                log.debug("Intentar� guardar: " + this.item);
            }
            
            Usuario usuario = DatosSesion.getDatosUsuarioBean();
            if (this.item.getId() != null) {
                this.item.setApiTransaccion("MODIFICAR");
                this.item.setUsuMod(usuario.getLogin());
            } else {
                this.item.setApiTransaccion("CREAR");
                this.item.setUsuCre(usuario.getLogin());
            }
            this.item.setApiEstado("CREADO");
            getService().guardar(this.item);
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Operaci�n exitosa", "Operaci�n exitosa"));
            this.editando = false;
            this.muestraDetalle = false;
            this.buscar();
        } catch (ProyectoException e) {
            log.error("Error al guardar item", e);
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            e.getMensajeUsuario(), e.getMensajeUsuario()));
        }
    }
    
}
