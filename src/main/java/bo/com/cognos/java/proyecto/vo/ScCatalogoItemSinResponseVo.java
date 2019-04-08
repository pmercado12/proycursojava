package bo.com.cognos.java.proyecto.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author DellPedro
 */
@Getter
@Setter
@ToString
public class ScCatalogoItemSinResponseVo {

    private Long idSinonimo;
    private UbicacionesGeograficasResponseVo idUbigeo;
    private ScCatalogoItemResponseVo idItem;
    private String descSinonimo;
    private String apiEstado;
    private boolean editando;

    public ScCatalogoItemSinResponseVo() {
    }

    public ScCatalogoItemSinResponseVo(boolean editando) {
        this.editando = editando;
    }
}
