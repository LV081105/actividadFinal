<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="actividadFinal.logica.Genero" %>
<%
    Genero g = (Genero) request.getAttribute("genero");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Editar género</title>
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
  <h2>Editar género</h2>
  <% if (g == null) { %>
    <div class="error">No se recibió ningún género para editar.</div>
    <a class="boton secundario" href="SvGeneroListar">Volver</a>
  <% } else { %>
  <form class="formulario" action="SvGeneroEditar" method="post">
    <input type="hidden" name="id" value="<%= g.getId() %>">
    <label>Nombre <input type="text" name="nombre" value="<%= g.getNombre() %>" required></label>
    <label>Descripción <input type="text" name="descripcion" value="<%= g.getDescripcion() %>" required></label>
    <div class="form-acciones">
      <button type="submit">Guardar cambios</button>
      <a class="boton secundario" href="SvGeneroListar">Cancelar</a>
    </div>
  </form>
  <% } %>
</main>
<footer>Instituto Técnico Ntra. Sra. de Fátima (A-893) — 5° TC</footer>
</body>
