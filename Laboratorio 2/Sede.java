/*
Kevin Josué Villagrán Mérida
Laboratorio #2 
Fecha de creación: 21/08/2023 1:04
Fecha de ultima modificación: 22/08/2023 23:15
*/
public class Sede{

    //Id de la sede
    private int id;

    public Sede(int id){//Constructor donde se asigna el id
        this.id = id;
    }

    public int getId(){//Retorna el id
        return id;
    }

    public String toString(){//Detalla el id
        return "Sede ID: " + id;
    }
}