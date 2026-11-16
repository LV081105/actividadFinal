package actividadFinal.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import actividadFinal.logica.ControladoraLogica;
import actividadFinal.logica.Genero;
import actividadFinal.logica.Plataforma;
import actividadFinal.logica.Videojuego;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvVideojuegoEditar", urlPatterns = { "/SvVideojuegoEditar" })
public class SvVideojuegoEditar extends HttpServlet {

    private static final String VISTA = "/WEB-INF/vistas/editarVideojuego.jsp";
    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Videojuego videojuego = control.buscarUnVideojuego(id);
            if (videojuego == null) {
                redirigirConError(request, response, "El videojuego con id " + id + " no existe.");
                return;
            }
            request.setAttribute("videojuego", videojuego);
            request.setAttribute("generos", control.listarGeneros());
            request.setAttribute("plataformas", control.listarPlataformas());
            request.getRequestDispatcher(VISTA).forward(request, response);
        } catch (NumberFormatException e) {
            redirigirConError(request, response, "El id del videojuego no es válido.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String destino = request.getContextPath() + "/SvVideojuegoListar";

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Videojuego videojuego = control.buscarUnVideojuego(id);
            if (videojuego == null) {
                throw new Exception("El videojuego con id " + id + " no existe.");
            }

            videojuego.setTitulo(request.getParameter("titulo"));
            videojuego.setAnioLanzamiento(Integer.parseInt(request.getParameter("anioLanzamiento")));
            videojuego.setPrecio(Double.parseDouble(request.getParameter("precio")));

            int generoId = Integer.parseInt(request.getParameter("generoId"));
            int plataformaId = Integer.parseInt(request.getParameter("plataformaId"));
            videojuego.setGenero(control.buscarUnGenero(generoId));
            videojuego.setPlataforma(control.buscarUnaPlataforma(plataformaId));

            control.editarVideojuego(videojuego);
        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("Los valores numéricos ingresados no son válidos.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }
        response.sendRedirect(destino);
    }

    private void redirigirConError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/SvVideojuegoListar?error=" + codificar(mensaje));
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}