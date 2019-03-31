package bo.com.cognos.java.proyecto.view.jsf;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.ScCatalogoItem;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemService;
import bo.com.cognos.java.proyecto.services.XXXService;
import bo.com.cognos.java.proyecto.vo.ScCatalogoItemResponseVo;
import java.util.ArrayList;
import java.util.List;
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
public class ItemBean extends XXXBean<ScCatalogoItem, Long> {

    private List<ScCatalogoItemResponseVo> itemsResponse;
    
    @ManagedProperty("#{scCatalogoItemServiceImpl}")
    ScCatalogoItemService scCatalogoItemService;

    Logger log = Logger.getLogger(this.getClass());

    public ItemBean() {
        super(ScCatalogoItem.class);
    }

    @Override
    XXXService<ScCatalogoItem, Long> getService() {
        return scCatalogoItemService;
    }

    @PostConstruct
    public void init() {
        log.debug("INICIANDO", null);
        itemsResponse = new ArrayList<ScCatalogoItemResponseVo>();
        itemsResponse.add(new ScCatalogoItemResponseVo(1L, "10000000", "Animales"));
        itemsResponse.add(new ScCatalogoItemResponseVo(2L, "11000000", "Plantas"));
        itemsResponse.add(new ScCatalogoItemResponseVo(3L, "12000000", "Minerales"));
    }

    public void nuevo() throws ProyectoException {
        itemsResponse.add(new ScCatalogoItemResponseVo(null, null, null, true));

        //buscar
        List<ScCatalogoItem> listaOriginal = scCatalogoItemService.buscar("");
        this.itemsResponse = new ArrayList<ScCatalogoItemResponseVo>();
        for (ScCatalogoItem scCatalogoItem : listaOriginal) {
            itemsResponse.add(new ScCatalogoItemResponseVo(
                    scCatalogoItem.getId(),
                    scCatalogoItem.getCodigoItem(),
                    scCatalogoItem.getDescItem())
            );
        }
    }

    public void editar(ScCatalogoItemResponseVo item) {
        item.setEditando(true);
    }

    public void cancelar(ScCatalogoItemResponseVo item) {
        item.setEditando(false);
        //TODO Recuperar de base el original, si es nuevo eliminar de la lista
        if (item.getIdItem() == null) {
            this.items.remove(item);
        }
    }

    public void actualizar(ScCatalogoItemResponseVo item) {
        item.setEditando(false);
    }
}
