/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.com.cognos.java.proyecto.resources;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.ScCatalogoItem;
import bo.com.cognos.java.proyecto.model.ScCatalogoItemSin;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemService;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemSinService;
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
@Path("sinonimos")
public class SinonimosResource {

    @Autowired
    ScCatalogoItemSinService scCatalogoItemSinService;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<ScCatalogoItemSin> listarTodos(@QueryParam("filtro") String filtro) throws ProyectoException {
        return scCatalogoItemSinService.buscar(filtro);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,})
    public ScCatalogoItemSin crear(ScCatalogoItemSin sin) throws ProyectoException {
        sin.setApiTransaccion("CREAR");
        sin.setApiEstado("CREADO");
        sin.setUsuCre("rest-admin");
        return scCatalogoItemSinService.guardar(sin);
    }

    @Path("modifica")
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ScCatalogoItemSin modificar(ScCatalogoItemSin sin) throws ProyectoException {
        ScCatalogoItemSin sinMod = scCatalogoItemSinService.obtener(sin.getId());

        sinMod.setDescSinonimo(sin.getDescSinonimo());
        sinMod.setIdUbigeo(sin.getIdUbigeo());
        sinMod.setApiTransaccion("MODIFICAR");
        sinMod.setUsuMod("rest-admin");
        sinMod.setApiEstado("CREADO");
        return scCatalogoItemSinService.guardar(sinMod);
    }

    @Path("activa")
    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ScCatalogoItemSin activar(ScCatalogoItemSin sin) throws ProyectoException {
        ScCatalogoItemSin sinMod = scCatalogoItemSinService.obtener(sin.getId());
        sinMod.setApiTransaccion("ACTIVAR");
        sinMod.setApiEstado("ACTIVO");
        sinMod.setUsuMod("rest-admin");
        return scCatalogoItemSinService.guardar(sinMod);
    }

    @Path("/{idSinonimo}")
    @DELETE
    public void eliminar(@PathParam("idSinonimo") Long idSinonimo) throws ProyectoException {
        scCatalogoItemSinService.borrar(idSinonimo);
    }
}
