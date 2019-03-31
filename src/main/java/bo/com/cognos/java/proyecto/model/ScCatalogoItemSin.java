package bo.com.cognos.java.proyecto.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pmercado
 */
@Entity
@Table(name = "SC_CATALOGO_ITEM_SIN")
@XmlRootElement
public class ScCatalogoItemSin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_SINONIMO", nullable = false)
    private Long idSinonimo;
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
    @Column(name = "API_ESTADO", length = 25)
    private String apiEstado;    
    @Column(name = "API_TRANSACCION", length = 30)
    private String apiTransaccion;    
    @Column(name = "USU_CRE", length = 30)
    private String usuCre;    
    @Column(name = "FEC_CRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCre;
    @Column(name = "USU_MOD", length = 30)
    private String usuMod;
    @Column(name = "FEC_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecMod;
    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ScCatalogoItem idItem;

    public ScCatalogoItemSin() {
    }

    public ScCatalogoItemSin(Long idSinonimo) {
        this.idSinonimo = idSinonimo;
    }

    public ScCatalogoItemSin(Long idSinonimo, String descSinonimo, Date fechaActivacion,
            String apiEstado, String apiTransaccion, String usuCre, Date fecCre) {
        this.idSinonimo = idSinonimo;
        this.descSinonimo = descSinonimo;
        this.fechaActivacion = fechaActivacion;
        this.apiEstado = apiEstado;
        this.apiTransaccion = apiTransaccion;
        this.usuCre = usuCre;
        this.fecCre = fecCre;
    }

    public Long getIdSinonimo() {
        return idSinonimo;
    }

    public void setIdSinonimo(Long idSinonimo) {
        this.idSinonimo = idSinonimo;
    }

    public UbicacionesGeograficas getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(UbicacionesGeograficas idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public String getDescSinonimo() {
        return descSinonimo;
    }

    public void setDescSinonimo(String descSinonimo) {
        this.descSinonimo = descSinonimo;
    }

    @XmlElement(nillable = false)
    public String getMotivoEstadoSinonimo() {
        return motivoEstadoSinonimo;
    }

    public void setMotivoEstadoSinonimo(String motivoEstadoSinonimo) {
        this.motivoEstadoSinonimo = motivoEstadoSinonimo;
    }

    @XmlTransient
    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    @XmlTransient
    public Date getFechaInactivacion() {
        return fechaInactivacion;
    }

    public void setFechaInactivacion(Date fechaInactivacion) {
        this.fechaInactivacion = fechaInactivacion;
    }

    public String getApiEstado() {
        return apiEstado;
    }

    public void setApiEstado(String apiEstado) {
        this.apiEstado = apiEstado;
    }

    @XmlTransient
    public String getApiTransaccion() {
        return apiTransaccion;
    }

    public void setApiTransaccion(String apiTransaccion) {
        this.apiTransaccion = apiTransaccion;
    }

    @XmlTransient
    public String getUsuCre() {
        return usuCre;
    }

    public void setUsuCre(String usuCre) {
        this.usuCre = usuCre;
    }

    @XmlTransient
    public Date getFecCre() {
        return fecCre;
    }

    public void setFecCre(Date fecCre) {
        this.fecCre = fecCre;
    }

    @XmlTransient
    public String getUsuMod() {
        return usuMod;
    }

    public void setUsuMod(String usuMod) {
        this.usuMod = usuMod;
    }

    @XmlTransient
    public Date getFecMod() {
        return fecMod;
    }

    public void setFecMod(Date fecMod) {
        this.fecMod = fecMod;
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
        hash += (idSinonimo != null ? idSinonimo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScCatalogoItemSin)) {
            return false;
        }
        ScCatalogoItemSin other = (ScCatalogoItemSin) object;
        if ((this.idSinonimo == null && other.idSinonimo != null)
                || (this.idSinonimo != null && !this.idSinonimo.equals(other.idSinonimo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScCatalogoItemSin{" + "idSinonimo=" + idSinonimo + ", idUbigeo=" + idUbigeo + ", descSinonimo=" + descSinonimo + ", apiEstado=" + apiEstado + '}';
    }

}
