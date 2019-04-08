package bo.com.cognos.java.proyecto.resources;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.ScCatalogoItem;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemService;
import bo.com.cognos.java.proyecto.services.TokenService;
import bo.com.cognos.java.proyecto.services.UsuarioService;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author DellPedro
 */
@Path("items")
public class ItemResource {

    @Autowired
    ScCatalogoItemService scCatalogoItemService;
    @Autowired
    TokenService tokenService;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<ScCatalogoItem> listarTodos(@QueryParam("filtro") String filtro) throws ProyectoException {
        return scCatalogoItemService.buscar(filtro);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,})
    public ScCatalogoItem crear(ScCatalogoItem item) throws ProyectoException {
        item.setApiTransaccion("CREAR");
        item.setApiEstado("CREADO");
        item.setUsuCre("rest-admin");
        return scCatalogoItemService.guardar(item);
    }

    @Path("modifica")
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ScCatalogoItem modificar(ScCatalogoItem item) throws ProyectoException {
        ScCatalogoItem itemMod = scCatalogoItemService.obtener(item.getId());
        
        itemMod.setCodigoItem(item.getCodigoItem());
        itemMod.setDescItem(item.getDescItem());
        itemMod.setApiTransaccion("MODIFICAR");
        itemMod.setUsuMod("rest-admin");
        itemMod.setApiEstado("CREADO");
        return scCatalogoItemService.guardar(itemMod);
    }

    @Path("activa")
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ScCatalogoItem activar(ScCatalogoItem item) throws ProyectoException {
        ScCatalogoItem itemMod = scCatalogoItemService.obtener(item.getId());
        itemMod.setApiTransaccion("ACTIVAR");
        itemMod.setApiEstado("ACTIVO");
        itemMod.setUsuMod("rest-admin");
        return scCatalogoItemService.guardar(itemMod);
    }

    @Path("/{idItem}")
    @DELETE
    public void eliminar(@PathParam("idItem") Long idItem) throws ProyectoException {
        scCatalogoItemService.borrar(idItem);
    }
}
