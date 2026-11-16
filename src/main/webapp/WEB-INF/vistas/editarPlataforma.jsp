<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="actividadFinal.logica.Plataforma" %>
<%
    Plataforma p = (Plataforma) request.getAttribute("plataforma");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Editar plataforma</title>
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
  <h2>Editar plataforma</h2>
  <% if (p == null) { %>
    <div class="error">No se recibió ninguna plataforma para editar.</div>
    <a class="boton secundario" href="SvPlataformaListar">Volver</a>
  <% } else { %>
  <form class="formulario" action="SvPlataformaEditar" method="post">
    <input type="hidden" name="id" value="<%= p.getId() %>">
    <label>Nombre <input type="text" name="nombre" value="<%= p.getNombre() %>" required></label>
    <label>Fabricante <input type="text" name="fabricante" value="<%= p.getFabricante() %>" required></label>
    <label>Año lanzamiento <input type="number" name="anioLanzamiento" min="1970" max="2030" value="<%= p.getAnioLanzamiento() %>" required></label>
    <div class="form-acciones">
      <button type="submit">Guardar cambios</button>
      <a class="boton secundario" href="SvPlataformaListar">Cancelar</a>
    </div>
  </form>
  <% } %>
</main>
<footer>Instituto Técnico Ntra. Sra. de Fátima (A-893) — 5° TC</footer>
</body>
</html>