package bo.com.cognos.java.proyecto.soap;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.ScCatalogoItem;
import bo.com.cognos.java.proyecto.model.ScCatalogoItemSin;
import bo.com.cognos.java.proyecto.model.Token;
import bo.com.cognos.java.proyecto.model.UbicacionesGeograficas;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemService;
import bo.com.cognos.java.proyecto.services.ScCatalogoItemSinService;
import bo.com.cognos.java.proyecto.services.TokenService;
import bo.com.cognos.java.proyecto.services.UsuarioService;
import bo.com.cognos.java.proyecto.vo.ScCatalogoItemResponseVo;
import bo.com.cognos.java.proyecto.vo.ScCatalogoItemSinResponseVo;

@WebService
@HandlerChain(file = "handlers.xml")
public class ProyectoService {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    TokenService tokenService;
    @Autowired
    ScCatalogoItemService scCatalogoItemService;
    @Autowired
    ScCatalogoItemSinService scCatalogoItemSinService;
    @Resource
    WebServiceContext wsContext;

    @WebMethod(operationName = "CrearUsuario")
    public Usuario crearUsuario(@WebParam(name = "usuario") @XmlElement(required = true) Usuario usuario)
            throws ProyectoException {
        usuario.setApiTransaccion("CREAR");
        usuario.setApiEstado("CREADO");
        usuario.setUsuCre("rest-admin");
        return usuarioService.guardar(usuario);
    }

    @WebResult(name = "ListarUsuarios")
    public List<Usuario> listarUsuarios(@WebParam(name = "filtro") @XmlElement(required = true) String filtro)
            throws ProyectoException {
        return usuarioService.buscar(filtro);
    }

    @WebResult(name = "BorrarUsuario")
    public String borrarUsuario(@WebParam(name = "idUsuario") @XmlElement(required = true) Integer idUsuario) throws ProyectoException {
        usuarioService.borrar(idUsuario);
        return "OK";
    }

    @WebResult(name = "ActualizarUsuario")
    public Usuario actualizarUsuario(@WebParam(name = "usuario") @XmlElement(required = true) Usuario usuario) throws ProyectoException {
        usuario.setApiTransaccion("MODIFICAR");
        usuario.setApiEstado("CREADO");
        usuario.setUsuMod("rest-admin");
        return usuarioService.guardar(usuario);
    }

    //items
    @WebMethod(operationName = "CrearItem")
    public ScCatalogoItem crearItem(@WebParam(name = "item") @XmlElement(required = true) ScCatalogoItem item)
            throws ProyectoException {
        item.setApiTransaccion("CREAR");
        item.setApiEstado("CREADO");
        item.setUsuCre("soap-admin");
        return scCatalogoItemService.guardar(item);
    }

    @WebResult(name = "ListarItems")
    public List<ScCatalogoItem> listarItems(
            @WebParam(name = "filtro") @XmlElement(required = true) String filtro)
            throws ProyectoException {
        return scCatalogoItemService.buscar(filtro);
    }

    @WebResult(name = "BorrarItem")
    public String borrarItem(
            @WebParam(name = "idItem") @XmlElement(required = true) Long idItem)
            throws ProyectoException {
        scCatalogoItemService.borrar(idItem);
        return "OK";
    }

    @WebResult(name = "ActualizarItem")
    public ScCatalogoItem actualizarItem(
            @WebParam(name = "item") @XmlElement(required = true) ScCatalogoItem item)
            throws ProyectoException {
        ScCatalogoItem itemMod = scCatalogoItemService.obtener(item.getId());

        itemMod.setCodigoItem(item.getCodigoItem());
        itemMod.setDescItem(item.getDescItem());
        itemMod.setApiTransaccion("MODIFICAR");
        itemMod.setUsuMod("soap-admin");
        itemMod.setApiEstado("CREADO");
        return scCatalogoItemService.guardar(itemMod);
    }

    //Sinonimos
    @WebMethod(operationName = "CrearSinonimo")
    public ScCatalogoItemSin crearSinonimo(
            @WebParam(name = "sinonimo") @XmlElement(required = true) ScCatalogoItemSinResponseVo sin)
            throws ProyectoException {
        ScCatalogoItemSin sinonimo = new ScCatalogoItemSin();
        sinonimo.setApiTransaccion("CREAR");
        sinonimo.setApiEstado("CREADO");
        sinonimo.setUsuCre("soap-admin");
        sinonimo.setDescSinonimo(sin.getDescSinonimo());
        sinonimo.setIdUbigeo(new UbicacionesGeograficas(sin.getIdUbigeo().getIdUbigeo()));
        sinonimo.setIdItem(new ScCatalogoItem(sin.getIdItem().getIdItem()));
        return scCatalogoItemSinService.guardar(sinonimo);
    }

    @WebResult(name = "ListarSinonimos")
    public List<ScCatalogoItemSin> listarSinonimos(
            @WebParam(name = "filtro") @XmlElement(required = true) String filtro)
            throws ProyectoException {
        return scCatalogoItemSinService.buscar(filtro);
    }

    @WebResult(name = "BorrarSinonimo")
    public String borrarSinonimo(
            @WebParam(name = "idSinonimo") @XmlElement(required = true) Long idSinonimo)
            throws ProyectoException {
        scCatalogoItemSinService.borrar(idSinonimo);
        return "OK";
    }

    @WebResult(name = "ActualizarSinonimo")
    public ScCatalogoItemSin actualizarSinonimo(
            @WebParam(name = "sinonimo") @XmlElement(required = true) ScCatalogoItemSinResponseVo sin)
            throws ProyectoException {
        ScCatalogoItemSin sinMod = scCatalogoItemSinService.obtener(sin.getIdSinonimo());

        sinMod.setDescSinonimo(sin.getDescSinonimo());
        sinMod.setIdUbigeo(new UbicacionesGeograficas(sin.getIdUbigeo().getIdUbigeo()));
        sinMod.setApiTransaccion("MODIFICAR");
        sinMod.setUsuMod("soap-admin");
        sinMod.setApiEstado("CREADO");
        return scCatalogoItemSinService.guardar(sinMod);
    }

    //
    @WebResult(name = "Login")
    public String login(
            @WebParam(name = "login") @XmlElement(required = true) String login,
            @WebParam(name = "password") @XmlElement(required = true) String password) throws ProyectoException {
        Usuario usuario = usuarioService.login(login, password);
        Token token = tokenService.generarToken(usuario);
        return token.getToken();
    }

    @PostConstruct
    @WebMethod(exclude = true)
    public void init() {
        WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext) wsContext.getMessageContext().
                get(MessageContext.SERVLET_CONTEXT)).getAutowireCapableBeanFactory().
                autowireBean(this);
    }

}
