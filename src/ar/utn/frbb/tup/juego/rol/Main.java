package ar.utn.frbb.tup.juego.rol;

import java.io.File;
import java.util.*;

import static ar.utn.frbb.tup.juego.rol.CreacionPersonajes.*;
import static ar.utn.frbb.tup.juego.rol.Log.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {
            System.out.println("\n\b1. Iniciar partida y que el sistema genere los 6 personajes aleatoriamente.");
            System.out.println("2. Iniciar partida pero que los personajes se ingresen a mano");
            System.out.println("3. Leer desde el archivo los logs de todas las partidas jugadas");
            System.out.println("4. Borrar archivo de logs");
            System.out.println("0. Salir del menu.");

            try {
                System.out.println("Ingrese una opcion del menu");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        CreacionPersonajes.crearPersonajes();
                        Log.crearPartida();
                        int count = 1;
                        int count1 = 1;
                        int count2 = 1;
                        int count3 = 1;
                        int count4 = 1;
                        System.out.println(Jugador1.size() + " y el 2 " + Jugador2.size());

                        Log.loguearLineaTexto("----------------------------------------");
                        Log.loguearLineaTexto("  Damos inicio a la ronda de los orcos  ");
                        Log.loguearLineaTexto("----------------------------------------");
                        Personaje p1 = Jugador1.get(0);
                        Personaje p2 = Jugador2.get(0);
                        Personaje p11 = Jugador1.get(1);
                        Personaje p21 = Jugador2.get(1);
                        Personaje p12 = Jugador1.get(2);
                        Personaje p22 = Jugador2.get(2);

                        loguearLineaTexto("jugador 1: " +p1.getNombre() + ", " +p11.getNombre() + ", " +p12.getNombre());
                        loguearLineaTexto("jugador 2: " +p2.getNombre() + ", " +p21.getNombre() + ", " +p22.getNombre());
                        while(p1.getSalud() > 0 && p2.getSalud() > 0){
                            double eD = Orco.EfectividadDisparo();
                            double vA = Orco.ValorAtaque(Orco.PoderDisparo(p1.getDestreza(), p1.getFuerza(), p1.getNivel()), eD);
                            double efeD = Orco.EfectividadDisparo();
                            double valA = Orco.ValorAtaque(Orco.PoderDisparo(p2.getDestreza(), p2.getFuerza(), p2.getNivel()), efeD);
                            Log.loguearLineaTexto("\nRonda "+ count++ +":");
                            //System.out.println("\nel ataque del primer orco es de "+Orco.Ataque(vA,eD));
                            //System.out.println("el ataque del segundo orco es de "+Orco.Ataque(valA,efeD));
                            p1.setSalud((int) Orco.restarSalud(p1.getSalud(),Orco.Ataque(valA,efeD)));
                            if (p1.getSalud() <= 0){
                                Log.loguearLineaTexto("\nEl orco del jugador 2 mato antes al orco del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p2.getSalud());
                                break;
                            }
                            Log.loguearLineaTexto("\nEl orco del jugador 1 realiza un ataque y queda con una salud de " + p1.getSalud());
                            p2.setSalud((int) Orco.restarSalud(p2.getSalud(),Orco.Ataque(vA,eD)));
                            Log.loguearLineaTexto("El orco del jugador 2 realiza un ataque y queda con una salud de " + p2.getSalud());
                            System.out.println("el primer personaje es "+p1.getRaza() + " y se llama " + p1.getNombre()+" y la efectividad de disparo es de " + eD);
                            System.out.println("el primer personaje es "+p2.getRaza() + " y se llama " + p2.getNombre()+" y la efectividad de disparo es de " + efeD);
                        }

                        if (p1.getSalud() > p2.getSalud()){
                            Log.loguearLineaTexto("\nla primera ronda la gano el jugador numero 1 con su personaje llamado " +p1.getNombre() + " y quedando con una salud de " + p1.getSalud());
                            Jugador2.remove(0);
                            Log.loguearLineaTexto("la lista del jugador uno es de  "+Jugador1.size()+ " mientras que la del 2 es de " + Jugador2.size());

                        }
                        else{
                            Log.loguearLineaTexto("\nla primera ronda la gano el jugador numero 2 con su personaje llamado " +p2.getNombre() + " y quedando con una salud de " + p2.getSalud());
                            Jugador1.remove(0);
                            Log.loguearLineaTexto("la lista del jugador uno es de  "+Jugador1.size()+ " mientras que la del 2 es de " + Jugador2.size());

                        }

                        Log.loguearLineaTexto("\n----------------------------------------");
                        Log.loguearLineaTexto("  Damos inicio a la ronda de los elfos  ");
                        Log.loguearLineaTexto("----------------------------------------");
                        while(p11.getSalud() >= 0 && p21.getSalud() >= 0){
                            double eD = Elfo.EfectividadDisparo();
                            double vA = Elfo.ValorAtaque(Elfo.PoderDisparo(p11.getDestreza(), p11.getFuerza(), p11.getNivel()), eD);
                            double efeD = Elfo.EfectividadDisparo();
                            double valA = Elfo.ValorAtaque(Elfo.PoderDisparo(p21.getDestreza(), p21.getFuerza(), p21.getNivel()), efeD);

                            Log.loguearLineaTexto("\nRonda "+ count1++ +":");
                            //System.out.println("el ataque del primer elfo es de "+Orco.Ataque(vA,eD));
                            //System.out.println("el ataque del segundo elfo es de "+Orco.Ataque(valA,efeD));
                            p11.setSalud((int) Elfo.restarSalud(p11.getSalud(),Elfo.Ataque(valA,efeD)));
                            if (p11.getSalud() <= 0){
                                Log.loguearLineaTexto("\nEl elfo del jugador 2 mato antes al elfo del jugador 1 con su ataque, haciendo que este no pueda reaccionar , quedando con una salud de "  + p21.getSalud());
                                break;
                            }
                            Log.loguearLineaTexto("\nEl elfo del jugador 1 realiza un ataque y queda con una salud de " + p11.getSalud());
                            p21.setSalud((int) Elfo.restarSalud(p21.getSalud(),Elfo.Ataque(vA,eD)));
                            Log.loguearLineaTexto("El elfo del jugador 2 realiza un ataque y queda con una salud de " + p21.getSalud());
                            System.out.println("el primer personaje es "+p11.getRaza() + " y se llama " + p11.getNombre()+" y la efectividad de disparo es de " + eD);
                            System.out.println("el primer personaje es "+p21.getRaza() + " y se llama " + p21.getNombre()+" y la efectividad de disparo es de " + efeD);
                        }

                        if (p11.getSalud() > p21.getSalud()){
                            Log.loguearLineaTexto("\nla segunda ronda la gano el jugador numero 1 con su personaje llamado " +p11.getNombre() + " y quedando con una salud de " + p11.getSalud());
                            if (Jugador2.size() == 2){
                            Jugador2.remove(0);
                            }else {
                                Jugador2.remove(1);
                            }
                            Log.loguearLineaTexto("la lista del jugador uno es de  "+Jugador1.size()+ " mientras que la del 2 es de " + Jugador2.size());

                        }
                        else{
                            Log.loguearLineaTexto("\nla segunda ronda la gano el jugador numero 2 con su personaje llamado " +p21.getNombre() + " y quedando con una salud de " + p21.getSalud());
                            if (Jugador1.size() == 2){
                                Jugador1.remove(0);
                            }else{
                                Jugador1.remove(1);
                            }
                            Log.loguearLineaTexto("la lista del jugador uno es de  "+Jugador1.size()+ " mientras que la del 2 es de " + Jugador2.size());

                        }


                        Log.loguearLineaTexto("\n----------------------------------------");
                        Log.loguearLineaTexto(" Damos inicio a la ronda de los humanos ");
                        Log.loguearLineaTexto("----------------------------------------");
                        while(p12.getSalud() >= 0 && p22.getSalud() >= 0){
                            double eD = Humano.EfectividadDisparo();
                            double vA = Humano.ValorAtaque(Humano.PoderDisparo(p12.getDestreza(), p12.getFuerza(), p12.getNivel()), eD);
                            double efeD = Humano.EfectividadDisparo();
                            double valA = Humano.ValorAtaque(Humano.PoderDisparo(p22.getDestreza(), p22.getFuerza(), p22.getNivel()), efeD);

                            Log.loguearLineaTexto("\nRonda "+ count2++ +":");

                            p12.setSalud((int) Humano.restarSalud(p12.getSalud(),Humano.Ataque(valA,efeD)));
                            if (p12.getSalud() <= 0){
                                Log.loguearLineaTexto("\nEl humano 2 mato antes al humano 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p22.getSalud());
                                break;
                            }
                            Log.loguearLineaTexto("\nEl humano del jugador 1 realiza un ataque y queda con una salud de " + p12.getSalud());
                            p22.setSalud((int) Humano.restarSalud(p22.getSalud(),Humano.Ataque(vA,eD)));
                            Log.loguearLineaTexto("El humano del jugador 2 realiza un ataque y queda con una salud de " + p22.getSalud());
                            System.out.println("el primer personaje es "+p12.getRaza() + " y se llama " + p12.getNombre()+" y la efectividad de disparo es de " + eD);
                            System.out.println("el primer personaje es "+p22.getRaza() + " y se llama " + p22.getNombre()+" y la efectividad de disparo es de " + efeD);
                        }

                        if (p12.getSalud() > p22.getSalud()){
                            if (Jugador2.size() == 1){
                                Jugador2.remove(0);
                            }if(Jugador2.size() == 2){
                                Jugador2.remove(1);
                            }if(Jugador2.size() == 3){
                            Jugador2.remove(2);}
                            Log.loguearLineaTexto("\nla tercera ronda la gano el jugador numero 1 con su personaje llamado " +p12.getNombre() + " y quedando con una salud de " + p12.getSalud());
                            Log.loguearLineaTexto("la lista del jugador uno es de  "+Jugador1.size()+ " mientras que la del 2 es de " + Jugador2.size());

                        }
                        else{
                            if (Jugador1.size() == 1){
                                Jugador1.remove(0);
                            }if(Jugador1.size() == 2){
                                Jugador1.remove(1);
                            }if(Jugador1.size() == 3){
                            Jugador1.remove(2);
                            }
                            Log.loguearLineaTexto("\nla tercera ronda la gano el jugador numero 2 con su personaje llamado " +p22.getNombre() + " y quedando con una salud de " + p22.getSalud());
                            Log.loguearLineaTexto("la lista del jugador uno es de  "+Jugador1.size()+ " mientras que la del 2 es de " + Jugador2.size());
                        }

                        if (Jugador2.size() == 3){
                            Log.loguearLineaTexto("Felicidades al jugador 2, aniquilo a los 3 rivales que les toco enfrentar y gano");
                            break;
                        }if(Jugador1.size() == 3){
                        Log.loguearLineaTexto("ACABAMOS DE VIVIR ALGO HISTORICO, EL JUGADOR 1 ASESINO A LOS 3 RIVALES QUE LES TOCO Y GANO");
                        break;
                    }

                        System.out.println("quedaron vivos " + Jugador2.size() + " del jugador 2");
                        System.out.println("quedaron vivos " + Jugador1.size() + " del jugador 1");
                        System.out.println();
                        Log.loguearLineaTexto("\n-----------------------------------------------------------");
                        Log.loguearLineaTexto("   Damos inicio a la ultima ronda, la definitoria, 1 vs 2    ");
                        Log.loguearLineaTexto("-------------------------------------------------------------");

                        //TODO: ver lo del ataque de cada personaje, logica incorporada con: && Jugador2.get(0).getRaza(), x ej
                        if (Jugador2.size() == 1 && Objects.equals(Jugador2.get(0).getRaza(), "Orco")){
                            Log.loguearLineaTexto(Jugador2.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador1.get(0).getNombre() + " y " + Jugador1.get(1).getNombre());
                            Personaje p = Jugador2.get(0);
                            Personaje p3 = Jugador1.get(0);
                            while(p.getSalud() >= 0 && p3.getSalud() >= 0) {
                            double eD = Orco.EfectividadDisparo();
                            double vA = Orco.ValorAtaque(Orco.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD);
                            double efeD = Orco.EfectividadDisparo();
                            double valA = Orco.ValorAtaque(Orco.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                            Log.loguearLineaTexto("\nRonda "+ count3++ +":");
                            p.setSalud((int) Orco.restarSalud(p.getSalud(),Orco.Ataque(valA,efeD)));
                            if (p.getSalud() <= 0){
                                Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 1 mato al "+ p.getRaza() +" del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                Log.loguearLineaTexto(p.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                Log.loguearLineaTexto("Gano el jugador 1, sus felicitaciones al mismo");
                                break;
                            }
                            Log.loguearLineaTexto("\nEl " + p.getRaza()  + " del jugador 2 realiza un ataque y queda con una salud de " + p.getSalud());
                            p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Orco.Ataque(vA,eD)));
                            if (p3.getSalud() >=0){
                                Log.loguearLineaTexto("\nEl "+ p.getRaza() +" del jugador 2 mato al "+ p3.getRaza()+ " del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p.getSalud());
                                Jugador2.remove(0);
                                Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                Log.loguearLineaTexto("-------------------------------------------------------------");
                                Log.loguearLineaTexto("\n Ultima Ronda :");
                                Log.loguearLineaTexto(p.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                Log.loguearLineaTexto(p.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());
                                p.setSalud((int) Orco.restarSalud(p.getSalud(),Orco.Ataque(valA,efeD)));
                                Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD + " mientras que la del jugador 2 fue de " + efeD);
                                Log.loguearLineaTexto("\nEl " + p.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + p.getSalud());
                                Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD + " mientras que la del jugador 2 fue de " + efeD);
                                p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Orco.Ataque(vA,eD)));
                                Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                                if(p.getSalud() > p3.getSalud()){
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD + " mientras que la del jugador 2 fue de " + efeD);
                                    Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 2, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ p.getNombre()+" con "+ p.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                    break;
                                }
                                else{
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD + " mientras que la del jugador 2 fue de " + efeD);
                                    Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 1, era lo predecible..");
                                    break;
                                }
                            }
                            Log.loguearLineaTexto("El "+ p3.getRaza()+ " del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                            System.out.println("el primer personaje es "+p.getRaza() + " y se llama " + p.getNombre()+" y la efectividad de disparo es de " + eD);
                            System.out.println("el primer personaje es "+p3.getRaza() + " y se llama " + p3.getNombre()+" y la efectividad de disparo es de " + efeD);
                        }}
                        if(Jugador2.size() == 2){
                            //TODO: while de pelea
                            Log.loguearLineaTexto(Jugador2.get(0).getNombre() + " y " + Jugador2.get(1).getNombre() + " va a pelear contra solamente 1, especificamente " + Jugador1.get(0).getNombre());
                            Personaje p = Jugador2.get(0);
                            Personaje p3 = Jugador1.get(0);
                            while(p.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Orco.EfectividadDisparo();
                                double vA = Orco.ValorAtaque(Orco.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD);
                                double efeD = Orco.EfectividadDisparo();
                                double valA = Orco.ValorAtaque(Orco.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda " + count4++ + ":");
                                p.setSalud((int) Orco.restarSalud(p.getSalud(), Orco.Ataque(valA, efeD)));
                                if (p.getSalud() <= 0) {
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza() + " del jugador 1 mato antes al " + p.getRaza() + " del jugador 2 con su ataque, tiene "+ p3.getSalud() +" puntos de vida");
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    Log.loguearLineaTexto(p.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(p.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efeD + " mientras que la del jugador 2 fue de " + eD);
                                    p.setSalud((int) Orco.restarSalud(p.getSalud(), Orco.Ataque(valA, efeD)));
                                    Log.loguearLineaTexto("\nEl " + p.getRaza() + " del jugador 2 realiza un ataque y queda con una salud de " + p.getSalud());
                                    p3.setSalud((int) Orco.restarSalud(p3.getSalud(), Orco.Ataque(vA, eD)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza() + " del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if (p3.getSalud() > p.getSalud()) {
                                        Log.loguearLineaTexto("INCREIBLEMENTE GANO EL JUGADOR 1, CONTRA 2 JUGADORES, FUE INCREIBLE " + p3.getNombre() + " gano con " + p3.getSalud() + " de salud, mientras que " + p.getNombre() + " se quedo con " + p.getSalud() + " de vida.");
                                    } else {
                                        Log.loguearLineaTexto("gano el jugador 1, era lo predecible..");
                                    }
                                    break;
                                }

                            Log.loguearLineaTexto("\nEl "+ p3.getRaza() +" del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                            p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Orco.Ataque(vA,eD)));
                            if (p3.getSalud() <= 0){
                                Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efeD + " mientras que la del jugador 2 fue de " + eD);
                                Log.loguearLineaTexto("\nEl "+ p.getRaza() +" del jugador 2 mato antes al "+ p3.getRaza()+ " del jugador 1 con su ataque, haciendo que este no pueda reaccionar");
                                Log.loguearLineaTexto("Gano el jugador 2, sus felicitaciones al mismo");
                                break;
                            }
                            Log.loguearLineaTexto("El" + p.getRaza() +" del jugador 2 realiza un ataque y queda con una salud de " + p.getSalud());
                        }
                    }
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        break;
                    case 3:
                        File rutaActual = new File("E:\\\\Juego\\\\src\\\\ar\\\\utn\\\\frbb\\\\tup\\\\juego\\\\rol\\\\partidas\\\\"+ fecha);
                        File[] archivos = rutaActual.listFiles();
                        if (archivos != null) {
                            for (int i = 0; i < archivos.length; i++) {
                                System.out.println(archivos[i].getName());
                            }
                        }
                        System.out.println(("¿cual desea ver?, ingresar el horario con el siguiente formato: HH-MM"));
                        String date = sc.next();
                        String filepath = rutaActual+"\\"+date+".log";
                        Log.leerPartidas(filepath);
                        break;
                    case 4:
                        System.out.println(Log.borrarArchivo());
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 0 y 4");
                }
            } catch (Exception e) {
                System.out.println("Debes insertar un número");
                e.printStackTrace();
                sc.next();
        }
        }
    }
}
