package bo.com.cognos.java.proyecto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DellPedro
 */
@Entity
@Table(name = "SC_CATALOGO_ITEM")
@XmlRootElement
public class ScCatalogoItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_ITEM", nullable = false)
    private Long idItem;
    @Column(name = "ID_ENTIDAD_UTILIZA")
    private Long idEntidadUtiliza;
    @Column(nullable = false)
    private short nivel;
    @Column(name = "CODIGO_ITEM", length = 12)
    private String codigoItem;
    @Column(name = "DESC_ITEM", nullable = false, length = 1000)
    private String descItem;
    @Column(name = "DESC_DEFINICION", length = 2000)
    private String descDefinicion;
    @Column(name = "KEY_EFECTIVO")
    private Long keyEfectivo;
    @Column(nullable = false, length = 1)
    private String apropiable;
    @Column(name = "APROPIABLE_PAC", nullable = false, length = 1)
    private String apropiablePac;
    @Column(name = "MOTIVO_ESTADO_ITEM", length = 100)
    private String motivoEstadoItem;
    @Column(name = "FECHA_ACTIVACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActivacion;
    @Column(name = "FECHA_INACTIVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInactivacion;
    @Column(name = "API_ESTADO", nullable = false, length = 25)
    private String apiEstado;
    @Column(name = "API_TRANSACCION", nullable = false, length = 30)
    private String apiTransaccion;
    @Column(name = "USU_CRE", nullable = false, length = 30)
    private String usuCre;
    @Column(name = "FEC_CRE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCre;
    @Column(name = "USU_MOD", length = 30)
    private String usuMod;
    @Column(name = "FEC_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecMod;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "idItem", fetch = FetchType.LAZY)
    private List<ScCatalogoItemSin> scCatalogoItemSinList;
    @OneToMany(mappedBy = "idItemPadre", fetch = FetchType.LAZY)
    private List<ScCatalogoItem> scCatalogoItemList;
    @JoinColumn(name = "ID_ITEM_PADRE", referencedColumnName = "ID_ITEM")
    @ManyToOne(fetch = FetchType.LAZY)
    private ScCatalogoItem idItemPadre;

    public ScCatalogoItem() {
    }

    public ScCatalogoItem(Long idItem) {
        this.idItem = idItem;
    }

    public ScCatalogoItem(Long idItem, short nivel, String descItem, String apropiable,
            String apropiablePac, Date fechaActivacion, String apiEstado, String apiTransaccion,
            String usuCre, Date fecCre) {
        this.idItem = idItem;
        this.nivel = nivel;
        this.descItem = descItem;
        this.apropiable = apropiable;
        this.apropiablePac = apropiablePac;
        this.fechaActivacion = fechaActivacion;
        this.apiEstado = apiEstado;
        this.apiTransaccion = apiTransaccion;
        this.usuCre = usuCre;
        this.fecCre = fecCre;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    @XmlElement(nillable = false)
    public Long getIdEntidadUtiliza() {
        return idEntidadUtiliza;
    }

    public void setIdEntidadUtiliza(Long idEntidadUtiliza) {
        this.idEntidadUtiliza = idEntidadUtiliza;
    }

    @XmlElement(nillable = false)
    public short getNivel() {
        return nivel;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }

    @XmlElement(nillable = false)
    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    @XmlElement(nillable = false)
    public String getDescItem() {
        return descItem;
    }

    public void setDescItem(String descItem) {
        this.descItem = descItem;
    }

    @XmlElement(nillable = false)
    public String getDescDefinicion() {
        return descDefinicion;
    }

    public void setDescDefinicion(String descDefinicion) {
        this.descDefinicion = descDefinicion;
    }

    @XmlElement(nillable = false)
    public Long getKeyEfectivo() {
        return keyEfectivo;
    }

    public void setKeyEfectivo(Long keyEfectivo) {
        this.keyEfectivo = keyEfectivo;
    }

    @XmlTransient
    public String getApropiable() {
        return apropiable;
    }

    public void setApropiable(String apropiable) {
        this.apropiable = apropiable;
    }

    @XmlTransient
    public String getApropiablePac() {
        return apropiablePac;
    }

    public void setApropiablePac(String apropiablePac) {
        this.apropiablePac = apropiablePac;
    }

    @XmlElement(nillable = false)
    public String getMotivoEstadoItem() {
        return motivoEstadoItem;
    }

    public void setMotivoEstadoItem(String motivoEstadoItem) {
        this.motivoEstadoItem = motivoEstadoItem;
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
    public List<ScCatalogoItemSin> getScCatalogoItemSinList() {
        return scCatalogoItemSinList;
    }

    public void setScCatalogoItemSinList(List<ScCatalogoItemSin> scCatalogoItemSinList) {
        this.scCatalogoItemSinList = scCatalogoItemSinList;
    }

    @XmlTransient
    public List<ScCatalogoItem> getScCatalogoItemList() {
        return scCatalogoItemList;
    }

    public void setScCatalogoItemList(List<ScCatalogoItem> scCatalogoItemList) {
        this.scCatalogoItemList = scCatalogoItemList;
    }

    @XmlElement(nillable = false)
    public ScCatalogoItem getIdItemPadre() {
        return idItemPadre;
    }

    public void setIdItemPadre(ScCatalogoItem idItemPadre) {
        this.idItemPadre = idItemPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScCatalogoItem)) {
            return false;
        }
        ScCatalogoItem other = (ScCatalogoItem) object;
        if ((this.idItem == null && other.idItem != null)
                || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ScCatalogoItem{" + "idItem=" + idItem + ", codigoItem=" + codigoItem + ", descItem=" + descItem + ", descDefinicion=" + descDefinicion + ", keyEfectivo=" + keyEfectivo + ", apiEstado=" + apiEstado + '}';
    }

}
