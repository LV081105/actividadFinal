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

@WebServlet(name = "SvVideojuegoCrear", urlPatterns = { "/SvVideojuegoCrear" })
public class SvVideojuegoCrear extends HttpServlet {

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String destino = request.getContextPath() + "/SvVideojuegoListar";

        try {
            String titulo = request.getParameter("titulo");
            int anioLanzamiento = Integer.parseInt(request.getParameter("anioLanzamiento"));
            double precio = Double.parseDouble(request.getParameter("precio"));
            int generoId = Integer.parseInt(request.getParameter("generoId"));
            int plataformaId = Integer.parseInt(request.getParameter("plataformaId"));

            Genero genero = control.buscarUnGenero(generoId);
            Plataforma plataforma = control.buscarUnaPlataforma(plataformaId);

            Videojuego v = new Videojuego(titulo, anioLanzamiento, precio, genero, plataforma);
            control.crearVideojuego(v);

        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("El año, el precio, el género y la plataforma deben ser valores válidos.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }
        response.sendRedirect(destino);
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}