class Estudiante:
    def __init__(self, nombre, direccion, numeroMatricula):
        self.nombre = nombre
        self.direccion = direccion
        self.numeroMatricula = numeroMatricula
        self.asignaturas = []
        self.notas = []  # Lista de tuplas (codigo de asignatura, nota)
        self.asistencia = []  # Lista de tuplas (codigo de asignatura, faltas de asistencia)

    def agregarAsignatura(self, codigoAsignatura):
        self.asignaturas.append(codigoAsignatura)

    def agregarNota(self, codigoAsignatura, nota):
        self.notas.append((codigoAsignatura, nota))

    def agregarAsistencia(self, codigoAsignatura, faltas):
        self.asistencia.append((codigoAsignatura, faltas))

    def obtener_nota(self, codigoAsignatura):
        for codigo, nota in self.notas:
            if codigo == codigoAsignatura:
                return nota
        return -1  # Valor que indica que no se encontro la nota

    def obtener_asistencia(self, codigoAsignatura):
        for codigo, faltas in self.asistencia:
            if codigo == codigoAsignatura:
                return faltas
        return -1  # Valor que indica que no se encontro la asistencia


class Profesor:
    def __init__(self, nombre, direccion, telefono):
        self.nombre = nombre
        self.direccion = direccion
        self.telefono = telefono
        self.asignaturas = []

    def agregar_asignatura(self, codigoAsignatura):
        self.asignaturas.append(codigoAsignatura)


class Asignatura:
    def __init__(self, codigo, nombre, descripcion, nombreProfesor):
        self.codigo = codigo
        self.nombre = nombre
        self.descripcion = descripcion
        self.nombreProfesor = nombreProfesor



estudiante1 = Estudiante("Juan Perez", "123 Calle Ficticia", "S001")
profesor1 = Profesor("Ing. Oscar Flores", "456 Calle Ejemplo", "555-1234")
asignatura1 = Asignatura("LP02", "Programacion", "Lenguajes de la Programacion", "Oscar Flores")

estudiante1.agregarAsignatura(asignatura1.codigo)
profesor1.agregarAsignatura(asignatura1.codigo)

estudiante1.agregarNota(asignatura1.codigo, 85.0)
estudiante1.agregarAsistencia(asignatura1.codigo, 2)

print(f"Estudiante: {estudiante1.nombre}")
print("Asignaturas matriculadas:")
for asignatura in estudiante1.asignaturas:
    print(f"- {asignatura}")

print("Notas:")
for codigo, nota in estudiante1.notas:
    print(f"- Asignatura: {codigo}, Nota: {nota}")

print("Faltas de asistencia:")
for codigo, faltas in estudiante1.asistencia:
    print(f"- Asignatura: {codigo}, Faltas: {faltas}")

print(f"\nProfesor: {profesor1.nombre}")
print("Asignaturas impartidas:")
for asignatura in profesor1.asignaturas:
    print(f"- {asignatura}")

