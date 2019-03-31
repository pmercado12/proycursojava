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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
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
            query = "select u from ScCatalogoItem u where u.descItem like :filtro")
    ,@NamedQuery(name = "ScCatalogoItem.buscarPorRangoFecha",
            query = "select u from ScCatalogoItem u ")
})
public class ScCatalogoItem extends XXXModel<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_ITEM", nullable = false)
    private Long id;
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

    @Override
    public String toString() {
        return "ScCatalogoItem{" + "idItem=" + id + ", codigoItem=" + codigoItem + ", descItem=" + descItem + ", descDefinicion=" + descDefinicion + ", keyEfectivo=" + keyEfectivo + ", apiEstado=" + apiEstado + '}';
    }

}
