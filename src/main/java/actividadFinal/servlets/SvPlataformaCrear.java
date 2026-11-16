package actividadFinal.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import actividadFinal.logica.ControladoraLogica;
import actividadFinal.logica.Plataforma;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvPlataformaCrear", urlPatterns = { "/SvPlataformaCrear" })
public class SvPlataformaCrear extends HttpServlet {

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String destino = request.getContextPath() + "/SvPlataformaListar";

        try {
            String nombre = request.getParameter("nombre");
            String fabricante = request.getParameter("fabricante");
            int anioLanzamiento = Integer.parseInt(request.getParameter("anioLanzamiento"));

            Plataforma p = new Plataforma(nombre, fabricante, anioLanzamiento);
            control.crearPlataforma(p);
        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("El año de lanzamiento debe ser un número válido.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }
        response.sendRedirect(destino);
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}