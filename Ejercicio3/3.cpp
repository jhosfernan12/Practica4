#include <iostream>
#include <string>
#include <vector>

using namespace std;


class Estudiante
 {
    public:
        string nombre;
        string direccion;
        string numeroMatricula;
        vector<string> asignaturas;
        vector<pair<string, double>> notas; 
        vector<pair<string, int>> asistencia; 
        

        Estudiante(string n, string dir, string numMat) 
            : nombre(n), direccion(dir), numeroMatricula(numMat) {}
        
        
        void agregarAsignatura(const string& codigoAsignatura) 
        {
            asignaturas.push_back(codigoAsignatura);
        }
        
        void agregarNota(const string& codigoAsignatura, double nota) 
        {
            notas.push_back({codigoAsignatura, nota});
        }
        
        void agregarAsistencia(const string& codigoAsignatura, int faltas) 
        {
            asistencia.push_back({codigoAsignatura, faltas});
    }
    

    double obtenerNota(const string& codigoAsignatura) {

        for (const auto& nota : notas) {
            if (nota.first == codigoAsignatura)
            {
                return nota.second;
            }
        }
        return -1; // Valor que indica que no se encontrO la nota
    }
    

    int obtenerAsistencia(const string& codigoAsignatura) 
    {
        for (const auto& falta : asistencia) 
        {
            if (falta.first == codigoAsignatura) 
            {
                return falta.second;
            }
        }
        return -1; // Valor que indica que no se encontro la asistencia
    }
};


class Profesor 
{
public:
    string nombre;
    string direccion;
    string telefono;
    vector<string> asignaturas;
    

    Profesor(string n, string dir, string tel) 
        : nombre(n), direccion(dir), telefono(tel) {}
    
    void agregarAsignatura(const string& codigoAsignatura) 
    {
        asignaturas.push_back(codigoAsignatura);
    }
};


class Asignatura 
{
public:
    string codigo;
    string nombre;
    string descripcion;
    string nombreProfesor;
    

    Asignatura(string c, string n, string desc, string nomProf) 
        : codigo(c), nombre(n), descripcion(desc), nombreProfesor(nomProf) {}
};


int main() 
{
    Estudiante estudiante1("Juan Perez", "123 Calle Ficticia", "S001");
    Profesor profesor1("Ing. Oscar Flores", "456 Calle Ejemplo", "555-1234");
    Asignatura asignatura1("LP02", "Programacion", "Lenguajes de la Programacion", "Oscar Flores");
    
    estudiante1.agregarAsignatura(asignatura1.codigo);
    profesor1.agregarAsignatura(asignatura1.codigo);
    
    estudiante1.agregarNota(asignatura1.codigo, 85.0);
    estudiante1.agregarAsistencia(asignatura1.codigo, 2);

    cout << "Estudiante: " << estudiante1.nombre << endl;
    cout << "Asignaturas matriculadas: " << endl;
    for (const auto& asignatura : estudiante1.asignaturas) 
    {
        cout << "- " << asignatura << endl;
    }
    
    cout << "Notas: " << endl;
    for (const auto& nota : estudiante1.notas) 
    {
        cout << "- Asignatura: " << nota.first << ", Nota: " << nota.second << endl;
    }
    
    cout << "Faltas de asistencia: " << endl;
    for (const auto& falta : estudiante1.asistencia) 
    {
        cout << "- Asignatura: " << falta.first << ", Faltas: " << falta.second << endl;
    }
    
    cout << "\nProfesor: " << profesor1.nombre << endl;
    cout << "Asignaturas impartidas: " << endl;
    for (const auto& asignatura : profesor1.asignaturas) 
    {
        cout << "- " << asignatura << endl;
    }
    
    return 0;
}
