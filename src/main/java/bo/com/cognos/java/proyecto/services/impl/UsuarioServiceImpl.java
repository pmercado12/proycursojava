package bo.com.cognos.java.proyecto.services.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bo.com.cognos.java.proyecto.model.ProyectoException;
import bo.com.cognos.java.proyecto.model.Usuario;
import bo.com.cognos.java.proyecto.repositories.UsuarioRepository;
import bo.com.cognos.java.proyecto.repositories.XXXRepository;
import bo.com.cognos.java.proyecto.services.UsuarioService;

@Service
@Transactional(readOnly = true)
public class UsuarioServiceImpl extends XXXServiceImpl<Usuario, Integer>
        implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public Usuario login(String login, String password) throws ProyectoException {
        Usuario usuario = repository.buscarPorLogin(login);
        if (usuario == null) {
            throw new ProyectoException(1001, "Usuario no existe");
        }
        if (!usuario.isHabilitado()) {
            throw new ProyectoException(1002, "Usuario bloqueado");
        }
        if (!usuario.getPassword().contentEquals(this.transformarPassword(login, password))) {
            throw new ProyectoException(1003, "Usuario o contraseña incorrecta");
        }
        return usuario;
    }

    private String obtenerHash(String textoOriginal, String algoritmo) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algoritmo);
            md.update(textoOriginal.getBytes());
            byte[] bytes = md.digest();
            String hash = new BigInteger(1, bytes).toString(16);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String transformarPassword(String login, String password) {
        String hash1 = this.obtenerHash(login + password, "MD5");
        String hash2 = this.obtenerHash(hash1, "SHA1");
        return hash2;
    }

//	public static void main(String[] args) {
//		UsuarioServiceImpl x = new UsuarioServiceImpl();
//		String hash= x.obtenerHash("..", "SHA1");
//		System.out.println("Hash: " + hash);
//	}
    @Override
    XXXRepository<Usuario, Integer> getRepository() {
        return repository;
    }

    @Override
    void validarAlta(Usuario obj) throws ProyectoException {
        Usuario existente = repository.buscarPorLogin(obj.getLogin());
        if (existente != null) {
            throw new ProyectoException(523, "Ya existe un usuario con ese login.");
        }

        // después de pasar tooodas las validaciones, transforma el password
        obj.setPassword(this.transformarPassword(obj.getLogin(), obj.getPassword()));
    }

    @Override
    void validarModificacion(Usuario objAnterior, Usuario objNuevo) throws ProyectoException {
        if (!objAnterior.getLogin().equals(objNuevo.getLogin())) {
            throw new ProyectoException(305, "No puede cambiar el login de un usuario existente.");
        }
    }

    @Override
    void validarBorrado(Usuario obj) throws ProyectoException {
        // .....	

    }

}
