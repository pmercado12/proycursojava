package bo.com.cognos.java.proyecto.services;

import java.util.Date;
import java.util.List;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.XXXModel;

public interface XXXService<T extends XXXModel, ID extends Number> {

    public T obtener(ID id) throws ProyectoException;

    public List<T> buscar(String filtro) throws ProyectoException;

    public List<T> buscar(String filtro, Date fechaInicial,
            Date fechaFinal) throws ProyectoException;

    public void borrar(ID id) throws ProyectoException;

    public T guardar(T obj) throws ProyectoException;

    public void validarApi(T obj) throws ProyectoException;
    
    public void validarApiMod(T obj) throws ProyectoException;

}
