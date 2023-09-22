import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Driver{
    public static void main(String[] args){
        int dias = 6;
        int horas = 15;

        ArrayList<Salon> salones = new ArrayList<Salon>();
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        ArrayList<Sede> sedes = new ArrayList<Sede>();
        String disponibilidadSalones[][] = new String[dias][horas];

        cargarArchivos(salones, cursos, sedes);
        menu(salones, cursos, disponibilidadSalones, sedes);
    }

    public static void cargarArchivos(ArrayList<Salon> salones, ArrayList<Curso> cursos, ArrayList<Sede> sedes){
        try{
            Scanner scan = new Scanner(new File("salones.csv"));
            scan.useDelimiter(";");

            if(scan.hasNextLine()){
                scan.nextLine();
            }
            while(scan.hasNextLine()){
                String[] salonData = scan.nextLine().split(";");

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

                if(sede != null){
                    Salon salon = new Salon(sede, salonData[1], Integer.parseInt(salonData[2]), Integer.parseInt(salonData[3]), Integer.parseInt(salonData[4]));
                    salones.add(salon);
                }
                else
                System.out.println("Sede es nulo");
            }
            scan.close();

            System.out.println("===SALONES===\n");
            for(int i = 0; i < salones.size(); i++)
                System.out.println(salones.get(i));

            System.out.println("===SEDES===\n");
            for(int i = 0; i < sedes.size(); i++)
                System.out.println(sedes.get(i));

            System.out.println("Archivo salones.csv cargado correctamente");

        }catch(Exception e){
            System.out.println("No se pudo cargar el archivo de salones.csv");
            System.out.println("Motivo: " + e);
        }

        try{
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

                Curso curso = new Curso(Integer.parseInt(cursoData[0]), sede, cursoData[2], Integer.parseInt(cursoData[3]), Integer.parseInt(cursoData[4]), cursoData[5], Integer.parseInt(cursoData[6]));
                cursos.add(curso);
            }
            scan.close();

            System.out.println("\n===CURSOS===\n");
            for(int i = 0; i < cursos.size(); i++)
                System.out.println(cursos.get(i));

            System.out.println("Archivo cursos.csv cargado correctamente\n");

        }catch(Exception e){
            System.out.println("No se pudo cargar el archivo de cursos.csv");
            System.out.println("Motivo: " + e);
        }
    }

    public static void menu(ArrayList<Salon> salones, ArrayList<Curso> cursos, String disponibilidadSalones[][], ArrayList<Sede> sedes){
        Scanner scan = new Scanner(System.in);
        boolean salir = false;
        String opcion;

        while(!salir){
            System.out.println("\n=== ASIGNACIÓN DE SALONES ===\n");
            System.out.println("¿Qué desea hacer? Ingrese el numero de la opción que desea escoger");
            System.out.println("1. Consultar un salon");
            System.out.println("2. Consultar un curso");
            System.out.println("3. Asignar salon a los cursos");
            System.out.println("4. Salir");
            opcion = scan.nextLine();

            switch(opcion){
                case "1":
                    consultarSalon(salones);
                    break;
                case "2":
                    consultarCurso(cursos);
                    break;
                case "3":
                    asignarCursos(cursos, salones, sedes);
                    break;
                case "4":
                    System.out.println("¡Hasta pronto!");
                    salir = true;
                    break;
                default:
                    System.out.println("\nPor favor, ingrese un valor numerico valido");
            }
        }
    }

    public static void consultarSalon(ArrayList<Salon> salones){
        Scanner scan = new Scanner(System.in);
        int salonesErroneos = 0;

        System.out.println("Ingrese la Sede del salón: ");
        int sede = Integer.parseInt(scan.nextLine());
        System.out.println("Ingrese el Edificio del salón: ");
        String edificio = scan.nextLine();
        System.out.println("Ingrese el Nivel del salón: ");
        int nivel = Integer.parseInt(scan.nextLine());
        System.out.println("Ingrese el ID del salón: ");
        int id = Integer.parseInt(scan.nextLine());

        for(int i = 0; i < salones.size(); i++){
            if(salones.get(i).getSede().getId() == sede && salones.get(i).getEdificio().equals(edificio) && salones.get(i).getNivel() == nivel && salones.get(i).getId() == id){
                System.out.println("\n" + salones.get(i));
                
                salones.get(i).mostrarHorario();
            }
            else
                salonesErroneos++;
        }
        if(salonesErroneos >= salones.size())
            System.out.println("\nERROR: NO SE HA ENCONTRADO ESE SALON");
        
        System.out.println("\nPresione enter para continuar...");
        String continuar = scan.nextLine();
    }

    public static void consultarCurso(ArrayList<Curso> cursos){
        Scanner scan = new Scanner(System.in);
        int cursosErroneos = 0;

        System.out.println("Ingrese el ID del curso: ");
        int id = Integer.parseInt(scan.nextLine());

        for(int i = 0; i < cursos.size(); i++)
                if(cursos.get(i).getId() == id)
                    System.out.println("\n" + cursos.get(i));
                else
                    cursosErroneos++;
        if(cursosErroneos >= cursos.size())
            System.out.println("\nERROR: NO SE HA ENCONTRADO ESE CURSO");

        System.out.println("\nPresione enter para continuar...");
        String continuar = scan.nextLine();
    }

    public static void asignarCursos(ArrayList<Curso> cursos, ArrayList<Salon> salones, ArrayList<Sede> sedes){
        for(int i = 0; i < cursos.size(); i++){
            for(int j = 0; j < salones.size(); j++){
                if(cursos.get(i).getSede() == salones.get(j).getSede()){
                    String[] diasSemana =  cursos.get(i).getDias().split(",");
                    for(int k = 0; k < diasSemana.length; k++){
                        System.out.println(k);
                        System.out.println(cursos.get(i).getHorario());
                        System.out.println(salones.get(j).getHorario(k,cursos.get(i).getHorario()));
                        /*if(salones.get(j).getHorario(k,cursos.get(i).getHorario()).equals("Libre"))
                            salones.get(j).setHorario(k,cursos.get(i).getHorario(), cursos.get(i));
                        else{
                            System.out.println("Este salon no esta disponible");
                        }*/
                    }
                }
            }
        }
    }
}