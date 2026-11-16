package actividadFinal.servlets;

import java.io.IOException;
import java.util.List;
import actividadFinal.logica.ControladoraLogica;
import actividadFinal.logica.Genero;
import actividadFinal.logica.Plataforma;
import actividadFinal.logica.Videojuego;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvVideojuegoListar", urlPatterns = { "/SvVideojuegoListar" })
public class SvVideojuegoListar extends HttpServlet {

    private static final String VISTA = "/WEB-INF/vistas/videojuegos.jsp";
    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String buscar = request.getParameter("buscar");
        List<Videojuego> videojuegos = control.buscarVideojuegosPorTitulo(buscar);
        List<Genero> generos = control.listarGeneros();
        List<Plataforma> plataformas = control.listarPlataformas();

        request.setAttribute("videojuegos", videojuegos);
        request.setAttribute("generos", generos);
        request.setAttribute("plataformas", plataformas);
        request.setAttribute("buscar", buscar != null ? buscar : "");
        request.getRequestDispatcher(VISTA).forward(request, response);
    }
}