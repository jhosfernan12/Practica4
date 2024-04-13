package Ejercicio1;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

// Clase Autor
class Autor 
{
    private String nombre;
    private String nacionalidad;
    private List<String> librosEscritos;

    public Autor(String nombre, String nacionalidad) 
    {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.librosEscritos = new ArrayList<>();
    }

    public void agregarLibroEscrito(String titulo) 
    {
        librosEscritos.add(titulo);
    }

    // Getters y Setters
    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getNacionalidad() 
    {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) 
    {
        this.nacionalidad = nacionalidad;
    }

    public List<String> getLibrosEscritos() 
    {
        return librosEscritos;
    }
}

// Clase Libro
class Libro 
{
    private String isbn;
    private String titulo;
    private int anoPublicacion;
    private int cantidadEjemplares;
    private Autor autor;

    public Libro(String isbn, String titulo, int anoPublicacion, int cantidadEjemplares, Autor autor) 
    {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anoPublicacion = anoPublicacion;
        this.cantidadEjemplares = cantidadEjemplares;
        this.autor = autor;
    }

    // Getters y Setters
    public String getIsbn() 
    {
        return isbn;
    }

    public void setIsbn(String isbn) 
    {
        this.isbn = isbn;
    }

    public String getTitulo()
     {
        return titulo;
    }

    public void setTitulo(String titulo) 
    {
        this.titulo = titulo;
    }

    public int getAnoPublicacion() 
    {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) 
    {
        this.anoPublicacion = anoPublicacion;
    }

    public int getCantidadEjemplares() 
    {
        return cantidadEjemplares;
    }

    public void setCantidadEjemplares(int cantidadEjemplares) 
    {
        this.cantidadEjemplares = cantidadEjemplares;
    }

    public Autor getAutor() 
    {
        return autor;
    }

    public void setAutor(Autor autor)
     {
        this.autor = autor;
    }
}

// Clase Usuario
class Usuario 
{
    private String nombre;
    private String direccion;
    private String telefono;
    private List<Libro> librosPrestados;

    public Usuario(String nombre, String direccion, String telefono) 
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.librosPrestados = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getDireccion() 
    
    {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() 
    {
        return telefono;
    }

    public void setTelefono(String telefono) 
    {
        this.telefono = telefono;
    }

    public List<Libro> getLibrosPrestados() 
    {
        return librosPrestados;
    }
}

// Clase Prestamo
class Prestamo 
{
    private Libro libro;
    private Usuario usuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo(Libro libro, Usuario usuario, Date fechaPrestamo) 
    {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = null;
    }

    public void devolverLibro(Date fechaDevolucion) 
    {
        this.fechaDevolucion = fechaDevolucion;
        // Eliminar el libro de la lista de libros prestados del usuario
        usuario.getLibrosPrestados().remove(libro);
    }
}

// Clase Biblioteca
class Biblioteca 
{
    private List<Libro> libros;
    private List<Autor> autores;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;

    public Biblioteca() 
    {
        libros = new ArrayList<>();
        autores = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    public void registrarAutor(Autor autor)
     {
        autores.add(autor);
    }

    public void registrarLibro(Libro libro) 
    {
        libros.add(libro);
    }

    public void registrarUsuario(Usuario usuario) 
    {
        usuarios.add(usuario);
    }

    public void prestarLibro(String isbn, String nombreUsuario, Date fechaPrestamo) 
    {
        Libro libro = null;
        Usuario usuario = null;

        // Buscar libro por ISBN
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) 
            {
                libro = l;
                break;
            }
        }

        // Buscar usuario por nombre
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombreUsuario)) 
            {
                usuario = u;
                break;
            }
        }

        if (libro != null && usuario != null) 
        {
            Prestamo prestamo = new Prestamo(libro, usuario, fechaPrestamo);
            prestamos.add(prestamo);
            usuario.getLibrosPrestados().add(libro);
            System.out.println("Prestamo exitoso ;D");
        } 
        else 
        {
            System.out.println("Libro o usuario no encontrado =(");
        }
    }

    public void devolverLibro(String isbn, String nombreUsuario, Date fechaDevolucion)
     {
        Prestamo prestamo = null;

        // Buscar prestamo por ISBN y nombre de usuario
        for (Prestamo p : prestamos) 
        {
            if (p.getLibro().getIsbn().equals(isbn) && p.getUsuario().getNombre().equals(nombreUsuario) && p.getFechaDevolucion() == null) 
            {
                prestamo = p;
                break;
            }
        }

        if (prestamo != null) 
        {
            prestamo.devolverLibro(fechaDevolucion);
            System.out.println("Devolucion exitosa ;D");
        } 
        else 
        {
            System.out.println("Prestamo no encontrado o ya fue devuelto");
        }
    }

    // Getters
    public List<Libro> getLibros()
     {
        return libros;
    }

    public List<Autor> getAutores() 
    {
        return autores;
    }

    public List<Usuario> getUsuarios()
     {
        return usuarios;
    }

    public List<Prestamo> getPrestamos() 
    {
        return prestamos;
    }
}

// Clase principal
public class Main3
{
    public static void main(String[] args) 
    {
        Biblioteca biblioteca = new Biblioteca();

        // Registrar autor
        Autor autor1 = new Autor("H.P. Lovecraft", "Estados Unidos");
        biblioteca.registrarAutor(autor1);

        // Registrar libro
        Libro libro1 = new Libro("123456789", "El color que cayo del cielo", 1967, 5, autor1);
        biblioteca.registrarLibro(libro1);
        autor1.agregarLibroEscrito(libro1.getTitulo());

        // Registrar usuario
        Usuario usuario1 = new Usuario("Pedro Perez", "Av. Principal 553", "555-1234");
        biblioteca.registrarUsuario(usuario1);

        // Prestar libro
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try 
        {
            Date fechaPrestamo = dateFormat.parse("2024-04-12");
            biblioteca.prestarLibro("123456789", "Pedro Perez", fechaPrestamo);

            // Devolver libro
            Date fechaDevolucion = dateFormat.parse("2024-04-15");
            biblioteca.devolverLibro("123456789", "Pedro Perez", fechaDevolucion);
        } catch (ParseException e) 
        {
            e.printStackTrace();
        }
    }
}
