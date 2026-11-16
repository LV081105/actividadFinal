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

@WebServlet(name = "SvGeneroEditar", urlPatterns = { "/SvGeneroEditar" })
public class SvGeneroEditar extends HttpServlet {

    private static final String VISTA = "/WEB-INF/vistas/editarGenero.jsp";
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
            Genero genero = control.buscarUnGenero(id);
            if (genero == null) {
                redirigirConError(request, response, "El género con id " + id + " no existe.");
                return;
            }
            request.setAttribute("genero", genero);
            request.getRequestDispatcher(VISTA).forward(request, response);
        } catch (NumberFormatException e) {
            redirigirConError(request, response, "El id del género no es válido.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String destino = request.getContextPath() + "/SvGeneroListar";

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Genero genero = control.buscarUnGenero(id);
            if (genero == null) {
                throw new Exception("El género con id " + id + " no existe.");
            }
            genero.setNombre(request.getParameter("nombre"));
            genero.setDescripcion(request.getParameter("descripcion"));
            control.editarGenero(genero);
        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("El id debe ser un número válido.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }
        response.sendRedirect(destino);
    }

    private void redirigirConError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/SvGeneroListar?error=" + codificar(mensaje));
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}