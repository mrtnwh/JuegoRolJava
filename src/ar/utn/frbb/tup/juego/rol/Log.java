package ar.utn.frbb.tup.juego.rol;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    public static DateTimeFormatter dia = DateTimeFormatter.ofPattern("yy-MM-dd");
    public static String fecha = dia.format(LocalDateTime.now());
    public static File directorio = new File("E:\\\\Juego\\\\src\\\\ar\\\\utn\\\\frbb\\\\tup\\\\juego\\\\rol\\\\partidas\\\\"+ fecha);
    public static DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH-mm");
    public static String date = hora.format(LocalDateTime.now());
    public static String filepath = directorio+"\\\\"+date+".log";

    public static void loguearLineaTexto(String texto) {
        System.out.println(texto);

        FileWriter file = null;
        try {
            file = new FileWriter(filepath, true);
            BufferedWriter output = new BufferedWriter(file);
            output.write(texto+"\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void crearPartida(){
        try{

            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    System.out.println("Directorio creado");
                } else {
                    System.out.println("Error al crear directorio");
                }
            }

            String filepath = directorio+"\\"+date+".log";
            FileWriter file = new FileWriter(filepath, true);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void leerPartidas(String filepath) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));

        try {
            String linea = "";

            while ((linea = (br.readLine())) != null) {
                System.out.println(linea);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String borrarArchivo(){
        String estado = "";
        File file = new File(String.valueOf(directorio));

        if (file.exists()){
            final File[] files = file.listFiles();
            for (File f: files) f.delete();
            file.delete();
            estado = "el archivo se ha podido eliminar correctamente";
        }
        else{
            estado = "el archivo no se ha podido eliminar.";
        }
        return estado;
    }
}
