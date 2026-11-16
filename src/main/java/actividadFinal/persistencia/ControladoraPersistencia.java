package actividadFinal.persistencia;

import java.util.List;
import actividadFinal.logica.Genero;
import actividadFinal.logica.Plataforma;
import actividadFinal.logica.Videojuego;

public class ControladoraPersistencia {

	GeneroJpaController generoJpa = new GeneroJpaController();
	PlataformaJpaController plataformaJpa = new PlataformaJpaController();
	VideojuegoJpaController videojuegoJpa = new VideojuegoJpaController();

	// ======================GÉNERO======================
	public void crearGenero(Genero g) {
		generoJpa.crear(g);
	}

	public Genero buscarGenero(int id) {
		return generoJpa.buscarUno(id);
	}

	public List<Genero> buscarTodosLosGeneros() {
		return generoJpa.buscarTodos();
	}

	public void editarGenero(Genero g) {
		generoJpa.editar(g);
	}

	public void eliminarGenero(int id) {
		generoJpa.eliminar(id);
	}

	// ======================PLATAFORMA======================V
	public void crearPlataforma(Plataforma p) {
		plataformaJpa.crear(p);
	}

	public Plataforma buscarPlataforma(int id) {
		return plataformaJpa.buscarUno(id);
	}

	public List<Plataforma> buscarTodasLasPlataformas() {
		return plataformaJpa.buscarTodos();
	}

	public void editarPlataforma(Plataforma p) {
		plataformaJpa.editar(p);
	}

	public void eliminarPlataforma(int id) {
		plataformaJpa.eliminar(id);
	}

	// ======================VIDEOJUEGO======================V
	public void crearVideojuego(Videojuego v) {
		videojuegoJpa.crear(v);
	}

	public Videojuego buscarVideojuego(int id) {
		return videojuegoJpa.buscarUno(id);
	}

	public List<Videojuego> buscarTodosLosVideojuegos() {
		return videojuegoJpa.buscarTodos();
	}

	public List<Videojuego> buscarVideojuegosPorTitulo(String titulo) {
		return videojuegoJpa.buscarPorTitulo(titulo);
	}

	public void editarVideojuego(Videojuego v) {
		videojuegoJpa.editar(v);
	}

	public void eliminarVideojuego(int id) {
		videojuegoJpa.eliminar(id);
	}
}