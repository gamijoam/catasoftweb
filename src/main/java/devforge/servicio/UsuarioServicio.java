package devforge.servicio;

import devforge.model.Usuario;
import java.util.List;

public interface UsuarioServicio {
    Usuario guardar(Usuario usuario);
    List<Usuario> listarUsuarios();
    List<Usuario> listarUsuariosPorLicoreria(Long licoreriaId);
    Usuario obtenerPorUsername(String username);
    void eliminar(Long id);
    Usuario obtenerPorId(Long id);
}