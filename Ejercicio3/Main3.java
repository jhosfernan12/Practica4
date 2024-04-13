import java.util.ArrayList;
import java.util.List;

class Estudiante 
{
    private String nombre;
    private String direccion;
    private String numeroMatricula;
    private List<String> asignaturas;
    private List<Nota> notas;
    private List<Asistencia> asistencias;

    public Estudiante(String nombre, String direccion, String numeroMatricula) 
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroMatricula = numeroMatricula;
        this.asignaturas = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.asistencias = new ArrayList<>();
    }

    public void agregarAsignatura(String codigoAsignatura) 
    {
        this.asignaturas.add(codigoAsignatura);
    }

    public void agregarNota(String codigoAsignatura, double nota) 
    {
        this.notas.add(new Nota(codigoAsignatura, nota));
    }

    public void agregarAsistencia(String codigoAsignatura, int faltas) 
    {
        this.asistencias.add(new Asistencia(codigoAsignatura, faltas));
    }

    public double obtenerNota(String codigoAsignatura) 
    {
        for (Nota nota : this.notas) {
            if (nota.getCodigoAsignatura().equals(codigoAsignatura)) 
            {
                return nota.getNota();
            }
        }
        return -1; // Valor que devuelve si no se encontro la nota
    }

    public int obtenerAsistencia(String codigoAsignatura) 
    {
        for (Asistencia asistencia : this.asistencias) 
        {
            if (asistencia.getCodigoAsignatura().equals(codigoAsignatura)) 
            {
                return asistencia.getFaltas();
            }
        }
        return -1; // Valor que indica que no se encontro la asistencia
    }

    public String getNombre() 
    {
        return nombre;
    }

    public List<String> getAsignaturas() 
    {
        return asignaturas;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public List<Asistencia> getAsistencias() 
    {
        return asistencias;

    }
}

class Nota {
    private String codigoAsignatura;
    private double nota;

    public Nota(String codigoAsignatura, double nota) 
    {
        this.codigoAsignatura = codigoAsignatura;
        this.nota = nota;
    }

    public String getCodigoAsignatura() 
    {
        return codigoAsignatura;
    }

    public double getNota() 
    {
        return nota;
    }
}

class Asistencia 
{
    private String codigoAsignatura;
    private int faltas;

    public Asistencia(String codigoAsignatura, int faltas) 
    {
        this.codigoAsignatura = codigoAsignatura;
        this.faltas = faltas;
    }

    public String getCodigoAsignatura() 
    {
        return codigoAsignatura;
    }

    public int getFaltas() {
        return faltas;
    }
}

class Profesor 
{
    private String nombre;
    private String direccion;
    private String telefono;
    private List<String> asignaturas;

    public Profesor(String nombre, String direccion, String telefono) 
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.asignaturas = new ArrayList<>();
    }

    public void agregarAsignatura(String codigoAsignatura)
     {
        this.asignaturas.add(codigoAsignatura);
    }

    public String getNombre() 
    {
        return nombre;
    }

    public List<String> getAsignaturas() 
    {
        return asignaturas;
    }
}

class Asignatura 
{
    private String codigo;
    private String nombre;
    private String descripcion;
    private String nombreProfesor;

    public Asignatura(String codigo, String nombre, String descripcion, String nombreProfesor) 
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombreProfesor = nombreProfesor;
    }

    public String getCodigo() 
    {
        return codigo;
    }

    public String getNombre() 
    {
        return nombre;
    }
}

public class Main3 
{
    public static void main(String[] args) 
    {
        Estudiante estudiante1 = new Estudiante("Juan Perez", "123 Calle Ficticia", "S001");
        Profesor profesor1 = new Profesor("Ing. Oscar Flores", "456 Calle Ejemplo", "555-1234");
        Asignatura asignatura1 = new Asignatura("LP02", "Programacion", "Lenguajes de la Programacion", "Oscar Flores");

        estudiante1.agregarAsignatura(asignatura1.getCodigo());
        profesor1.agregarAsignatura(asignatura1.getCodigo());

        estudiante1.agregarNota(asignatura1.getCodigo(), 85.0);
        estudiante1.agregarAsistencia(asignatura1.getCodigo(), 2);

        System.out.println("Estudiante: " + estudiante1.getNombre());
        System.out.println("Asignaturas matriculadas:");
        for (String asignatura : estudiante1.getAsignaturas()) 
        {
            System.out.println("- " + asignatura);
        }

        System.out.println("Notas:");
        for (Nota nota : estudiante1.getNotas()) 
        {
            System.out.println("- Asignatura: " + nota.getCodigoAsignatura() + ", Nota: " + nota.getNota());
        }

        System.out.println("Faltas de asistencia:");
        for (Asistencia asistencia : estudiante1.getAsistencias()) 
        {
            System.out.println("- Asignatura: " + asistencia.getCodigoAsignatura() + ", Faltas: " + asistencia.getFaltas());
        }

        System.out.println("\nProfesor: " + profesor1.getNombre());
        System.out.println("Asignaturas impartidas:");
        for (String asignatura : profesor1.getAsignaturas()) 
        {
            System.out.println("- " + asignatura);
        }
    }
}
