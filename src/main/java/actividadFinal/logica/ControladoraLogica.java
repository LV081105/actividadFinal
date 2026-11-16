package actividadFinal.logica;

import java.time.Year;
import java.util.List;
import actividadFinal.persistencia.ControladoraPersistencia;

public class ControladoraLogica {
	// La controladora de persistencia se encarga de hablar con la BD.
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

  
 // ================= VALIDACIONES COMPARTIDAS =================
 	// Las usan el alta y la edición, así las reglas son siempre las mismas.
    private void validarGenero(Genero g) throws Exception {
        if (g == null) {
            throw new Exception("El género no puede ser nulo.");
        }
        if (g.getNombre() == null || g.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del género es obligatorio.");
        }
        if (!g.getNombre().trim().matches("^[\\p{L}][\\p{L} .'-]*$")) {
            throw new Exception("El nombre del género solo puede contener letras.");
        }
        if (g.getDescripcion() == null || g.getDescripcion().trim().isEmpty()) {
            throw new Exception("La descripción del género es obligatoria.");
        }
    }

    private void validarPlataforma(Plataforma p) throws Exception {
        if (p == null) {
            throw new Exception("La plataforma no puede ser nula.");
        }
        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre de la plataforma es obligatorio.");
        }
        if (p.getFabricante() == null || p.getFabricante().trim().isEmpty()) {
            throw new Exception("El fabricante es obligatorio.");
        }
        if (!p.getFabricante().trim().matches("^[\\p{L}][\\p{L} .'-]*$")) {
            throw new Exception("El fabricante solo puede contener letras.");
        }
        if (p.getAnioLanzamiento() < 1970 || p.getAnioLanzamiento() > Year.now().getValue()) {
            throw new Exception("El año de lanzamiento de la plataforma debe estar entre 1970 y " + Year.now().getValue() + ".");
        }
    }

    private void validarVideojuego(Videojuego v) throws Exception {
        if (v == null) {
            throw new Exception("El videojuego no puede ser nulo.");
        }
        if (v.getTitulo() == null || v.getTitulo().trim().isEmpty()) {
            throw new Exception("El título del videojuego es obligatorio.");
        }
        if (v.getAnioLanzamiento() < 1970 || v.getAnioLanzamiento() > Year.now().getValue()) {
            throw new Exception("El año de lanzamiento debe estar entre 1970 y " + Year.now().getValue() + ".");
        }
        if (v.getPrecio() < 0) {
            throw new Exception("El precio no puede ser negativo.");
        }
        if (v.getGenero() == null) {
            throw new Exception("Debe seleccionar un género válido.");
        }
        if (v.getPlataforma() == null) {
            throw new Exception("Debe seleccionar una plataforma válida.");
        }
    }

    // ================= GÉNERO =================

    public void crearGenero(Genero g) throws Exception {
        validarGenero(g);
        controlPersis.crearGenero(g);
    }

    public void editarGenero(Genero g) throws Exception {
        if (g == null || controlPersis.buscarGenero(g.getId()) == null) {
            throw new Exception("El género que quiere editar no existe.");
        }
        validarGenero(g);
        controlPersis.editarGenero(g);
    }

    public void eliminarGenero(int id) throws Exception {
        if (controlPersis.buscarGenero(id) == null) {
            throw new Exception("El género con id " + id + " no existe.");
        }
        try {
            controlPersis.eliminarGenero(id);
        } catch (Exception e) {
            throw new Exception("No se puede eliminar el género porque tiene videojuegos asociados.");
        }
    }

    public Genero buscarUnGenero(int id) {
        return controlPersis.buscarGenero(id);
    }

    public List<Genero> listarGeneros() {
        return controlPersis.buscarTodosLosGeneros();
    }

    // ================= PLATAFORMA =================

    public void crearPlataforma(Plataforma p) throws Exception {
        validarPlataforma(p);
        controlPersis.crearPlataforma(p);
    }

    public void editarPlataforma(Plataforma p) throws Exception {
        if (p == null || controlPersis.buscarPlataforma(p.getId()) == null) {
            throw new Exception("La plataforma que quiere editar no existe.");
        }
        validarPlataforma(p);
        controlPersis.editarPlataforma(p);
    }

    public void eliminarPlataforma(int id) throws Exception {
        if (controlPersis.buscarPlataforma(id) == null) {
            throw new Exception("La plataforma con id " + id + " no existe.");
        }
        try {
            controlPersis.eliminarPlataforma(id);
        } catch (Exception e) {
            throw new Exception("No se puede eliminar la plataforma porque tiene videojuegos asociados.");
        }
    }

    public Plataforma buscarUnaPlataforma(int id) {
        return controlPersis.buscarPlataforma(id);
    }

    public List<Plataforma> listarPlataformas() {
        return controlPersis.buscarTodasLasPlataformas();
    }

    // ================= VIDEOJUEGO =================

    public void crearVideojuego(Videojuego v) throws Exception {
        validarVideojuego(v);
        controlPersis.crearVideojuego(v);
    }

    public void editarVideojuego(Videojuego v) throws Exception {
        if (v == null || controlPersis.buscarVideojuego(v.getId()) == null) {
            throw new Exception("El videojuego que quiere editar no existe.");
        }
        validarVideojuego(v);
        controlPersis.editarVideojuego(v);
    }

    public void eliminarVideojuego(int id) throws Exception {
        if (controlPersis.buscarVideojuego(id) == null) {
            throw new Exception("El videojuego con id " + id + " no existe.");
        }
        controlPersis.eliminarVideojuego(id);
    }

    public Videojuego buscarUnVideojuego(int id) {
        return controlPersis.buscarVideojuego(id);
    }

    public List<Videojuego> listarVideojuegos() {
        return controlPersis.buscarTodosLosVideojuegos();
    }

    public List<Videojuego> buscarVideojuegosPorTitulo(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return listarVideojuegos();
        }
        return controlPersis.buscarVideojuegosPorTitulo(texto.trim());
    }
}