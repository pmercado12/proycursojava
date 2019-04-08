package bo.com.cognos.java.proyecto.view.jsf;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.ScCatalogoItem;
import bo.com.cognos.java.proyecto.model.ScCatalogoItemSin;
import bo.com.cognos.java.proyecto.model.UbicacionesGeograficas;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemSinService;
import bo.com.cognos.java.proyecto.view.jsf.util.DatosSesion;
import bo.com.cognos.java.proyecto.vo.ScCatalogoItemResponseVo;
import bo.com.cognos.java.proyecto.vo.ScCatalogoItemSinResponseVo;
import bo.com.cognos.java.proyecto.vo.UbicacionesGeograficasResponseVo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;

/**
 *
 * @author DellPedro
 */
@ManagedBean
@ViewScoped
@Getter
@Setter
public class SinonimosBean {

    private List<ScCatalogoItemSinResponseVo> listaSinonimos = new ArrayList<ScCatalogoItemSinResponseVo>();
    private ScCatalogoItemResponseVo item;
    @ManagedProperty("#{scCatalogoItemSinServiceImpl}")
    ScCatalogoItemSinService scCatalogoItemSinService;

    @PostConstruct
    public void init() {
    }

    public void agregar(ScCatalogoItemResponseVo item) {
        this.item = item;
        List<ScCatalogoItemSin> lista = scCatalogoItemSinService.buscarPorItem(item.getIdItem());
        listaSinonimos.clear();
        for (ScCatalogoItemSin scCatalogoItemSin : lista) {
            listaSinonimos.add(transformarSinonimo(scCatalogoItemSin));
        }
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlg1').show()");
    }

    public void nuevo() {
        listaSinonimos.add(new ScCatalogoItemSinResponseVo(true));
    }

    public void editar(ScCatalogoItemSinResponseVo sinonimo) {
        sinonimo.setEditando(true);
    }

    public void cancelar(ScCatalogoItemSinResponseVo sinonimo) throws ProyectoException {
        if (sinonimo.getIdSinonimo() == null) {
            this.listaSinonimos.remove(sinonimo);
        } else {
            this.actualizaSinView(sinonimo, this.scCatalogoItemSinService.obtener(sinonimo.getIdSinonimo()));
        }
        sinonimo.setEditando(false);
    }

    public void guardar(ScCatalogoItemSinResponseVo sinView) {

        try {
            ScCatalogoItemSin sinonimo;
            Usuario usuario = DatosSesion.getDatosUsuarioBean();

            if (sinView.getIdSinonimo() == null) {
                sinonimo = new ScCatalogoItemSin();
                sinonimo.setApiTransaccion("CREAR");
                sinonimo.setUsuCre(usuario.getLogin());
            } else {
                sinonimo = this.scCatalogoItemSinService.obtener(sinView.getIdSinonimo());
                sinonimo.setApiTransaccion("MODIFICAR");
                sinonimo.setUsuMod(usuario.getLogin());
            }

            ScCatalogoItem item = new ScCatalogoItem(this.item.getIdItem());

            sinonimo.setId(sinView.getIdSinonimo());
            sinonimo.setDescSinonimo(sinView.getDescSinonimo());
            sinonimo.setIdItem(item);
            sinonimo.setIdUbigeo(new UbicacionesGeograficas(sinView.getIdUbigeo().getIdUbigeo()));
            sinonimo.setApiEstado("CREADO");
            sinonimo = this.scCatalogoItemSinService.guardar(sinonimo);
            System.out.println("sinonimo creado:::" + sinonimo.toString());
            actualizaSinView(sinView, sinonimo);

            sinView.setEditando(false);
        } catch (ProyectoException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMensajeUsuario(), e.getMensajeUsuario()));
        }

    }

    public void eliminar(ScCatalogoItemSinResponseVo sinonimo) {
        try {
            this.scCatalogoItemSinService.borrar(sinonimo.getIdSinonimo());
            this.listaSinonimos.remove(sinonimo);
        } catch (ProyectoException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMensajeUsuario(), e.getMensajeUsuario()));
        }

    }

    public void actualizaSinView(ScCatalogoItemSinResponseVo sinView, ScCatalogoItemSin sinonimo) {
        UbicacionesGeograficas ubicacion = sinonimo.getIdUbigeo();
        sinView.setIdUbigeo(new UbicacionesGeograficasResponseVo(ubicacion.getId(), ubicacion.getUbigeo(), ubicacion.getDescUbigeo()));
        sinView.setDescSinonimo(sinonimo.getDescSinonimo());
    }

    public ScCatalogoItemSinResponseVo transformarSinonimo(ScCatalogoItemSin sinonimo) {
        ScCatalogoItemSinResponseVo respuesta = new ScCatalogoItemSinResponseVo();
        respuesta.setApiEstado(sinonimo.getApiEstado());
        respuesta.setDescSinonimo(sinonimo.getDescSinonimo());
        respuesta.setEditando(false);
        respuesta.setIdSinonimo(sinonimo.getId());
        respuesta.setIdUbigeo(transformarUbicacion(sinonimo.getIdUbigeo()));
        return respuesta;
    }

    public UbicacionesGeograficasResponseVo transformarUbicacion(UbicacionesGeograficas ubigeo) {
        UbicacionesGeograficasResponseVo ubicacion = new UbicacionesGeograficasResponseVo();
        ubicacion.setDescUbigeo(ubigeo.getDescUbigeo());
        ubicacion.setIdUbigeo(ubigeo.getId());
        ubicacion.setUbigeo(ubigeo.getUbigeo());
        return ubicacion;
    }
}
