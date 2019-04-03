package bo.com.cognos.java.proyecto.vo;

/**
 *
 * @author DellPedro
 */
public class UbicacionesGeograficasResponseVo {

    private Long idUbigeo;
    private int ubigeo;
    private String descUbigeo;

    public UbicacionesGeograficasResponseVo() {
    }

    public UbicacionesGeograficasResponseVo(Long idUbigeo, int ubigeo, String descUbigeo) {
        this.idUbigeo = idUbigeo;
        this.ubigeo = ubigeo;
        this.descUbigeo = descUbigeo;
    }

    public Long getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(Long idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public int getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(int ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getDescUbigeo() {
        return descUbigeo;
    }

    public void setDescUbigeo(String descUbigeo) {
        this.descUbigeo = descUbigeo;
    }

    @Override
    public String toString() {
        return "UbicacionesGeograficasResponseVo{" + "idUbigeo=" + idUbigeo + ", ubigeo=" + ubigeo + ", descUbigeo=" + descUbigeo + '}';
    }

}
