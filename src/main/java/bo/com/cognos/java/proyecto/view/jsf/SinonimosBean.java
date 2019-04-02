package bo.com.cognos.java.proyecto.view.jsf;

import bo.com.cognos.java.proyecto.vo.ScCatalogoItemResponseVo;
import bo.com.cognos.java.proyecto.vo.ScCatalogoItemSinResponseVo;
import bo.com.cognos.java.proyecto.vo.UbicacionesGeograficasResponseVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

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
    private List<UbicacionesGeograficasResponseVo> ubicacionesGeograficas = new ArrayList<UbicacionesGeograficasResponseVo>();

    @PostConstruct
    public void init() {
        ubicacionesGeograficas.add(new UbicacionesGeograficasResponseVo(1l, 1, "Chuquisaca"));
        ubicacionesGeograficas.add(new UbicacionesGeograficasResponseVo(2l, 2, "La Paz"));
    }

    public void agregar(ScCatalogoItemResponseVo item) {
        this.item = item;
    }

    public void nuevo() {
        listaSinonimos.add(new ScCatalogoItemSinResponseVo(true));
    }

    public void editar(ScCatalogoItemSinResponseVo sinonimo) {
        sinonimo.setEditando(true);
    }

    public void cancelar(ScCatalogoItemSinResponseVo sinonimo) {
        sinonimo.setEditando(false);
    }

    public void guardar(ScCatalogoItemSinResponseVo sinonimo) {
        sinonimo.setEditando(true);
    }

    public void eliminar(ScCatalogoItemSinResponseVo sinonimo) {

    }
}
