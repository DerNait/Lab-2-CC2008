/*
Kevin Josué Villagrán Mérida
Laboratorio #2 
Fecha de creación: 21/08/2023 1:04
Fecha de ultima modificación: 22/08/2023 23:15
*/
public class Curso{
    
    //Atributos
    private int id;
    private Sede sede;
    private Salon salon;
    private String nombre;
    private int duracion;
    private int horario;
    private String dias;
    private int cantidadEstudiantes;

    //Metodos
    public Curso(int id, Sede sede, String nombre, int horario, int duracion, String dias, int cantidadEstudiantes){//Constructor donde settea los valores de varios de los atributos
        this.id = id;
        this.sede = sede;
        this.nombre = nombre;
        this.horario = horario;
        this.duracion = duracion;
        this.dias = dias;
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    //Getters
    public String getNombre(){
        return nombre;
    }

    public int getDuracion(){
        return duracion;
    }

    public int getId(){
        return id;
    }

    public String getDias(){
        return dias;
    }

    public int getHorario(){
        return horario;
    }

    public Sede getSede(){
        return sede;
    }

    public void setSalon(Salon salon){//Se le asigna el salon
        this.salon = salon;
    }

    public Salon getSalon(){
        return salon;
    }

    public int getCantidadEstudiantes(){
        return cantidadEstudiantes;
    }

    public String toString(){//Muestra los detalles del curso
        return "Id Curso: " + id + "\nId Sede: " + sede.getId() + "\nNombre: " + nombre + "\nHorario: " + horario + "\nDuracion: " + duracion + " horas" + "\nDias: " + dias + "\nCantidad de estudiantes: " + cantidadEstudiantes + "\n";
    }
}