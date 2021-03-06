package bo.com.cognos.java.proyecto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author DellPedro
 */
@Entity
@Table(name = "SC_CATALOGO_ITEM")
@XmlRootElement
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "ScCatalogoItem.buscar",
            query = "select u from ScCatalogoItem u where (u.descItem like :filtro or u.codigoItem like :filtro)")
    ,@NamedQuery(name = "ScCatalogoItem.buscarPorRangoFecha",
            query = "select u from ScCatalogoItem u ")
})
public class ScCatalogoItem extends XXXModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM")
    private Long id;
    @Column(name = "ID_ENTIDAD_UTILIZA")
    private Long idEntidadUtiliza;
    //@Column(nullable = false)
    private short nivel;
    @Column(name = "CODIGO_ITEM", length = 12)
    private String codigoItem;
    @Column(name = "DESC_ITEM", nullable = false, length = 1000)
    private String descItem;
    @Column(name = "DESC_DEFINICION", length = 2000)
    private String descDefinicion;
    @Column(name = "KEY_EFECTIVO")
    private Long keyEfectivo;
    @Column(length = 1)
    private String apropiable;
    @Column(name = "APROPIABLE_PAC", length = 1)
    private String apropiablePac;
    @Column(name = "MOTIVO_ESTADO_ITEM", length = 100)
    private String motivoEstadoItem;
    @Column(name = "FECHA_ACTIVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActivacion;
    @Column(name = "FECHA_INACTIVACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInactivacion;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "idItem", fetch = FetchType.LAZY)
    private List<ScCatalogoItemSin> scCatalogoItemSinList;
    @OneToMany(mappedBy = "idItemPadre", fetch = FetchType.LAZY)
    private List<ScCatalogoItem> scCatalogoItemList;
    @JoinColumn(name = "ID_ITEM_PADRE", referencedColumnName = "ID_ITEM")
    @ManyToOne(fetch = FetchType.LAZY)
    private ScCatalogoItem idItemPadre;

    public ScCatalogoItem() {
    }

    public ScCatalogoItem(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ScCatalogoItem{" + "idItem=" + id + ", codigoItem=" + codigoItem + ", descItem=" + descItem + ", descDefinicion=" + descDefinicion + ", keyEfectivo=" + keyEfectivo + '}';
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
    
    

}
