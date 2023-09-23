/*
Kevin Josué Villagrán Mérida
Laboratorio #2 
Fecha de creación: 21/08/2023 1:04
Fecha de ultima modificación: 22/08/2023 23:15
*/

//Importamos librerias
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Driver{
    public static void main(String[] args){//Main
        int dias = 6;
        int horas = 15;

        //Listados de las diferentes clases
        ArrayList<Salon> salones = new ArrayList<Salon>();
        ArrayList<Curso> cursosNoAsignados = new ArrayList<Curso>();
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        ArrayList<Sede> sedes = new ArrayList<Sede>();
        String disponibilidadSalones[][] = new String[dias][horas];

        //Llamamos a los metodos
        cargarArchivos(salones, cursos, sedes);
        menu(salones, cursos, disponibilidadSalones, sedes, cursosNoAsignados);
    }

    public static void cargarArchivos(ArrayList<Salon> salones, ArrayList<Curso> cursos, ArrayList<Sede> sedes){
        try{
            Scanner scan = new Scanner(new File("salones.csv"));//Se intenta cargar el archivo de salones
            scan.useDelimiter(";");

            if(scan.hasNextLine()){
                scan.nextLine();
            }
            while(scan.hasNextLine()){
                String[] salonData = scan.nextLine().split(";");

                //Creamos las sedes segun las que estan presentes en el archivo de salones
                Sede sede = null;
                int salonesDiferentes = 0;
                int sedeId = Integer.parseInt(salonData[0]);
                if(sedes.size() == 0){
                    sede = new Sede(sedeId);
                    sedes.add(sede);
                }
                else{
                    for(int i = 0; i < sedes.size(); i++){
                        if(sedes.get(i).getId() == sedeId){
                            sede = new Sede(sedeId);
                            salonesDiferentes = 0;
                        }
                        else
                            salonesDiferentes++;
                    }
                    if(salonesDiferentes >= sedes.size()){
                        sede = new Sede(sedeId);
                        sedes.add(sede);
                        salonesDiferentes = 0;
                    }
                }

                //Se crea el salon y se le asigna su respectiva sede
                if(sede != null){
                    Salon salon = new Salon(sede, salonData[1], Integer.parseInt(salonData[2]), Integer.parseInt(salonData[3]), Integer.parseInt(salonData[4]));
                    salones.add(salon);
                }
                else
                System.out.println("Sede es nulo");
            }
            scan.close();

            //Se imprimen los salones
            System.out.println("===SALONES===\n");
            for(int i = 0; i < salones.size(); i++)
                System.out.println(salones.get(i)); 

            //Se imprimen las sedes
            System.out.println("===SEDES===\n");
            for(int i = 0; i < sedes.size(); i++)
                System.out.println(sedes.get(i));

            System.out.println("Archivo salones.csv cargado correctamente"); //Se notifica que se cargo correctamente

        }catch(Exception e){//En caso de que falle en cargar, mandara este mensaje
            System.out.println("No se pudo cargar el archivo de salones.csv");
            System.out.println("Motivo: " + e);
        }

        try{//Se intenta cargar el archivo de cursos
            Scanner scan = new Scanner(new File("cursos.csv"));
            
            if(scan.hasNextLine()){
                scan.nextLine();
            }
            while(scan.hasNextLine()){
                String[] cursoData = scan.nextLine().split(";");

                Sede sede = null;
                for(int i = 0; i < sedes.size(); i++){
                    if(sedes.get(i).getId() == Integer.parseInt(cursoData[1]))
                        sede = sedes.get(i);
                }

                //Se crean los cursos con su respectiva sede asignada
                Curso curso = new Curso(Integer.parseInt(cursoData[0]), sede, cursoData[2], Integer.parseInt(cursoData[3]), Integer.parseInt(cursoData[4]), cursoData[5], Integer.parseInt(cursoData[6]));
                cursos.add(curso);
            }
            scan.close();

            //Se imprimen los cursos
            System.out.println("\n===CURSOS===\n");
            for(int i = 0; i < cursos.size(); i++)
                System.out.println(cursos.get(i));

            System.out.println("Archivo cursos.csv cargado correctamente\n"); //Se notifica que se cargo el archivo

        }catch(Exception e){ //Si el archivo falla en cargar, se manda este mensaje
            System.out.println("No se pudo cargar el archivo de cursos.csv");
            System.out.println("Motivo: " + e);
        }
    }

    public static void menu(ArrayList<Salon> salones, ArrayList<Curso> cursos, String disponibilidadSalones[][], ArrayList<Sede> sedes, ArrayList<Curso> cursosNoAsignados){
        //Variables para que el ciclo del menu funcione
        Scanner scan = new Scanner(System.in);
        boolean salir = false;
        String opcion;

        while(!salir){//Ciclo del menu que seguira hasta que se decida salir
            System.out.println("\n=== ASIGNACIÓN DE SALONES ===\n");
            System.out.println("¿Qué desea hacer? Ingrese el numero de la opción que desea escoger");
            System.out.println("1. Consultar un salon");
            System.out.println("2. Consultar un curso");
            System.out.println("3. Asignar salon a los cursos");
            System.out.println("4. Salir");
            opcion = scan.nextLine();

            switch(opcion){//Las diferentes opciones que tiene el usuario
                case "1":
                    consultarSalon(salones);
                    break;
                case "2":
                    consultarCurso(cursos);
                    break;
                case "3":
                    asignarCursos(cursos, salones, sedes, cursosNoAsignados);
                    break;
                case "4":
                    System.out.println("¡Hasta pronto!");
                    salir = true;
                    break;
                default: //Si introduce un valor invalido, le saldra el siguiente mensaje
                    System.out.println("\nPor favor, ingrese un valor numerico valido");
            }
        }
    }

    public static void consultarSalon(ArrayList<Salon> salones){
        //Variables que el usuario introducira para encontrar el salon
        boolean anException = false;
        Scanner scan = new Scanner(System.in);
        int salonesErroneos = 0;
        
        int sede = 0; 
        int nivel = 0;
        int id = 0; 

        //Excepciones para que el usuario ingrese los valores correctos
        do{
            System.out.println("Ingrese la Sede del salón: ");
            try{//Se asegura que el valor introducido sea un valor numerico y no algo mas
                sede = Integer.parseInt(scan.nextLine());
                anException = false;
            }catch(Exception e){//Si el usuario introduce un valor incorrecto, el ciclo se repetira hasta que coloque un valor valido.
                System.out.println("\nIntroduzca un valor numerico valido");
                anException = true;
            }
        } while(anException);
        
        System.out.println("Ingrese el Edificio del salón: ");
        String edificio = scan.nextLine();

        do{
            System.out.println("Ingrese el Nivel del salón: ");
            try{//Se asegura que el valor introducido sea un valor numerico y no algo mas
                nivel = Integer.parseInt(scan.nextLine());
                anException = false;
            }catch(Exception e){//Si el usuario introduce un valor incorrecto, el ciclo se repetira hasta que coloque un valor valido.
                System.out.println("\nIntroduzca un valor numerico valido");
                anException = true;
            }
        } while(anException);

        do{
            System.out.println("Ingrese el ID del salón: ");
            try{//Se asegura que el valor introducido sea un valor numerico y no algo mas
                id = Integer.parseInt(scan.nextLine());
                anException = false;
            }catch(Exception e){//Si el usuario introduce un valor incorrecto, el ciclo se repetira hasta que coloque un valor valido.
                System.out.println("\nIntroduzca un valor numerico valido");
                anException = true;
            }
        } while(anException);

        //Se busca el salon con base a los datos que ingreso el usuario
        for(int i = 0; i < salones.size(); i++){
            if(salones.get(i).getSede().getId() == sede && salones.get(i).getEdificio().equals(edificio) && salones.get(i).getNivel() == nivel && salones.get(i).getId() == id){
                System.out.println("\n" + salones.get(i));
                
                salones.get(i).mostrarHorario();//Si los datos coinciden, se muestra el horario de dicho salon
            }
            else
                salonesErroneos++;
        }
        if(salonesErroneos >= salones.size())//Si los datos no coinciden con ningun salon, se lanza el siguiente mensaje
            System.out.println("\nERROR: NO SE HA ENCONTRADO ESE SALON");
        
        System.out.println("\nPresione enter para continuar...");
        String continuar = scan.nextLine();
    }

    public static void consultarCurso(ArrayList<Curso> cursos){
        //Variables para que el usuario introduzca los datos del curso
        Scanner scan = new Scanner(System.in);
        boolean anException = false;
        int cursosErroneos = 0;
        int id = 0;

        do{
            System.out.println("Ingrese el ID del curso: ");;
            try{//Se asegura que el valor introducido sea un valor numerico y no algo mas
                id = Integer.parseInt(scan.nextLine());
                anException = false;
            }catch(Exception e){//Si el usuario introduce un valor incorrecto, el ciclo se repetira hasta que coloque un valor valido.
                System.out.println("\nIntroduzca un valor numerico valido");
                anException = true;
            }
        } while(anException);

        for(int i = 0; i < cursos.size(); i++)//Si lo encuentra, mostrara los detalles de dicho curso
                if(cursos.get(i).getId() == id)
                    System.out.println("\n" + cursos.get(i));
                else
                    cursosErroneos++;
        if(cursosErroneos >= cursos.size())//Si no, le notificara que no se ha encontrado
            System.out.println("\nERROR: NO SE HA ENCONTRADO ESE CURSO");

        System.out.println("\nPresione enter para continuar...");
        String continuar = scan.nextLine();
    }

    public static void asignarCursos(ArrayList<Curso> cursos, ArrayList<Salon> salones, ArrayList<Sede> sedes, ArrayList<Curso> cursosNoAsignados){
        Scanner scan = new Scanner(System.in);
        int salonesDescartados;

        ArrayList<Curso> cursosAsignados = new ArrayList<Curso>();
        
        System.out.println("\n=== CURSOS ASIGNADOS CORRECTAMENTE ===");
        //Recorre todos los cursos y salones, hasta que el curso cumpla con cada condicion del salon
        for(int i = 0; i < cursos.size(); i++){
            salonesDescartados = 0;
            for(int j = 0; j < salones.size(); j++){
                if(cursos.get(i).getSede() == salones.get(j).getSede() && cursos.get(i).getCantidadEstudiantes() <= salones.get(j).getCapacidad()){//Busca que esten en la misma sede y que ademas, cumpla con la capacidad de estudiantes adecuada.
                    String[] diasSemana =  cursos.get(i).getDias().split(",");
                    int diaOcupado;
                    int diasValidos = 0;
                    int horasValidas = 0;
                    for(String dia : diasSemana){//Busca que los dias del curso coincidan con los que el salon tiene libre
                        switch(dia){
                            case "lunes":
                                diaOcupado = 0;
                                break;
                            case "martes":
                                diaOcupado = 1;
                                break;
                            case "miercoles":
                                diaOcupado = 2;
                                break;
                            case "jueves":
                                diaOcupado = 3;
                                break;
                            case "viernes":
                                diaOcupado = 4;
                                break;
                            case "sabado":
                                diaOcupado = 5;
                                break;
                            default:
                                diaOcupado = 0;
                                break;
                            }
                        
                        for(int k = 0; k < cursos.get(i).getDuracion(); k++){//Busca que la duracion del curso, coincida con las horas libres que tiene el salon
                            if(salones.get(j).getHorario(diaOcupado,cursos.get(i).getHorario()+k).equals("Libre"))
                                horasValidas++;
                            else{
                                diasValidos = 0;
                            }
                            
                            if(horasValidas >= (cursos.get(i).getDuracion()-1))
                                diasValidos++;
                        }

                        if(diasValidos >= diasSemana.length){//Si los dias son validos, busca cuantos va a necesitar
                            for(String diaUsar : diasSemana){
                                switch(diaUsar){
                                    case "lunes":
                                        diaOcupado = 0;
                                        break;
                                    case "martes":
                                        diaOcupado = 1;
                                        break;
                                    case "miercoles":
                                        diaOcupado = 2;
                                        break;
                                    case "jueves":
                                        diaOcupado = 3;
                                        break;
                                    case "viernes":
                                        diaOcupado = 4;
                                        break;
                                    case "sabado":
                                        diaOcupado = 5;
                                        break;
                                    default:
                                        diaOcupado = 0;
                                        break;
                                    }
                                
                                for(int k = 0; k < cursos.get(i).getDuracion(); k++){//Ya que los dias y las horas son validas, busca las que va a necesitar
                                    salones.get(j).setHorario(diaOcupado,cursos.get(i).getHorario()+k, cursos.get(i));//Asigna el nuevo horario del salon
                                }
                            }

                            if(cursos.get(i).getCantidadEstudiantes() <= salones.get(j).getCapacidad()){//Asigna el salon al curso y lo imprime en la lista de asignados
                                cursos.get(i).setSalon(salones.get(j));
                                cursosAsignados.add(cursos.get(i));
                                System.out.println("Curso " + cursos.get(i).getNombre() + " asignado correctamente al salon " + salones.get(j).getId());                                
                            }
                        } 
                    }
                }
                else
                    salonesDescartados++;//Descarta el salon si no cumple con las condiciones

                if(salonesDescartados >= salones.size()){//Se le asigna a la lista de no asignados si no cumple con las condiciones de ningun salon
                    if(!cursosNoAsignados.contains(cursos.get(i)))
                            cursosNoAsignados.add(cursos.get(i));
                }
            }
        }

        System.out.println("\n=== CURSOS SIN ASIGNAR ===");//Muestra la lista de no asignados
        for(int i = 0; i < cursosNoAsignados.size(); i++)
            System.out.println(cursosNoAsignados.get(i).getNombre());

                //CREACION DEL ARCHIVO CSV
        File archivoCSV = new File("cursos_salones.csv");//Nombre del archivo
        
        try{
            PrintWriter out = new PrintWriter(archivoCSV);//Se busca escribir en el archivo nombrado anteriormente
            String titulos[] = {"id_sede","id_curso","nombre_curso","horario","duracion","dias","cantidad_estudiantes","id_salon","edificio_salon","nivel_salon"}; //Titulos del CSV
            
            for(String titulo : titulos)//Se agrega cada titulo separado por el delimitador ;
                out.print(titulo + ";");
            
            out.println();

            for(Curso cursoAsignado : cursosAsignados){//Se agregan los datos del curso con su salon asignado
                out.println(cursoAsignado.getSede().getId()+";"+cursoAsignado.getId()+";"+cursoAsignado.getNombre()+";"+cursoAsignado.getHorario()+";"+cursoAsignado.getDuracion()+";"+cursoAsignado.getDias()+";"+cursoAsignado.getCantidadEstudiantes()+";"+cursoAsignado.getSalon().getId()+";"+cursoAsignado.getSalon().getEdificio()+";"+cursoAsignado.getSalon().getNivel());
            }

            out.close();//Se cierra el printwriter pues ya no se escribira mas
        }
        catch(FileNotFoundException e){//En caso de que no encuentre el archivo, tirara el mensaje
            System.out.println("No se ha encontrado el archivo");
        }

        System.out.println("\nArchivo " + archivoCSV + " creado correctamente");

        System.out.println("\nPresione enter para continuar...");
        String continuar = scan.nextLine();
    }
}