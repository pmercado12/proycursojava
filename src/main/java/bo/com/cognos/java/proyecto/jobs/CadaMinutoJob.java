package bo.com.cognos.java.proyecto.jobs;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.services.UsuarioService;

public class CadaMinutoJob {

	@Autowired
	UsuarioService usuarioService;
	
	Logger log = Logger.getLogger(CadaMinutoJob.class);
	
	
}
