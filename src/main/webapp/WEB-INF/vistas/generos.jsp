<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, actividadFinal.logica.Genero" %>
<%
    List<Genero> generos = (List<Genero>) request.getAttribute("generos");
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Géneros</title>
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

  <h2>Géneros</h2>
  <div class="tabla-contenedor">
    <table>
      <thead>
        <tr>
          <th>ID</th><th>Nombre</th><th>Descripción</th><th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <% if (generos == null || generos.isEmpty()) { %>
          <tr><td colspan="4" class="vacio">Todavía no hay géneros cargados.</td></tr>
        <% } else {
             for (Genero g : generos) { %>
          <tr>
            <td>#<%= g.getId() %></td>
            <td><strong><%= g.getNombre() %></strong></td>
            <td><%= g.getDescripcion() %></td>
            <td class="acciones">
              <a class="boton secundario" href="SvGeneroEditar?id=<%= g.getId() %>">Editar</a>
              <form action="SvGeneroEliminar" method="post" onsubmit="return confirm('¿Eliminar este género?');">
                <input type="hidden" name="id" value="<%= g.getId() %>">
                <button type="submit" class="peligro">Eliminar</button>
              </form>
            </td>
          </tr>
        <%   }
           } %>
      </tbody>
    </table>
  </div>

  <h2>Nuevo género</h2>
  <form class="formulario" action="SvGeneroCrear" method="post">
    <label>Nombre <input type="text" name="nombre" required></label>
    <label>Descripción <input type="text" name="descripcion" required></label>
    <div class="form-acciones">
      <button type="submit">Guardar</button>
    </div>
  </form>

</main>
<footer>Instituto Técnico Ntra. Sra. de Fátima (A-893) — 5° TC</footer>
</body>
</html>