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

@WebServlet(name = "SvPlataformaEditar", urlPatterns = { "/SvPlataformaEditar" })
public class SvPlataformaEditar extends HttpServlet {

    private static final String VISTA = "/WEB-INF/vistas/editarPlataforma.jsp";
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
            Plataforma plataforma = control.buscarUnaPlataforma(id);
            if (plataforma == null) {
                redirigirConError(request, response, "La plataforma con id " + id + " no existe.");
                return;
            }
            request.setAttribute("plataforma", plataforma);
            request.getRequestDispatcher(VISTA).forward(request, response);
        } catch (NumberFormatException e) {
            redirigirConError(request, response, "El id de la plataforma no es válido.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String destino = request.getContextPath() + "/SvPlataformaListar";

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Plataforma plataforma = control.buscarUnaPlataforma(id);
            if (plataforma == null) {
                throw new Exception("La plataforma con id " + id + " no existe.");
            }
            plataforma.setNombre(request.getParameter("nombre"));
            plataforma.setFabricante(request.getParameter("fabricante"));
            plataforma.setAnioLanzamiento(Integer.parseInt(request.getParameter("anioLanzamiento")));

            control.editarPlataforma(plataforma);
        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("El id y el año deben ser números válidos.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }
        response.sendRedirect(destino);
    }

    private void redirigirConError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/SvPlataformaListar?error=" + codificar(mensaje));
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}