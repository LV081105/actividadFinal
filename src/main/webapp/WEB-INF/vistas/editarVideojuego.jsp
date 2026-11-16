<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, actividadFinal.logica.Videojuego, actividadFinal.logica.Genero, actividadFinal.logica.Plataforma" %>
<%
    Videojuego v = (Videojuego) request.getAttribute("videojuego");
    List<Genero> generos = (List<Genero>) request.getAttribute("generos");
    List<Plataforma> plataformas = (List<Plataforma>) request.getAttribute("plataformas");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Editar videojuego</title>
  <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<header>
  <h1>Catálogo de Videojuegos</h1>
  <nav>
    <a href="SvVideojuegoListar">Videojuegos</a>
    <a href="SvGeneroListar">Géneros</a>
    <a href="SvPlataformaListar">Plataformas</a>
  </nav>
</header>
<main>
  <h2>Editar videojuego</h2>
  <% if (v == null) { %>
    <div class="error">No se recibió ningún videojuego para editar.</div>
    <a class="boton secundario" href="SvVideojuegoListar">Volver</a>
  <% } else { %>
  <form class="formulario" action="SvVideojuegoEditar" method="post">
    <input type="hidden" name="id" value="<%= v.getId() %>">
    <label>Título <input type="text" name="titulo" value="<%= v.getTitulo() %>" required></label>
    <label>Año de lanzamiento <input type="number" name="anioLanzamiento" min="1970" max="2030" value="<%= v.getAnioLanzamiento() %>" required></label>
    <label>Precio <input type="number" name="precio" step="0.01" min="0" value="<%= v.getPrecio() %>" required></label>
    <label>Género
      <select name="generoId" required>
        <% if (generos != null) {
             for (Genero g : generos) {
               boolean sel = (v.getGenero() != null && v.getGenero().getId() == g.getId()); %>
          <option value="<%= g.getId() %>" <%= sel ? "selected" : "" %>><%= g.getNombre() %></option>
        <%   }
           } %>
      </select>
    </label>
    <label>Plataforma
      <select name="plataformaId" required>
        <% if (plataformas != null) {
             for (Plataforma p : plataformas) {
               boolean sel = (v.getPlataforma() != null && v.getPlataforma().getId() == p.getId()); %>
          <option value="<%= p.getId() %>" <%= sel ? "selected" : "" %>><%= p.getNombre() %></option>
        <%   }
           } %>
      </select>
    </label>
    <div class="form-acciones">
      <button type="submit">Guardar cambios</button>
      <a class="boton secundario" href="SvVideojuegoListar">Cancelar</a>
    </div>
  </form>
  <% } %>
</main>
<footer>Instituto Técnico Ntra. Sra. de Fátima (A-893) — 5° TC</footer>
</body>
</html>