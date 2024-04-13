from datetime import datetime
from typing import List

# Clase Autor
class Autor:
    def __init__(self, nombre: str, nacionalidad: str):
        self.nombre = nombre
        self.nacionalidad = nacionalidad
        self.librosEscritos = []

# Clase Libro
class Libro:
    def __init__(self, isbn: str, titulo: str, anoPublicacion: int, cantidadEjemplares: int, autor: Autor):
        self.isbn = isbn
        self.titulo = titulo
        self.anoPublicacion = anoPublicacion
        self.cantidadEjemplares = cantidadEjemplares
        self.autor = autor

# Clase Usuario
class Usuario:
    def __init__(self, nombre: str, direccion: str, telefono: str):
        self.nombre = nombre
        self.direccion = direccion
        self.telefono = telefono
        self.librosPrestados = []

# Clase Prestamo
class Prestamo:
    def __init__(self, libro: Libro, usuario: Usuario, fechaPrestamo: datetime):
        self.libro = libro
        self.usuario = usuario
        self.fechaPrestamo = fechaPrestamo
        self.fechaDevolucion = None

    def devolver_Libro(self, fechaDevolucion: datetime):
        self.fechaDevolucion = fechaDevolucion
        # Eliminar el libro de la lista de libros prestados del usuario
        if self.libro in self.usuario.librosPrestados:
            self.usuario.librosPrestados.remove(self.libro)

# Clase Biblioteca
class Biblioteca:
    def __init__(self):
        self.libros = []
        self.autores = []
        self.usuarios = []
        self.prestamos = []

    def RegistrarAutor(self, autor: Autor):
        self.autores.append(autor)

    def RegistrarLibro(self, libro: Libro):
        self.libros.append(libro)

    def RegistrarUsuario(self, usuario: Usuario):
        self.usuarios.append(usuario)

    def PrestarLibro(self, isbn: str, nombreUsuario: str, fechaPrestamo: datetime):
        libro = None
        usuario = None

        # Buscar libro por ISBN
        for l in self.libros:
            if l.isbn == isbn:
                libro = l
                break

        # Buscar usuario por nombre
        for u in self.usuarios:
            if u.nombre == nombreUsuario:
                usuario = u
                break

        if libro and usuario:
            prestamo = Prestamo(libro, usuario, fechaPrestamo)
            self.prestamos.append(prestamo)
            usuario.librosPrestados.append(libro)
            print("Prestamo exitoso ;D")
        else:
            print("Libro o usuario no encontrado =(")

    def DevolverLibro(self, isbn: str, nombreUsuario: str, fechaDevolucion: datetime):
        prestamo = None
        # Buscar prestamo por ISBN y nombre de usuario
        for p in self.prestamos:
            if p.libro.isbn == isbn and p.usuario.nombre == nombreUsuario and p.fechaDevolucion is None:
                prestamo = p
                break

        if prestamo:
            prestamo.devolver_Libro(fechaDevolucion)
            print("Devolucion exitosa ;D")
        else:
            print("Prestamo no encontrado o ya fue devuelto")


# Codigo principal

biblioteca = Biblioteca()
    
# Registrar autor
autor1 = Autor("La llamada de Cthulhu", "Estados Unidos")
biblioteca.RegistrarAutor(autor1)
    
# Registrar libro
libro1 = Libro("123456789", "El color que cayo del cielo", 1967, 5, autor1)
biblioteca.RegistrarLibro(libro1)
autor1.librosEscritos.append(libro1.titulo)
    
# Registrar usuario
usuario1 = Usuario("Pedro Perez", "Av. Principal 553", "555-1234")
biblioteca.RegistrarUsuario(usuario1)
    
# Prestar libro
fechaPrestamo = datetime.strptime("2024-04-12", "%Y-%m-%d")
biblioteca.PrestarLibro("123456789", "Pedro Perez", fechaPrestamo)
    
# Devolver libro
fechaDevolucion = datetime.strptime("2024-04-15", "%Y-%m-%d")
biblioteca.DevolverLibro("123456789", "Pedro Perez", fechaDevolucion)
