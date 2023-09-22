public class Salon{

    private int dias = 6;
    private int horas = 24;

    private Sede sede;
    private String edificio;
    private int nivel;
    private int id;
    private int capacidad;

    String disponibilidadSalones[][] = new String[dias][horas];

    public Salon(Sede sede, String edificio, int nivel, int id, int capacidad){
        this.sede = sede;
        this.edificio = edificio;
        this.nivel = nivel;
        this.id = id;
        this.capacidad = capacidad;

        for(int i = 0; i < disponibilidadSalones.length; i++){
            for(int j = 0; j < disponibilidadSalones.length; j++){
                disponibilidadSalones[i][j] = "Libre";
            }
        }
    }

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

    public void setHorario(int dia, int hora, Curso curso){
        this.disponibilidadSalones[dia][hora] = "id: " + curso.getId();
    }

    public void mostrarHorario(){
        System.out.println("día/horario | 7:00 a 8:00 | 8:00 a 9:00 | 9:00 a 10:00 | 10:00 a 11:00");
        for(int i = 0; i < disponibilidadSalones.length; i++){
            for(int j = 0; j < disponibilidadSalones.length; j++){
                if(j == 0){
                    switch(i){
                        case 0:
                            System.out.print("Lunes ");
                            break;
                        case 1:
                            System.out.print("Martes ");
                            break;
                        case 2:
                            System.out.print("Miercoles ");
                            break;
                        case 3:
                            System.out.print("Jueves ");
                            break;
                        case 4:
                            System.out.print("Viernes ");
                            break;
                        case 5:
                            System.out.print("Sabado ");
                            break;
                    }                    
                }
                System.out.print("| " + disponibilidadSalones[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public String toString(){
        return "Id Sede: " + sede.getId() + "\nEdificio: " + edificio + "\nNivel: " + nivel + "\nId Salon: " + id + "\nCapacidad: " + capacidad + "\n";
    }
}