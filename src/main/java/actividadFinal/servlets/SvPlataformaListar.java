package actividadFinal.servlets;

import java.io.IOException;
import java.util.List;
import actividadFinal.logica.ControladoraLogica;
import actividadFinal.logica.Plataforma;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvPlataformaListar", urlPatterns = { "/SvPlataformaListar" })
public class SvPlataformaListar extends HttpServlet {

    private static final String VISTA = "/WEB-INF/vistas/plataformas.jsp";
    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Plataforma> plataformas = control.listarPlataformas();
        request.setAttribute("plataformas", plataformas);
        request.getRequestDispatcher(VISTA).forward(request, response);
    }
}