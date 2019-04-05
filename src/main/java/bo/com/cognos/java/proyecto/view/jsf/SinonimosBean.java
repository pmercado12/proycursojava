package bo.com.cognos.java.proyecto.view.jsf;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.ScCatalogoItem;
import bo.com.cognos.java.proyecto.model.ScCatalogoItemSin;
import bo.com.cognos.java.proyecto.model.UbicacionesGeograficas;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemSinService;
import bo.com.cognos.java.proyecto.vo.ScCatalogoItemResponseVo;
import bo.com.cognos.java.proyecto.vo.ScCatalogoItemSinResponseVo;
import bo.com.cognos.java.proyecto.vo.UbicacionesGeograficasResponseVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    @ManagedProperty("#{scCatalogoItemSinServiceImpl}")
    ScCatalogoItemSinService scCatalogoItemSinService;

    @PostConstruct
    public void init() {
        ubicacionesGeograficas.add(new UbicacionesGeograficasResponseVo(1l, 1, "CHUQUISACA"));
        ubicacionesGeograficas.add(new UbicacionesGeograficasResponseVo(53l, 2, "LA PAZ"));
    }

    public void agregar(ScCatalogoItemResponseVo item) {
        this.item = item;
        List<ScCatalogoItemSin> lista = scCatalogoItemSinService.buscarPorItem(item.getIdItem());
        listaSinonimos.clear();
        for (ScCatalogoItemSin scCatalogoItemSin : lista) {
            listaSinonimos.add(transformarSinonimo(scCatalogoItemSin));
        }
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

    public void guardar(ScCatalogoItemSinResponseVo sinView) throws ProyectoException {

        ScCatalogoItemSin sinonimo;

        if (sinView.getIdSinonimo() == null) {
            sinonimo = new ScCatalogoItemSin();
        } else {
            sinonimo = this.scCatalogoItemSinService.obtener(sinView.getIdSinonimo());
        }

        ScCatalogoItem item = new ScCatalogoItem(this.item.getIdItem());

        sinonimo.setId(sinView.getIdSinonimo());
        sinonimo.setDescSinonimo(sinView.getDescSinonimo());
        sinonimo.setIdItem(item);
        sinonimo.setIdUbigeo(new UbicacionesGeograficas(sinView.getIdUbigeo().getIdUbigeo()));

        sinonimo = this.scCatalogoItemSinService.guardar(sinonimo);
        actualizaSinView(sinView, sinonimo);

        sinView.setEditando(false);
    }

    public void eliminar(ScCatalogoItemSinResponseVo sinonimo) throws ProyectoException {
        this.scCatalogoItemSinService.borrar(sinonimo.getIdSinonimo());
        this.listaSinonimos.remove(sinonimo);
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
