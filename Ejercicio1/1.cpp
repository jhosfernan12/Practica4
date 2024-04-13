#include <iostream>
#include <vector>
#include <string>

using namespace std;

// Clase Autor
class Autor 
{
public:
    string nombre;
    string nacionalidad;
    vector<string> librosEscritos;

    Autor(string nombre, string nacionalidad) 
    {
        this->nombre = nombre;
        this->nacionalidad = nacionalidad;
    }
};


class Libro 
{

public:
    string isbn;
    string titulo;
    int anioPublicacion;
    int cantidadEjemplares;
    Autor* autor;

    Libro(string isbn, string titulo, int anioPublicacion, int cantidadEjemplares, Autor* autor) {
        this->isbn = isbn;
        this->titulo = titulo;
        this->anioPublicacion = anioPublicacion;
        this->cantidadEjemplares = cantidadEjemplares;
        this->autor = autor;
    }
};


class Usuario 
{
public:
    string nombre;
    string direccion;
    string telefono;
    vector<Libro*> librosPrestados;

    Usuario(string nombre, string direccion, string telefono) 
    {
        this->nombre = nombre;
        this->direccion = direccion;
        this->telefono = telefono;
    }
};


class Prestamo 
{
public:
    Libro* libro;
    Usuario* usuario;
    string fechaPrestamo;
    string fechaDevolucion;

    Prestamo(Libro* libro, Usuario* usuario, string fechaPrestamo) 
    {
        this->libro = libro;
        this->usuario = usuario;
        this->fechaPrestamo = fechaPrestamo;
        this->fechaDevolucion = "";
    }

    void devolverLibro(string fechaDevolucion) 
    {
        this->fechaDevolucion = fechaDevolucion;
        // Eliminar el libro de la lista de libros prestados del usuario
        for (auto it = usuario->librosPrestados.begin(); it != usuario->librosPrestados.end(); ++it) 
        {
            if (*it == libro) 
            {
                usuario->librosPrestados.erase(it);
                break;
            }
        }
    }
};


class Biblioteca 
{
public:
    vector<Libro*> libros;
    vector<Autor*> autores;
    vector<Usuario*> usuarios;
    vector<Prestamo*> prestamos;

    void registrarAutor(Autor* autor) 
    {
        autores.push_back(autor);  //PUSHBACK PARA METER ALGO A UN VECTOR
    }

    void registrarLibro(Libro* libro)  
    {
        libros.push_back(libro); //PUSHBACK PARA METER ALGO A UN VECTOR
    }

    void registrarUsuario(Usuario* usuario) 
    {
        usuarios.push_back(usuario); //PUSHBACK PARA METER ALGO A UN VECTOR
    }

    void prestarLibro(string isbn, string nombreUsuario, string fechaPrestamo) 
    {
        Libro* libro = nullptr;
        Usuario* usuario = nullptr;

                                     
        for (auto l : libros)    //SE BUSCA EL LIBRO POS ISBN
        {
            if (l->isbn == isbn) 
            {
                libro = l;
                break;
            }
        }


        for (auto u : usuarios) 
        {
            if (u->nombre == nombreUsuario) 
            {
                usuario = u;
                break;
            }
        }

        if (libro && usuario) 
        {
            Prestamo* prestamo = new Prestamo(libro, usuario, fechaPrestamo);
            prestamos.push_back(prestamo);
            usuario->librosPrestados.push_back(libro);
            cout << "Prestamo exitosamente realizado ;D" << endl;
        } 
        else 
        {
            cout << "Libro o usuario no encontrado =(" << endl;
        }
    }

    void devolverLibro(string isbn, string nombreUsuario, string fechaDevolucion) 
    {
        Prestamo* prestamo = nullptr;
        // BUSCAMOS EL PRESTAMO POR ISBN Y NOMBRE DE USUARIO
        for (auto p : prestamos) 
        {
            if (p->libro->isbn == isbn && p->usuario->nombre == nombreUsuario && p->fechaDevolucion.empty()) 
            { 
                prestamo = p;
                break;
            }
        }

        if (prestamo) 
        {
            prestamo->devolverLibro(fechaDevolucion);
            cout << "Devolucion exitosamente realizada ;D" << endl;
        } 
        else 
        {
            cout << "Prestamo no encontrado o ya fue devuelto" << endl;
        }
    }
};

int main() 
{

    Biblioteca biblioteca;
    Autor* autor1 = new Autor("La llamada de Cthulhu", "Estados Unidos");
    biblioteca.registrarAutor(autor1);


    Libro* libro1 = new Libro("123456789", "El color que cayo del cielo", 1967, 5, autor1);
    biblioteca.registrarLibro(libro1);
    autor1->librosEscritos.push_back(libro1->titulo);

    Usuario* usuario1 = new Usuario("Pedro Perez", "Av. Principal 553", "555-1234");
    biblioteca.registrarUsuario(usuario1);


    biblioteca.prestarLibro("123456789", "Pedro Perez", "2024-04-12");
    biblioteca.devolverLibro("123456789", "Pedro Perez", "2024-04-15");

    return 0;
}
