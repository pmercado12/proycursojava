package bo.com.cognos.java.proyecto.view.jsf;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.ScCatalogoItem;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemService;
import bo.com.cognos.java.proyecto.services.XXXService;
import bo.com.cognos.java.proyecto.vo.ScCatalogoItemResponseVo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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

    public void guardarItem(ScCatalogoItemResponseVo itemView) throws ProyectoException {
        ScCatalogoItem item;

        if (itemView.getIdItem() == null) {
            item = new ScCatalogoItem();
        } else {
            item = this.scCatalogoItemService.obtener(itemView.getIdItem());
        }
        item.setId(itemView.getIdItem());
        item.setCodigoItem(itemView.getCodigoItem());
        item.setDescItem(itemView.getDescItem());

        item = this.scCatalogoItemService.guardar(item);
        actualizaItemView(itemView, item);
        itemView.setEditando(false);
    }

    public void eliminarItem(ScCatalogoItemResponseVo itemView) throws ProyectoException {
        this.scCatalogoItemService.borrar(itemView.getIdItem());
    }

    public void actualizaItemView(ScCatalogoItemResponseVo itemView, ScCatalogoItem item) {
        itemView.setIdItem(item.getId());
        itemView.setCodigoItem(item.getCodigoItem());
        itemView.setDescItem(item.getDescItem());
    }
}
