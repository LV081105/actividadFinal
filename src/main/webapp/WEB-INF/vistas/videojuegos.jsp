<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, actividadFinal.logica.Videojuego, actividadFinal.logica.Genero, actividadFinal.logica.Plataforma"%>
<%
List<Videojuego> videojuegos = (List<Videojuego>) request.getAttribute("videojuegos");
List<Genero> generos = (List<Genero>) request.getAttribute("generos");
List<Plataforma> plataformas = (List<Plataforma>) request.getAttribute("plataformas");
String buscar = (String) request.getAttribute("buscar");
String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Videojuegos</title>
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
		<div class="error"><%=error%></div>
		<% } %>

		<div class="alerta-info">
			<strong>Nota:</strong> Si el género o la plataforma que buscás no
			aparece en la lista, primero debés cargarlo en <a
				href="SvGeneroListar">Géneros</a> o en <a href="SvPlataformaListar">Plataformas</a>
			para que esté disponible acá.
		</div>

		<h2>Buscar videojuego</h2>
		<form class="barra-busqueda" action="SvVideojuegoListar" method="get">
			<input type="text" name="buscar" placeholder="Título del juego..."
				value="<%=buscar != null ? buscar : ""%>">
			<button type="submit">Buscar</button>
			<a class="boton secundario" href="SvVideojuegoListar">Limpiar</a>
		</form>

		<h2>Videojuegos</h2>
		<div class="tabla-contenedor">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Título</th>
						<th>Año</th>
						<th>Precio</th>
						<th>Género</th>
						<th>Plataforma</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<% if (videojuegos == null || videojuegos.isEmpty()) { %>
					<tr>
						<td colspan="7" class="vacio">No se encontraron videojuegos.</td>
					</tr>
					<% } else {
					   for (Videojuego v : videojuegos) { %>
					<tr>
						<td>#<%=v.getId()%></td>
						<td><strong><%=v.getTitulo()%></strong></td>
						<td><%=v.getAnioLanzamiento()%></td>
						<td class="precio">$<%=v.getPrecio()%></td>
						<td><span class="badge"><%=v.getGenero() != null ? v.getGenero().getNombre() : "Sin género"%></span></td>
						<td><span class="badge"><%=v.getPlataforma() != null ? v.getPlataforma().getNombre() : "Sin plataforma"%></span></td>
						<td class="acciones">
							<a class="boton secundario" href="SvVideojuegoEditar?id=<%=v.getId()%>">Editar</a>
							<form action="SvVideojuegoEliminar" method="post"
								onsubmit="return confirm('¿Eliminar este videojuego?');">
								<input type="hidden" name="id" value="<%=v.getId()%>">
								<button type="submit" class="peligro">Eliminar</button>
							</form>
						</td>
					</tr>
					<% }
					} %>
				</tbody>
			</table>
		</div>

		<h2>Nuevo videojuego</h2>
		<form class="formulario" action="SvVideojuegoCrear" method="post">
			<label>Título
				<input type="text" name="titulo" required>
			</label>
			<label>Año de lanzamiento
				<input type="number" name="anioLanzamiento" min="1970" max="2030" required>
			</label>
			<label>Precio
				<input type="number" name="precio" step="0.01" min="0" required>
			</label>
			<label>Género
				<select name="generoId" required>
					<option value="">-- Seleccionar género --</option>
					<% if (generos != null) {
						for (Genero g : generos) { %>
					<option value="<%=g.getId()%>"><%=g.getNombre()%></option>
					<% }
					} %>
				</select>
			</label>
			<label>Plataforma
				<select name="plataformaId" required>
					<option value="">-- Seleccionar plataforma --</option>
					<% if (plataformas != null) {
						for (Plataforma p : plataformas) { %>
					<option value="<%=p.getId()%>"><%=p.getNombre()%></option>
					<% }
					} %>
				</select>
			</label>
			<div class="form-acciones">
				<button type="submit">Guardar</button>
			</div>
		</form>

	</main>
	<footer>Instituto Técnico Ntra. Sra. de Fátima (A-893) — 5° TC</footer>
</body>
</html>