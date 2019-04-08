package bo.com.cognos.java.proyecto.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pmercado
 */
@Entity
@Table(name = "SC_CATALOGO_ITEM_SIN")
@XmlRootElement
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "ScCatalogoItemSin.buscar",
            query = "select u from ScCatalogoItemSin u where (u.descSinonimo like :filtro)")
    ,@NamedQuery(name = "ScCatalogoItemSin.buscarPorRangoFecha",
            query = "select u from ScCatalogoItemSin u ")
    ,@NamedQuery(name = "ScCatalogoItemSin.buscarPorItem",
            query = "select u from ScCatalogoItemSin u where idItem.id = :idItem")
})
public class ScCatalogoItemSin extends XXXModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SINONIMO", nullable = false)
    private Long id;
    @JoinColumn(name = "ID_UBIGEO", referencedColumnName = "ID_UBIGEO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UbicacionesGeograficas idUbigeo;
    @Column(name = "DESC_SINONIMO", nullable = false, length = 500)
    private String descSinonimo;
    @Column(name = "MOTIVO_ESTADO_SINONIMO", length = 100)
    private String motivoEstadoSinonimo;
    @Column(name = "FECHA_ACTIVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActivacion;
    @Column(name = "FECHA_INACTIVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInactivacion;
    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ScCatalogoItem idItem;

    public ScCatalogoItemSin() {
    }

    public ScCatalogoItemSin(Long id) {
        this.id = id;
    }

    @XmlTransient
    public UbicacionesGeograficas getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(UbicacionesGeograficas idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    @XmlTransient
    public ScCatalogoItem getIdItem() {
        return idItem;
    }

    public void setIdItem(ScCatalogoItem idItem) {
        this.idItem = idItem;
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
        if (!(object instanceof ScCatalogoItemSin)) {
            return false;
        }
        ScCatalogoItemSin other = (ScCatalogoItemSin) object;
        if ((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScCatalogoItemSin{" + "idSinonimo=" + id + ", idUbigeo=" + idUbigeo + ", descSinonimo=" + descSinonimo + '}';
    }

}
