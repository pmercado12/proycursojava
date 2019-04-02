package bo.com.cognos.java.proyecto.vo;

/**
 *
 * @author DellPedro
 */
public class ScCatalogoItemSinResponseVo {

    private Long idSinonimo;
    private UbicacionesGeograficasResponseVo idUbigeo;
    private String descSinonimo;
    private String apiEstado;
    private boolean editando;

    public ScCatalogoItemSinResponseVo() {
    }

    public ScCatalogoItemSinResponseVo(boolean editando) {
        this.editando = editando;
    }

    public Long getIdSinonimo() {
        return idSinonimo;
    }

    public UbicacionesGeograficasResponseVo getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(UbicacionesGeograficasResponseVo idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public void setIdSinonimo(Long idSinonimo) {
        this.idSinonimo = idSinonimo;
    }

    public String getDescSinonimo() {
        return descSinonimo;
    }

    public void setDescSinonimo(String descSinonimo) {
        this.descSinonimo = descSinonimo;
    }

    public String getApiEstado() {
        return apiEstado;
    }

    public void setApiEstado(String apiEstado) {
        this.apiEstado = apiEstado;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

}
