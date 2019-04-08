package bo.com.cognos.java.proyecto.view.jsf;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.ScCatalogoItem;
import bo.com.cognos.java.proyecto.model.ScCatalogoItemSin;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemService;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemSinService;
import bo.com.cognos.java.proyecto.services.XXXService;
import bo.com.cognos.java.proyecto.view.jsf.util.DatosSesion;
import bo.com.cognos.java.proyecto.vo.ScCatalogoItemResponseVo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

/**
 *
 * @author DellPedro
 */
@ManagedBean
@ViewScoped
@Getter
@Setter
public class ItemBean /*extends XXXBean<ScCatalogoItem, Long>*/ {

    private List<ScCatalogoItemResponseVo> itemsResponse;
    private String filtro;
    @ManagedProperty("#{scCatalogoItemServiceImpl}")
    ScCatalogoItemService scCatalogoItemService;

    @ManagedProperty("#{scCatalogoItemSinServiceImpl}")
    ScCatalogoItemSinService scCatalogoItemSinService;

    Logger log = Logger.getLogger(this.getClass());

    /*public ItemBean() {
        super(ScCatalogoItem.class);
    }

    @Override
    XXXService<ScCatalogoItem, Long> getService() {
        return scCatalogoItemService;
    }*/
    @PostConstruct
    public void init() {
        log.debug("iniciando itembean???/", null);
        try {
            this.buscarItems();
        } catch (ProyectoException ex) {

        }
    }

    public void buscarItems() throws ProyectoException {
        //buscar
        filtro = filtro == null ? "" : filtro;
        List<ScCatalogoItem> listaOriginal = scCatalogoItemService.buscar(filtro);
        this.itemsResponse = new ArrayList<ScCatalogoItemResponseVo>();
        for (ScCatalogoItem scCatalogoItem : listaOriginal) {
            itemsResponse.add(new ScCatalogoItemResponseVo(
                    scCatalogoItem.getId(),
                    scCatalogoItem.getCodigoItem(),
                    scCatalogoItem.getDescItem())
            );
        }
    }

    public void nuevo() {
        itemsResponse.add(new ScCatalogoItemResponseVo(null, null, null, true));
    }

    public void editar(ScCatalogoItemResponseVo item) {
        item.setEditando(true);
    }

    public void cancelar(ScCatalogoItemResponseVo item) throws ProyectoException {
        if (item.getIdItem() == null) {
            this.itemsResponse.remove(item);
        } else {
            this.actualizaItemView(item, this.scCatalogoItemService.obtener(item.getIdItem()));
        }
        item.setEditando(false);
    }

    public void guardarItem(ScCatalogoItemResponseVo itemView) {
        try {
            ScCatalogoItem item;
            Usuario usuario = DatosSesion.getDatosUsuarioBean();

            if (itemView.getIdItem() == null) {
                item = new ScCatalogoItem();
                item.setApiTransaccion("CREAR");
                item.setUsuCre(usuario.getLogin());
            } else {
                item = this.scCatalogoItemService.obtener(itemView.getIdItem());
                item.setUsuMod(usuario.getLogin());
                item.setApiTransaccion("MODIFICAR");
            }
            item.setId(itemView.getIdItem());
            item.setCodigoItem(itemView.getCodigoItem());
            item.setDescItem(itemView.getDescItem());

            item.setApiEstado("CREADO");

            item = this.scCatalogoItemService.guardar(item);
            actualizaItemView(itemView, item);
            itemView.setEditando(false);
        } catch (ProyectoException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMensajeUsuario(), e.getMensajeUsuario()));
        }

    }

    public void eliminarItem(ScCatalogoItemResponseVo itemView) {
        try {
            this.scCatalogoItemService.borrar(itemView.getIdItem());
            this.itemsResponse.remove(itemView);

            List<ScCatalogoItemSin> listaSinonimos = scCatalogoItemSinService.buscarPorItem(itemView.getIdItem());

            for (ScCatalogoItemSin sin : listaSinonimos) {
                scCatalogoItemSinService.borrar(sin.getId());
            }
        } catch (ProyectoException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMensajeUsuario(), e.getMensajeUsuario()));
        }

    }

    public void actualizaItemView(ScCatalogoItemResponseVo itemView, ScCatalogoItem item) {
        itemView.setIdItem(item.getId());
        itemView.setCodigoItem(item.getCodigoItem());
        itemView.setDescItem(item.getDescItem());
    }
}
