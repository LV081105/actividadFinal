package actividadFinal.logica;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Videojuego implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Basic
	private String titulo;
	private int anioLanzamiento;
	private double precio;

	@ManyToOne
	@JoinColumn(name = "genero_id")
	private Genero genero;

	@ManyToOne
	@JoinColumn(name = "plataforma_id")
	private Plataforma plataforma;

	public Videojuego() {
	}

	public Videojuego(String titulo, int anioLanzamiento, double precio, Genero genero, Plataforma plataforma) {
		this.titulo = titulo;
		this.anioLanzamiento = anioLanzamiento;
		this.precio = precio;
		this.genero = genero;
		this.plataforma = plataforma;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnioLanzamiento() {
		return anioLanzamiento;
	}

	public void setAnioLanzamiento(int anioLanzamiento) {
		this.anioLanzamiento = anioLanzamiento;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
}