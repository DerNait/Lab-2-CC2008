/*
Kevin Josué Villagrán Mérida
Laboratorio #2 
Fecha de creación: 21/08/2023 1:04
Fecha de ultima modificación: 22/08/2023 23:15
*/
public class Salon{

    //Atributos
    private int dias = 6;
    private int horas = 24;

    private Sede sede;
    private String edificio;
    private int nivel;
    private int id;
    private int capacidad;

    private String disponibilidadSalones[][] = new String[dias][horas];//Horario

    public Salon(Sede sede, String edificio, int nivel, int id, int capacidad){//Constructor donde se definen varios atributos y se crea el horario
        this.sede = sede;
        this.edificio = edificio;
        this.nivel = nivel;
        this.id = id;
        this.capacidad = capacidad;

        for(int i = 0; i < disponibilidadSalones.length; i++){
            for(int j = 0; j < disponibilidadSalones[0].length; j++){
                disponibilidadSalones[i][j] = "Libre";
            }
        }
    }

    //Metodos getters
    public Sede getSede(){
        return sede;
    }

    public String getEdificio(){
        return edificio;
    }

    public int getNivel(){
        return nivel;
    }

    public int getId(){
        return id;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public void setHorario(int dia, int hora, Curso curso){//Se asigna un curso en un dia y hora del horario de este salon
        this.disponibilidadSalones[dia][hora] = "id: " + curso.getId();
    }

    public String getHorario(int dia, int hora){//Retorna el valor del salon segun la hora y el dia
        return disponibilidadSalones[dia][hora];
    }

    public void mostrarHorario(){//Muestra el horario, sus espacios libres y ocupados de 7 a 14 horas.
        System.out.println("día/horario\t7:00 a 8:00\t8:00 a 9:00\t9:00 a 10:00\t10:00 a 11:00"+"\t11:00 a 12:00"+"\t12:00 a 13:00"+"\t13:00 a 14:00");
        for(int i = 0; i < disponibilidadSalones.length; i++){
            for(int j = 7; j < 14; j++){
                if(j == 7){
                    switch(i){
                        case 0:
                            System.out.print("Lunes\t");
                            break;
                        case 1:
                            System.out.print("Martes\t");
                            break;
                        case 2:
                            System.out.print("Miercoles");
                            break;
                        case 3:
                            System.out.print("Jueves\t");
                            break;
                        case 4:
                            System.out.print("Viernes\t");
                            break;
                        case 5:
                            System.out.print("Sabado\t");
                            break;
                    }                    
                }
                if(disponibilidadSalones[i][j].equals("Libre"))
                    System.out.print("\t" + disponibilidadSalones[i][j] +"\t");
                else
                    System.out.print("\t"+disponibilidadSalones[i][j]);
            }
            System.out.println("");
        }
    }

    public String toString(){//Muestra los detalles del salon
        return "Id Sede: " + sede.getId() + "\nEdificio: " + edificio + "\nNivel: " + nivel + "\nId Salon: " + id + "\nCapacidad: " + capacidad + "\n";
    }
}