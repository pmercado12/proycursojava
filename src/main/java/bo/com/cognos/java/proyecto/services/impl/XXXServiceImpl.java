package bo.com.cognos.java.proyecto.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.model.XXXModel;
import bo.com.cognos.java.proyecto.repositories.XXXRepository;
import bo.com.cognos.java.proyecto.services.XXXService;

@Transactional(readOnly=true)
public abstract class XXXServiceImpl<T extends XXXModel<ID>,
	ID extends Number> implements XXXService<T, ID>  {

	abstract XXXRepository<T, ID> getRepository();
	abstract void validarAlta(T obj) throws ProyectoException;
	abstract void validarModificacion(T objAnterior, T objNuevo) throws ProyectoException;
	abstract void validarBorrado(final T obj) throws ProyectoException;
	
	@Override
	public T obtener(ID id) throws ProyectoException {
		return getRepository().findById(id).orElse(null);
	}

	@Override
	public List<T> buscar(String filtro) throws ProyectoException {
		filtro = "%" + filtro + "%";	
		return getRepository().buscar(filtro);
	}

	@Override
	public List<T> buscar(String filtro, Date fechaInicial, Date fechaFinal) throws ProyectoException {
		filtro = "%" + filtro + "%";	
		return getRepository().buscarPorRangoFecha(filtro, fechaInicial, fechaFinal);
	}

	Logger log = Logger.getLogger(this.getClass());

	@Override
	@Transactional(rollbackFor=ProyectoException.class)
	public void borrar(ID id) throws ProyectoException {
		T item = this.obtener(id);
		validarBorrado(item);
		item.setFechaBaja(new Date());
		getRepository().saveAndFlush(item);
	}
	
	@Override
	@Transactional(rollbackFor=ProyectoException.class)
	public T guardar(T item) throws ProyectoException {
		log.debug("Intenta guardar: " + item);
		// Validaciones antes guardar...
		if(item.getId() == null) {
			validarAlta(item);
			item.setFechaAlta(new Date());
		}
		else {
			T itemExistente = getRepository().findById(item.getId()).orElse(null);
			validarModificacion(itemExistente, item);
		}
		item.setFechaModificacion(new Date());
		log.debug("Guardar�: " + item);
		getRepository().saveAndFlush(item);
		return item;
	}

}
