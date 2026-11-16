package actividadFinal.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import actividadFinal.logica.ControladoraLogica;
import actividadFinal.logica.Genero;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvGeneroCrear", urlPatterns = { "/SvGeneroCrear" })
public class SvGeneroCrear extends HttpServlet {

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String destino = request.getContextPath() + "/SvGeneroListar";

        try {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            Genero g = new Genero(nombre, descripcion);
            control.crearGenero(g);
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }
        response.sendRedirect(destino);
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}