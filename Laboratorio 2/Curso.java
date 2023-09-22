public class Curso{
    
    private int id;
    private Sede sede;
    private String nombre;
    private int duracion;
    private int horario;
    private String dias;
    private int cantidadEstudiantes;

    public Curso(int id, Sede sede, String nombre, int horario, int duracion, String dias, int cantidadEstudiantes){
        this.id = id;
        this.sede = sede;
        this.nombre = nombre;
        this.horario = horario;
        this.duracion = duracion;
        this.dias = dias;
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public int getId(){
        return id;
    }

    public String toString(){
        return "Id Curso: " + id + "\nId Sede: " + sede.getId() + "\nNombre: " + nombre + "\nHorario: " + horario + "\nDuracion: " + duracion + " horas" + "\nDias: " + dias + "\nCantidad de estudiantes: " + cantidadEstudiantes + "\n";
    }
}