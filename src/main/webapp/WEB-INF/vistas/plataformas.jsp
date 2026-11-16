<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, actividadFinal.logica.Plataforma" %>
<%
    List<Plataforma> plataformas = (List<Plataforma>) request.getAttribute("plataformas");
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Plataformas</title>
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

  <% if (error != null) { %>
    <div class="error"><%= error %></div>
  <% } %>

  <h2>Plataformas</h2>
  <div class="tabla-contenedor">
    <table>
      <thead>
        <tr>
          <th>ID</th><th>Nombre</th><th>Fabricante</th><th>Año lanzamiento</th><th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <% if (plataformas == null || plataformas.isEmpty()) { %>
          <tr><td colspan="5" class="vacio">Todavía no hay plataformas cargadas.</td></tr>
        <% } else {
             for (Plataforma p : plataformas) { %>
          <tr>
            <td>#<%= p.getId() %></td>
            <td><strong><%= p.getNombre() %></strong></td>
            <td><%= p.getFabricante() %></td>
            <td><%= p.getAnioLanzamiento() %></td>
            <td class="acciones">
              <a class="boton secundario" href="SvPlataformaEditar?id=<%= p.getId() %>">Editar</a>
              <form action="SvPlataformaEliminar" method="post" onsubmit="return confirm('¿Eliminar esta plataforma?');">
                <input type="hidden" name="id" value="<%= p.getId() %>">
                <button type="submit" class="peligro">Eliminar</button>
              </form>
            </td>
          </tr>
        <%   }
           } %>
      </tbody>
    </table>
  </div>

  <h2>Nueva plataforma</h2>
  <form class="formulario" action="SvPlataformaCrear" method="post">
    <label>Nombre <input type="text" name="nombre" required></label>
    <label>Fabricante <input type="text" name="fabricante" required></label>
    <label>Año lanzamiento <input type="number" name="anioLanzamiento" min="1970" max="2030" required></label>
    <div class="form-acciones">
      <button type="submit">Guardar</button>
    </div>
  </form>

</main>
<footer>Instituto Técnico Ntra. Sra. de Fátima (A-893) — 5° TC</footer>
</body>
</html>