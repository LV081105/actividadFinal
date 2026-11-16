package actividadFinal.servlets;

import java.io.IOException;
import java.util.List;
import actividadFinal.logica.ControladoraLogica;
import actividadFinal.logica.Genero;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvGeneroListar", urlPatterns = { "/SvGeneroListar" })
public class SvGeneroListar extends HttpServlet {

    private static final String VISTA = "/WEB-INF/vistas/generos.jsp";
    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Genero> generos = control.listarGeneros();
        request.setAttribute("generos", generos);
        request.getRequestDispatcher(VISTA).forward(request, response);
    }
}