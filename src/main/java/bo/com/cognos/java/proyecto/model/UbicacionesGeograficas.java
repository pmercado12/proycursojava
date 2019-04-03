package bo.com.cognos.java.proyecto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DellPedro
 */
@Entity
@Table(name = "UBICACIONES_GEOGRAFICAS")
@XmlRootElement
public class UbicacionesGeograficas extends XXXModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_UBIGEO", nullable = false)
    private Long id;
    @Column(name = "UBIGEO", nullable = false)
    private int ubigeo;
    @Column(name = "DESC_UBIGEO", nullable = false, length = 110)
    private String descUbigeo;
    @Column(name = "SIGLA_UBIGEO", nullable = false, length = 15)
    private String siglaUbigeo;
    @Column(name = "NIVEL", nullable = false, length = 10)
    private String nivel;
    @OneToMany(mappedBy = "idPadre", fetch = FetchType.LAZY)
    private List<UbicacionesGeograficas> ubicacionesGeograficasList;
    @JoinColumn(name = "ID_PADRE", referencedColumnName = "ID_UBIGEO")
    @ManyToOne(fetch = FetchType.LAZY)
    private UbicacionesGeograficas idPadre;

    public UbicacionesGeograficas() {
    }

    public UbicacionesGeograficas(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSiglaUbigeo() {
        return siglaUbigeo;
    }

    public void setSiglaUbigeo(String siglaUbigeo) {
        this.siglaUbigeo = siglaUbigeo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<UbicacionesGeograficas> getUbicacionesGeograficasList() {
        return ubicacionesGeograficasList;
    }

    public void setUbicacionesGeograficasList(
            List<UbicacionesGeograficas> ubicacionesGeograficasList) {
        this.ubicacionesGeograficasList = ubicacionesGeograficasList;
    }

    public UbicacionesGeograficas getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(UbicacionesGeograficas idPadre) {
        this.idPadre = idPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UbicacionesGeograficas)) {
            return false;
        }
        UbicacionesGeograficas other = (UbicacionesGeograficas) object;
        if ((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UbicacionesGeograficas{" + "id=" + id + ", ubigeo=" + ubigeo + ", descUbigeo=" + descUbigeo + '}';
    }

}
