package bo.com.cognos.java.proyecto.vo;

/**
 *
 * @author DellPedro
 */
public class ScCatalogoItemResponseVo {

    private Long idItem;
    private String codigoItem;
    private String descItem;
    private boolean editando;

    public ScCatalogoItemResponseVo() {
    }

    public ScCatalogoItemResponseVo(Long idItem, String codigoItem, String descItem) {
        this.idItem = idItem;
        this.codigoItem = codigoItem;
        this.descItem = descItem;
    }

    public ScCatalogoItemResponseVo(Long idItem, String codigoItem, String descItem, boolean editando) {
        this.idItem = idItem;
        this.codigoItem = codigoItem;
        this.descItem = descItem;
        this.editando = editando;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getDescItem() {
        return descItem;
    }

    public void setDescItem(String descItem) {
        this.descItem = descItem;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

}
