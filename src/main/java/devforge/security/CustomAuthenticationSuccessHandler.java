package devforge.security;

import devforge.model.Usuario;
import devforge.servicio.UsuarioServicio;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UsuarioServicio usuarioServicio;

    public CustomAuthenticationSuccessHandler(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        Usuario usuario = usuarioServicio.obtenerPorUsername(username);

        if (usuario.getRol() == Usuario.Rol.SUPER_ADMIN) {
            // Para SUPER_ADMIN, redirigir a la selección de licorería
            setDefaultTargetUrl("/licorerias/seleccionar");
        } else {
            // Para otros roles, redirigir directamente al dashboard
            // La licorería se seleccionará automáticamente en el interceptor
            setDefaultTargetUrl("/dashboard");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
} 