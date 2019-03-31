package bo.com.cognos.java.proyecto.view.jsf;

import bo.com.cognos.java.proyecto.vo.ScCatalogoItemResponseVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author DellPedro
 */
@ManagedBean
@ViewScoped
public class ItemBean {

    private List<ScCatalogoItemResponseVo> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<ScCatalogoItemResponseVo>();
        items.add(new ScCatalogoItemResponseVo(1L, "10000000", "Animales"));
        items.add(new ScCatalogoItemResponseVo(2L, "11000000", "Plantas"));
        items.add(new ScCatalogoItemResponseVo(3L, "12000000", "Minerales"));
    }

    public void nuevo() {
        items.add(new ScCatalogoItemResponseVo(null, null, null, true));
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

    public List<ScCatalogoItemResponseVo> getItems() {
        return items;
    }

    public void setItems(List<ScCatalogoItemResponseVo> items) {
        this.items = items;
    }

}
