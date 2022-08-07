package ar.utn.frbb.tup.juego.rol;

import java.awt.*;
import java.io.File;
import java.sql.SQLOutput;
import java.util.*;
import java.util.List;

import static ar.utn.frbb.tup.juego.rol.CreacionPersonajes.*;
import static ar.utn.frbb.tup.juego.rol.CreacionPersonajes.Jugador2;
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
                            double efeD = Elfo.EfectividadDisparo();
                            double valA = Elfo.ValorAtaque(Elfo.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                            Log.loguearLineaTexto("\nRonda "+ count3++ +":");
                            p.setSalud((int) Orco.restarSalud(p.getSalud(),Elfo.Ataque(valA,efeD)));
                            if (p.getSalud() <= 0){
                                Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 1 mato al "+ p.getRaza() +" del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                Log.loguearLineaTexto(p.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                Log.loguearLineaTexto("Gano el jugador 1, sus felicitaciones al mismo");
                                break;
                            }
                            Log.loguearLineaTexto("\nEl " + p.getRaza()  + " del jugador 2 realiza un ataque y queda con una salud de " + p.getSalud());
                            p3.setSalud((int) Elfo.restarSalud(p3.getSalud(),Orco.Ataque(vA,eD)));
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
                                double eD2 = Orco.EfectividadDisparo();
                                double vA2 = Orco.ValorAtaque(Orco.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD2);
                                double efecHumano = Humano.EfectividadDisparo();
                                double valHumano = Humano.ValorAtaque(Humano.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecHumano);
                                Log.loguearLineaTexto(p.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                Log.loguearLineaTexto(p.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());
                                p.setSalud((int) Orco.restarSalud(p.getSalud(),Humano.Ataque(valHumano,efecHumano)));
                                Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efeD + " mientras que la del jugador 2 fue de " + eD2);
                                Log.loguearLineaTexto("\nEl " + p.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + p.getSalud());
                                Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efeD + " mientras que la del jugador 2 fue de " + eD2);
                                p3.setSalud((int) Humano.restarSalud(p3.getSalud(),Orco.Ataque(vA2,eD2)));
                                Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                                if(p.getSalud() > p3.getSalud()){
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecHumano + " mientras que la del jugador 2 fue de " + eD2);
                                    Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 2, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ p.getNombre()+" con "+ p.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                    break;
                                }
                                else{
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecHumano + " mientras que la del jugador 2 fue de " + eD2);
                                    Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 1, era lo predecible..");
                                    break;
                                }
                            }
                            Log.loguearLineaTexto("El "+ p3.getRaza()+ " del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                            System.out.println("el primer personaje es "+p.getRaza() + " y se llama " + p.getNombre()+" y la efectividad de disparo es de " + eD);
                            System.out.println("el primer personaje es "+p3.getRaza() + " y se llama " + p3.getNombre()+" y la efectividad de disparo es de " + efeD);
                        }}
                        if (Jugador2.size() == 1 && Objects.equals(Jugador2.get(0).getRaza(), "Elfo")){
                            Log.loguearLineaTexto(Jugador2.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador1.get(0).getNombre() + " y " + Jugador1.get(1).getNombre());
                            Personaje p = Jugador2.get(0);
                            Personaje p3 = Jugador1.get(0);
                            while(p.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Elfo.EfectividadDisparo();
                                double vA = Elfo.ValorAtaque(Elfo.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD);
                                double efeD = Orco.EfectividadDisparo();
                                double valA = Orco.ValorAtaque(Orco.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ count3++ +":");
                                p.setSalud((int) Elfo.restarSalud(p.getSalud(),Orco.Ataque(valA,efeD)));
                                if (p.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 1 mato al "+ p.getRaza() +" del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(p.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 1, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + p.getRaza()  + " del jugador 2 realiza un ataque y queda con una salud de " + p.getSalud());
                                p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Elfo.Ataque(vA,eD)));
                                if (p3.getSalud() >=0){
                                    Log.loguearLineaTexto("\nEl "+ p.getRaza() +" del jugador 2 mato al "+ p3.getRaza()+ " del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p.getSalud());
                                    Jugador2.remove(0);
                                    double efecElfo = Orco.EfectividadDisparo();
                                    double valElfo = Orco.ValorAtaque(Orco.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), efecElfo);
                                    double efecHumano = Humano.EfectividadDisparo();
                                    double valHumano = Humano.ValorAtaque(Humano.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecHumano);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    Log.loguearLineaTexto(p.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(p.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());

                                    p.setSalud((int) Elfo.restarSalud(p.getSalud(),Humano.Ataque(valElfo,efecElfo)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecHumano + " mientras que la del jugador 2 fue de " + efecElfo);
                                    Log.loguearLineaTexto("\nEl " + p.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + p.getSalud());
                                    p3.setSalud((int) Humano.restarSalud(p3.getSalud(),Elfo.Ataque(valHumano,efecHumano)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(p.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 2, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ p.getNombre()+" con "+ p.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 1, era lo predecible..");
                                        break;
                                    }
                                }
                            }}
                        if (Jugador2.size() == 1 && Objects.equals(Jugador2.get(0).getRaza(), "Humano")){
                            Log.loguearLineaTexto(Jugador2.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador1.get(0).getNombre() + " y " + Jugador1.get(1).getNombre());
                            Personaje p = Jugador2.get(0);
                            Personaje p3 = Jugador1.get(0);
                            while(p.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Humano.EfectividadDisparo();
                                double vA = Humano.ValorAtaque(Humano.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD);
                                double efeD = Orco.EfectividadDisparo();
                                double valA = Orco.ValorAtaque(Orco.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ count3++ +":");
                                p.setSalud((int) Humano.restarSalud(p.getSalud(),Orco.Ataque(valA,efeD)));
                                if (p.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 1 mato al "+ p.getRaza() +" del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(p.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 1, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + p.getRaza()  + " del jugador 2 realiza un ataque y queda con una salud de " + p.getSalud());
                                p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Humano.Ataque(vA,eD)));
                                if (p3.getSalud() >= 0){
                                    Log.loguearLineaTexto("\nEl "+ p.getRaza() +" del jugador 2 mato al "+ p3.getRaza()+ " del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p.getSalud());
                                    Jugador2.remove(0);
                                    double efecHumano = Humano.EfectividadDisparo();
                                    double valHumano = Humano.ValorAtaque(Humano.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), efecHumano);
                                    double efecElfo = Elfo.EfectividadDisparo();
                                    double valElfo = Elfo.ValorAtaque(Elfo.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecElfo);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    Log.loguearLineaTexto(p.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(p.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());

                                    p.setSalud((int) Humano.restarSalud(p.getSalud(),Elfo.Ataque(valElfo,efecElfo)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecElfo + " mientras que la del jugador 2 fue de " + efecHumano);
                                    Log.loguearLineaTexto("\nEl " + p.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + p.getSalud());
                                    p3.setSalud((int) Elfo.restarSalud(p3.getSalud(),Humano.Ataque(valHumano,efecHumano)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(p.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 2, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ p.getNombre()+" con "+ p.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 1, era lo predecible..");
                                        break;
                                    }
                                }
                            }}
                        if (Jugador1.size() == 1 && Objects.equals(Jugador1.get(0).getRaza(), "Orco")){
                            Log.loguearLineaTexto(Jugador1.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador2.get(0).getNombre() + " y " + Jugador2.get(1).getNombre());
                            Personaje p = Jugador1.get(0);
                            Personaje p3 = Jugador2.get(0);
                            while(p.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Orco.EfectividadDisparo();
                                double vA = Orco.ValorAtaque(Orco.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD);
                                double efeD = Elfo.EfectividadDisparo();
                                double valA = Elfo.ValorAtaque(Elfo.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ count3++ +":");
                                p.setSalud((int) Orco.restarSalud(p.getSalud(),Elfo.Ataque(valA,efeD)));
                                if (p.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 2 mato al "+ p.getRaza() +" del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(p.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 2, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + p.getRaza()  + " del jugador 1 realiza un ataque y queda con una salud de " + p.getSalud());
                                p3.setSalud((int) Elfo.restarSalud(p3.getSalud(),Orco.Ataque(vA,eD)));
                                if (p3.getSalud() >=0){
                                    Log.loguearLineaTexto("\nEl "+ p.getRaza() +" del jugador 1 mato al "+ p3.getRaza()+ " del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p.getSalud());
                                    Jugador2.remove(0);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    double eD2 = Orco.EfectividadDisparo();
                                    double vA2 = Orco.ValorAtaque(Orco.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD);
                                    double efecHumano = Humano.EfectividadDisparo();
                                    double valHumano = Humano.ValorAtaque(Humano.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecHumano);
                                    Log.loguearLineaTexto(p.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(p.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());
                                    p.setSalud((int) Orco.restarSalud(p.getSalud(),Humano.Ataque(valHumano,efecHumano)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador 2 fue de " + efecHumano);
                                    Log.loguearLineaTexto("\nEl " + p.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + p.getSalud());
                                    p3.setSalud((int) Humano.restarSalud(p3.getSalud(),Orco.Ataque(vA2,eD2)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(p.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador  fue de " + efecHumano);
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 1, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ p.getNombre()+" con "+ p.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecHumano + " mientras que la del jugador 2 fue de " + eD2);
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 2, era lo predecible..");
                                        break;
                                    }
                                }
                            }}
                        if (Jugador1.size() == 1 && Objects.equals(Jugador1.get(0).getRaza(), "Elfo")){
                            Log.loguearLineaTexto(Jugador1.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador2.get(0).getNombre() + " y " + Jugador2.get(1).getNombre());
                            Personaje p = Jugador1.get(0);
                            Personaje p3 = Jugador2.get(0);
                            while(p.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Elfo.EfectividadDisparo();
                                double vA = Elfo.ValorAtaque(Elfo.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD);
                                double efeD = Orco.EfectividadDisparo();
                                double valA = Orco.ValorAtaque(Orco.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ count3++ +":");
                                p.setSalud((int) Elfo.restarSalud(p.getSalud(),Orco.Ataque(valA,efeD)));
                                if (p.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 2 mato al "+ p.getRaza() +" del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(p.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 2, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + p.getRaza()  + " del jugador 1 realiza un ataque y queda con una salud de " + p.getSalud());
                                p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Elfo.Ataque(vA,eD)));
                                if (p3.getSalud() >=0){
                                    Log.loguearLineaTexto("\nEl "+ p.getRaza() +" del jugador 1 mato al "+ p3.getRaza()+ " del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p.getSalud());
                                    Jugador2.remove(0);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    double eD2 = Elfo.EfectividadDisparo();
                                    double vA2 = Elfo.ValorAtaque(Elfo.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD);
                                    double efecHumano = Humano.EfectividadDisparo();
                                    double valHumano = Humano.ValorAtaque(Humano.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecHumano);
                                    Log.loguearLineaTexto(p.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(p.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());
                                    p.setSalud((int) Orco.restarSalud(p.getSalud(),Humano.Ataque(valHumano,efecHumano)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador 2 fue de " + efecHumano);
                                    Log.loguearLineaTexto("\nEl " + p.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + p.getSalud());
                                    p3.setSalud((int) Humano.restarSalud(p3.getSalud(),Orco.Ataque(vA2,eD2)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(p.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador  fue de " + efecHumano);
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 1, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ p.getNombre()+" con "+ p.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecHumano + " mientras que la del jugador 2 fue de " + eD2);
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 2, era lo predecible..");
                                        break;
                                    }
                                }
                            }}
                        if (Jugador1.size() == 1 && Objects.equals(Jugador1.get(0).getRaza(), "Humano")){
                            Log.loguearLineaTexto(Jugador1.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador2.get(0).getNombre() + " y " + Jugador2.get(1).getNombre());
                            Personaje p = Jugador1.get(0);
                            Personaje p3 = Jugador2.get(0);
                            while(p.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Humano.EfectividadDisparo();
                                double vA = Humano.ValorAtaque(Humano.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD);
                                double efeD = Orco.EfectividadDisparo();
                                double valA = Orco.ValorAtaque(Orco.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ count3++ +":");
                                p.setSalud((int) Humano.restarSalud(p.getSalud(),Orco.Ataque(valA,efeD)));
                                if (p.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 2 mato al "+ p.getRaza() +" del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(p.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 2, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + p.getRaza()  + " del jugador 1 realiza un ataque y queda con una salud de " + p.getSalud());
                                p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Humano.Ataque(vA,eD)));
                                if (p3.getSalud() >=0){
                                    Log.loguearLineaTexto("\nEl "+ p.getRaza() +" del jugador 1 mato al "+ p3.getRaza()+ " del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p.getSalud());
                                    Jugador2.remove(0);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    double eD2 = Humano.EfectividadDisparo();
                                    double vA2 = Humano.ValorAtaque(Humano.PoderDisparo(p.getDestreza(), p.getFuerza(), p.getNivel()), eD);
                                    double efecElfo = Elfo.EfectividadDisparo();
                                    double valElfo = Elfo.ValorAtaque(Elfo.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecElfo);
                                    Log.loguearLineaTexto(p.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(p.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());
                                    p.setSalud((int) Humano.restarSalud(p.getSalud(),Elfo.Ataque(valElfo,efecElfo)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador 2 fue de " + efecElfo);
                                    Log.loguearLineaTexto("\nEl " + p.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + p.getSalud());
                                    p3.setSalud((int) Elfo.restarSalud(p3.getSalud(),Humano.Ataque(vA2,eD2)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(p.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador  fue de " + efecElfo);
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 1, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ p.getNombre()+" con "+ p.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecElfo + " mientras que la del jugador 2 fue de " + eD2);
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 2, era lo predecible..");
                                        break;
                                    }
                                }
                            }}
                        break;
                    case 2:
                        Scanner sca = new Scanner(System.in);
                        Log.crearPartida();
                        List<Personaje> Jugador1 = new ArrayList<Personaje>();
                        List<Personaje> Jugador2 = new ArrayList<Personaje>();
                        Personaje p = new Personaje();
                        Personaje personaje2 = new Personaje();
                        Personaje personaje3 = new Personaje();
                        Personaje personaje4 = new Personaje();
                        Personaje personaje5 = new Personaje();
                        Personaje personaje6 = new Personaje();
                        int cont = 1;
                        int cont1 = 1;
                        int cont2 = 1;
                        int cont3 = 1;

                        p.setRaza("Orco");
                        p.setSalud(100);
                        System.out.println("Ingrese el nombre del primer personaje que desea crear para el jugador 1");
                        p.setNombre(sca.next());
                        System.out.println("Ingrese la cantidad de destreza que desea tener");
                        p.setDestreza(sca.nextInt());
                        System.out.println("Ingrese la cantidad de fuerza que desea tener");
                        p.setFuerza(sca.nextInt());
                        System.out.println("Ingrese el nivel que desea tener");
                        p.setNivel(sca.nextInt());
                        System.out.println("Ingrese la cantidad de velocidad que desea tener");
                        p.setVelocidad(sca.nextInt());
                        System.out.println("Ingrese la cantidad de armadura que desea tener");
                        p.setArmadura(sca.nextInt());
                        Jugador1.add(0,p);

                        personaje2.setRaza("Orco");
                        personaje2.setSalud(100);
                        System.out.println("Ingrese el nombre del primer personaje que desea crear para el jugador 2");
                        personaje2.setNombre(sca.next());
                        System.out.println("Ingrese la cantidad de destreza que desea tener");
                        personaje2.setDestreza(sca.nextInt());
                        System.out.println("Ingrese la cantidad de fuerza que desea tener");
                        personaje2.setFuerza(sca.nextInt());
                        System.out.println("Ingrese el nivel que desea tener");
                        personaje2.setNivel(sca.nextInt());
                        System.out.println("Ingrese la cantidad de velocidad que desea tener");
                        personaje2.setVelocidad(sca.nextInt());
                        System.out.println("Ingrese la cantidad de armadura que desea tener");
                        personaje2.setArmadura(sca.nextInt());
                        Jugador2.add(0,personaje2);

                        personaje3.setRaza("Elfo");
                        personaje3.setSalud(100);
                        System.out.println("Ingrese el nombre del segundo personaje que desea crear para el jugador 1");
                        personaje3.setNombre(sca.next());
                        System.out.println("Ingrese la cantidad de destreza que desea tener");
                        personaje3.setDestreza(sca.nextInt());
                        System.out.println("Ingrese la cantidad de fuerza que desea tener");
                        personaje3.setFuerza(sca.nextInt());
                        System.out.println("Ingrese el nivel que desea tener");
                        personaje3.setNivel(sca.nextInt());
                        System.out.println("Ingrese la cantidad de velocidad que desea tener");
                        personaje3.setVelocidad(sca.nextInt());
                        System.out.println("Ingrese la cantidad de armadura que desea tener");
                        personaje3.setArmadura(sca.nextInt());
                        Jugador1.add(1,personaje3);

                        personaje4.setRaza("Elfo");
                        personaje4.setSalud(100);
                        System.out.println("Ingrese el nombre del segundo personaje que desea crear para el jugador 2");
                        personaje4.setNombre(sca.next());
                        System.out.println("Ingrese la cantidad de destreza que desea tener");
                        personaje4.setDestreza(sca.nextInt());
                        System.out.println("Ingrese la cantidad de fuerza que desea tener");
                        personaje4.setFuerza(sca.nextInt());
                        System.out.println("Ingrese el nivel que desea tener");
                        personaje4.setNivel(sca.nextInt());
                        System.out.println("Ingrese la cantidad de velocidad que desea tener");
                        personaje4.setVelocidad(sca.nextInt());
                        System.out.println("Ingrese la cantidad de armadura que desea tener");
                        personaje4.setArmadura(sca.nextInt());
                        Jugador2.add(1,personaje4);

                        personaje5.setRaza("Humano");
                        personaje5.setSalud(100);
                        System.out.println("Ingrese el nombre del tercer personaje que desea crear para el jugador 1");
                        personaje5.setNombre(sca.next());
                        System.out.println("Ingrese la cantidad de destreza que desea tener");
                        personaje5.setDestreza(sca.nextInt());
                        System.out.println("Ingrese la cantidad de fuerza que desea tener");
                        personaje5.setFuerza(sca.nextInt());
                        System.out.println("Ingrese el nivel que desea tener");
                        personaje5.setNivel(sca.nextInt());
                        System.out.println("Ingrese la cantidad de velocidad que desea tener");
                        personaje5.setVelocidad(sca.nextInt());
                        System.out.println("Ingrese la cantidad de armadura que desea tener");
                        personaje5.setArmadura(sca.nextInt());
                        Jugador1.add(2,personaje5);

                        personaje6.setRaza("Humano");
                        personaje6.setSalud(100);
                        System.out.println("Ingrese el nombre del tercer personaje que desea crear para el jugador 2");
                        personaje6.setNombre(sca.next());
                        System.out.println("Ingrese la cantidad de destreza que desea tener");
                        personaje6.setDestreza(sca.nextInt());
                        System.out.println("Ingrese la cantidad de fuerza que desea tener");
                        personaje6.setFuerza(sca.nextInt());
                        System.out.println("Ingrese el nivel que desea tener");
                        personaje6.setNivel(sca.nextInt());
                        System.out.println("Ingrese la cantidad de velocidad que desea tener");
                        personaje6.setVelocidad(sca.nextInt());
                        System.out.println("Ingrese la cantidad de armadura que desea tener");
                        personaje6.setArmadura(sca.nextInt());
                        Jugador2.add(2,personaje6);
                        System.out.println(Jugador1.size() + " y el 2 " + Jugador2.size());

                        Log.loguearLineaTexto("----------------------------------------");
                        Log.loguearLineaTexto("  Damos inicio a la ronda de los orcos  ");
                        Log.loguearLineaTexto("----------------------------------------");
                        Personaje personaje11 = Jugador1.get(0);
                        Personaje personaje121 = Jugador2.get(0);
                        Personaje personaje112 = Jugador1.get(1);
                        Personaje personaje122 = Jugador2.get(1);
                        Personaje personaje113 = Jugador1.get(2);
                        Personaje personaje123 = Jugador2.get(2);

                        loguearLineaTexto("jugador 1: " + personaje11.getNombre() + ", " + personaje112.getNombre() + ", " + personaje113.getNombre());
                        loguearLineaTexto("jugador 2: " + personaje121.getNombre() + ", " + personaje121.getNombre() + ", " + personaje121.getNombre());
                        while(personaje11.getSalud() > 0 && personaje121.getSalud() > 0){
                            double eD = Orco.EfectividadDisparo();
                            double vA = Orco.ValorAtaque(Orco.PoderDisparo(personaje11.getDestreza(), personaje11.getFuerza(), personaje11.getNivel()), eD);
                            double efeD = Orco.EfectividadDisparo();
                            double valA = Orco.ValorAtaque(Orco.PoderDisparo(personaje121.getDestreza(), personaje121.getFuerza(), personaje121.getNivel()), efeD);
                            Log.loguearLineaTexto("\nRonda "+ cont++ +":");
                            //System.out.println("\nel ataque del primer orco es de "+Orco.Ataque(vA,eD));
                            //System.out.println("el ataque del segundo orco es de "+Orco.Ataque(valA,efeD));
                            personaje11.setSalud((int) Orco.restarSalud(personaje11.getSalud(),Orco.Ataque(valA,efeD)));
                            if (personaje11.getSalud() <= 0){
                                Log.loguearLineaTexto("\nEl orco del jugador 2 mato antes al orco del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + personaje121.getSalud());
                                break;
                            }
                            Log.loguearLineaTexto("\nEl orco del jugador 1 realiza un ataque y queda con una salud de " + personaje11.getSalud());
                            personaje121.setSalud((int) Orco.restarSalud(personaje121.getSalud(),Orco.Ataque(vA,eD)));
                            Log.loguearLineaTexto("El orco del jugador 2 realiza un ataque y queda con una salud de " + personaje121.getSalud());
                            System.out.println("el primer personaje es "+personaje11.getRaza() + " y se llama " + personaje11.getNombre()+" y la efectividad de disparo es de " + eD);
                            System.out.println("el primer personaje es "+personaje121.getRaza() + " y se llama " + personaje121.getNombre()+" y la efectividad de disparo es de " + efeD);
                        }

                        if (personaje11.getSalud() > personaje121.getSalud()){
                            Log.loguearLineaTexto("\nla primera ronda la gano el jugador numero 1 con su personaje llamado " +personaje11.getNombre() + " y quedando con una salud de " + personaje11.getSalud());
                            Jugador2.remove(0);
                            Log.loguearLineaTexto("la lista del jugador uno es de  "+Jugador1.size()+ " mientras que la del 2 es de " + Jugador2.size());

                        }
                        else{
                            Log.loguearLineaTexto("\nla primera ronda la gano el jugador numero 2 con su personaje llamado " +personaje121.getNombre() + " y quedando con una salud de " + personaje121.getSalud());
                            Jugador1.remove(0);
                            Log.loguearLineaTexto("la lista del jugador uno es de  "+Jugador1.size()+ " mientras que la del 2 es de " + Jugador2.size());

                        }

                        Log.loguearLineaTexto("\n----------------------------------------");
                        Log.loguearLineaTexto("  Damos inicio a la ronda de los elfos  ");
                        Log.loguearLineaTexto("----------------------------------------");
                        while(personaje112.getSalud() >= 0 && personaje122.getSalud() >= 0){
                            double eD = Elfo.EfectividadDisparo();
                            double vA = Elfo.ValorAtaque(Elfo.PoderDisparo(personaje112.getDestreza(), personaje112.getFuerza(), personaje112.getNivel()), eD);
                            double efeD = Elfo.EfectividadDisparo();
                            double valA = Elfo.ValorAtaque(Elfo.PoderDisparo(personaje122.getDestreza(), personaje122.getFuerza(), personaje122.getNivel()), efeD);

                            Log.loguearLineaTexto("\nRonda "+ cont1++ +":");
                            //System.out.println("el ataque del primer elfo es de "+Orco.Ataque(vA,eD));
                            //System.out.println("el ataque del segundo elfo es de "+Orco.Ataque(valA,efeD));
                            personaje112.setSalud((int) Elfo.restarSalud(personaje112.getSalud(),Elfo.Ataque(valA,efeD)));
                            if (personaje112.getSalud() <= 0){
                                Log.loguearLineaTexto("\nEl elfo del jugador 2 mato antes al elfo del jugador 1 con su ataque, haciendo que este no pueda reaccionar , quedando con una salud de "  + personaje122.getSalud());
                                break;
                            }
                            Log.loguearLineaTexto("\nEl elfo del jugador 1 realiza un ataque y queda con una salud de " + personaje112.getSalud());
                            personaje122.setSalud((int) Elfo.restarSalud(personaje122.getSalud(),Elfo.Ataque(vA,eD)));
                            Log.loguearLineaTexto("El elfo del jugador 2 realiza un ataque y queda con una salud de " + personaje122.getSalud());
                            System.out.println("el primer personaje es "+personaje112.getRaza() + " y se llama " + personaje112.getNombre()+" y la efectividad de disparo es de " + eD);
                            System.out.println("el primer personaje es "+personaje122.getRaza() + " y se llama " + personaje122.getNombre()+" y la efectividad de disparo es de " + efeD);
                        }

                        if (personaje112.getSalud() > personaje122.getSalud()){
                            Log.loguearLineaTexto("\nla segunda ronda la gano el jugador numero 1 con su personaje llamado " +personaje112.getNombre() + " y quedando con una salud de " + personaje112.getSalud());
                            if (Jugador2.size() == 2){
                                Jugador2.remove(0);
                            }else {
                                Jugador2.remove(1);
                            }
                            Log.loguearLineaTexto("la lista del jugador uno es de  "+Jugador1.size()+ " mientras que la del 2 es de " + Jugador2.size());

                        }
                        else{
                            Log.loguearLineaTexto("\nla segunda ronda la gano el jugador numero 2 con su personaje llamado " +personaje122.getNombre() + " y quedando con una salud de " + personaje122.getSalud());
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
                        while(personaje113.getSalud() >= 0 && personaje123.getSalud() >= 0){
                            double eD = Humano.EfectividadDisparo();
                            double vA = Humano.ValorAtaque(Humano.PoderDisparo(personaje113.getDestreza(), personaje113.getFuerza(), personaje113.getNivel()), eD);
                            double efeD = Humano.EfectividadDisparo();
                            double valA = Humano.ValorAtaque(Humano.PoderDisparo(personaje123.getDestreza(), personaje123.getFuerza(), personaje123.getNivel()), efeD);

                            Log.loguearLineaTexto("\nRonda "+ cont2++ +":");

                            personaje113.setSalud((int) Humano.restarSalud(personaje113.getSalud(),Humano.Ataque(valA,efeD)));
                            if (personaje113.getSalud() <= 0){
                                Log.loguearLineaTexto("\nEl humano 2 mato antes al humano 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + personaje123.getSalud());
                                break;
                            }
                            Log.loguearLineaTexto("\nEl humano del jugador 1 realiza un ataque y queda con una salud de " + personaje113.getSalud());
                            personaje123.setSalud((int) Humano.restarSalud(personaje123.getSalud(),Humano.Ataque(vA,eD)));
                            Log.loguearLineaTexto("El humano del jugador 2 realiza un ataque y queda con una salud de " + personaje123.getSalud());
                            System.out.println("el primer personaje es "+personaje113.getRaza() + " y se llama " + personaje113.getNombre()+" y la efectividad de disparo es de " + eD);
                            System.out.println("el primer personaje es "+personaje123.getRaza() + " y se llama " + personaje123.getNombre()+" y la efectividad de disparo es de " + efeD);
                        }

                        if (personaje113.getSalud() > personaje123.getSalud()){
                            if (Jugador2.size() == 1){
                                Jugador2.remove(0);
                            }if(Jugador2.size() == 2){
                                Jugador2.remove(1);
                            }if(Jugador2.size() == 3){
                                Jugador2.remove(2);}
                            Log.loguearLineaTexto("\nla tercera ronda la gano el jugador numero 1 con su personaje llamado " +personaje113.getNombre() + " y quedando con una salud de " + personaje113.getSalud());
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
                            Log.loguearLineaTexto("\nla tercera ronda la gano el jugador numero 2 con su personaje llamado " +personaje123.getNombre() + " y quedando con una salud de " + personaje123.getSalud());
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
                            Personaje personaje = Jugador2.get(0);
                            Personaje p3 = Jugador1.get(0);
                            while(personaje.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Orco.EfectividadDisparo();
                                double vA = Orco.ValorAtaque(Orco.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), eD);
                                double efeD = Elfo.EfectividadDisparo();
                                double valA = Elfo.ValorAtaque(Elfo.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ cont3++ +":");
                                personaje.setSalud((int) Orco.restarSalud(personaje.getSalud(),Elfo.Ataque(valA,efeD)));
                                if (personaje.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 1 mato al "+ personaje.getRaza() +" del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(personaje.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 1, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + personaje.getRaza()  + " del jugador 2 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                p3.setSalud((int) Elfo.restarSalud(p3.getSalud(),Orco.Ataque(vA,eD)));
                                if (p3.getSalud() >=0){
                                    Log.loguearLineaTexto("\nEl "+ personaje.getRaza() +" del jugador 2 mato al "+ p3.getRaza()+ " del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p.getSalud());
                                    Jugador2.remove(0);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    double eD2 = Orco.EfectividadDisparo();
                                    double vA2 = Orco.ValorAtaque(Orco.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), eD2);
                                    double efecHumano = Humano.EfectividadDisparo();
                                    double valHumano = Humano.ValorAtaque(Humano.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecHumano);
                                    Log.loguearLineaTexto(personaje.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(personaje.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());
                                    personaje.setSalud((int) Orco.restarSalud(personaje.getSalud(),Humano.Ataque(valHumano,efecHumano)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efeD + " mientras que la del jugador 2 fue de " + eD2);
                                    Log.loguearLineaTexto("\nEl " + personaje.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efeD + " mientras que la del jugador 2 fue de " + eD2);
                                    p3.setSalud((int) Humano.restarSalud(p3.getSalud(),Orco.Ataque(vA2,eD2)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(personaje.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecHumano + " mientras que la del jugador 2 fue de " + eD2);
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 2, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ personaje.getNombre()+" con "+ personaje.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecHumano + " mientras que la del jugador 2 fue de " + eD2);
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 1, era lo predecible..");
                                        break;
                                    }
                                }
                                Log.loguearLineaTexto("El "+ p3.getRaza()+ " del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                                System.out.println("el primer personaje es "+personaje.getRaza() + " y se llama " + personaje.getNombre()+" y la efectividad de disparo es de " + eD);
                                System.out.println("el primer personaje es "+p3.getRaza() + " y se llama " + p3.getNombre()+" y la efectividad de disparo es de " + efeD);
                            }}
                        if (Jugador2.size() == 1 && Objects.equals(Jugador2.get(0).getRaza(), "Elfo")){
                            Log.loguearLineaTexto(Jugador2.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador1.get(0).getNombre() + " y " + Jugador1.get(1).getNombre());
                            Personaje personaje = Jugador2.get(0);
                            Personaje p3 = Jugador1.get(0);
                            while(personaje.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Elfo.EfectividadDisparo();
                                double vA = Elfo.ValorAtaque(Elfo.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), eD);
                                double efeD = Orco.EfectividadDisparo();
                                double valA = Orco.ValorAtaque(Orco.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ cont3++ +":");
                                personaje.setSalud((int) Elfo.restarSalud(personaje.getSalud(),Orco.Ataque(valA,efeD)));
                                if (personaje.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 1 mato al "+ personaje.getRaza() +" del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(personaje.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 1, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + personaje.getRaza()  + " del jugador 2 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Elfo.Ataque(vA,eD)));
                                if (p3.getSalud() >=0){
                                    Log.loguearLineaTexto("\nEl "+ personaje.getRaza() +" del jugador 2 mato al "+ p3.getRaza()+ " del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + personaje.getSalud());
                                    Jugador2.remove(0);
                                    double efecElfo = Orco.EfectividadDisparo();
                                    double valElfo = Orco.ValorAtaque(Orco.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), efecElfo);
                                    double efecHumano = Humano.EfectividadDisparo();
                                    double valHumano = Humano.ValorAtaque(Humano.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecHumano);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    Log.loguearLineaTexto(personaje.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(personaje.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());

                                    personaje.setSalud((int) Elfo.restarSalud(personaje.getSalud(),Humano.Ataque(valElfo,efecElfo)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecHumano + " mientras que la del jugador 2 fue de " + efecElfo);
                                    Log.loguearLineaTexto("\nEl " + personaje.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                    p3.setSalud((int) Humano.restarSalud(p3.getSalud(),Elfo.Ataque(valHumano,efecHumano)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(personaje.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 2, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ personaje.getNombre()+" con "+ personaje.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 1, era lo predecible..");
                                        break;
                                    }
                                }
                            }}
                        if (Jugador2.size() == 1 && Objects.equals(Jugador2.get(0).getRaza(), "Humano")){
                            Log.loguearLineaTexto(Jugador2.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador1.get(0).getNombre() + " y " + Jugador1.get(1).getNombre());
                            Personaje personaje = Jugador2.get(0);
                            Personaje p3 = Jugador1.get(0);
                            while(personaje.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Humano.EfectividadDisparo();
                                double vA = Humano.ValorAtaque(Humano.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), eD);
                                double efeD = Orco.EfectividadDisparo();
                                double valA = Orco.ValorAtaque(Orco.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ cont3++ +":");
                                personaje.setSalud((int) Humano.restarSalud(personaje.getSalud(),Orco.Ataque(valA,efeD)));
                                if (personaje.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 1 mato al "+ personaje.getRaza() +" del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(personaje.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 1, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + personaje.getRaza()  + " del jugador 2 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Humano.Ataque(vA,eD)));
                                if (p3.getSalud() >= 0){
                                    Log.loguearLineaTexto("\nEl "+ personaje.getRaza() +" del jugador 2 mato al "+ p3.getRaza()+ " del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + personaje.getSalud());
                                    Jugador2.remove(0);
                                    double efecHumano = Humano.EfectividadDisparo();
                                    double valHumano = Humano.ValorAtaque(Humano.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), efecHumano);
                                    double efecElfo = Elfo.EfectividadDisparo();
                                    double valElfo = Elfo.ValorAtaque(Elfo.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecElfo);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    Log.loguearLineaTexto(personaje.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(personaje.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());

                                    personaje.setSalud((int) Humano.restarSalud(personaje.getSalud(),Elfo.Ataque(valElfo,efecElfo)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecElfo + " mientras que la del jugador 2 fue de " + efecHumano);
                                    Log.loguearLineaTexto("\nEl " + personaje.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                    p3.setSalud((int) Elfo.restarSalud(p3.getSalud(),Humano.Ataque(valHumano,efecHumano)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(personaje.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 2, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ personaje.getNombre()+" con "+ personaje.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 1, era lo predecible..");
                                        break;
                                    }
                                }
                            }}
                        if (Jugador1.size() == 1 && Objects.equals(Jugador1.get(0).getRaza(), "Orco")){
                            Log.loguearLineaTexto(Jugador1.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador2.get(0).getNombre() + " y " + Jugador2.get(1).getNombre());
                            Personaje personaje = Jugador1.get(0);
                            Personaje p3 = Jugador2.get(0);
                            while(personaje.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Orco.EfectividadDisparo();
                                double vA = Orco.ValorAtaque(Orco.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), eD);
                                double efeD = Elfo.EfectividadDisparo();
                                double valA = Elfo.ValorAtaque(Elfo.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ cont3++ +":");
                                personaje.setSalud((int) Orco.restarSalud(personaje.getSalud(),Elfo.Ataque(valA,efeD)));
                                if (personaje.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 2 mato al "+ personaje.getRaza() +" del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(personaje.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 2, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + personaje.getRaza()  + " del jugador 1 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                p3.setSalud((int) Elfo.restarSalud(p3.getSalud(),Orco.Ataque(vA,eD)));
                                if (p3.getSalud() >=0){
                                    Log.loguearLineaTexto("\nEl "+ personaje.getRaza() +" del jugador 1 mato al "+ p3.getRaza()+ " del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + personaje.getSalud());
                                    Jugador2.remove(0);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    double eD2 = Orco.EfectividadDisparo();
                                    double vA2 = Orco.ValorAtaque(Orco.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), eD);
                                    double efecHumano = Humano.EfectividadDisparo();
                                    double valHumano = Humano.ValorAtaque(Humano.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecHumano);
                                    Log.loguearLineaTexto(personaje.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(personaje.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());
                                    personaje.setSalud((int) Orco.restarSalud(personaje.getSalud(),Humano.Ataque(valHumano,efecHumano)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador 2 fue de " + efecHumano);
                                    Log.loguearLineaTexto("\nEl " + personaje.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                    p3.setSalud((int) Humano.restarSalud(p3.getSalud(),Orco.Ataque(vA2,eD2)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(personaje.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador  fue de " + efecHumano);
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 1, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ personaje.getNombre()+" con "+ personaje.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecHumano + " mientras que la del jugador 2 fue de " + eD2);
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 2, era lo predecible..");
                                        break;
                                    }
                                }
                            }}
                        if (Jugador1.size() == 1 && Objects.equals(Jugador1.get(0).getRaza(), "Elfo")){
                            Log.loguearLineaTexto(Jugador1.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador2.get(0).getNombre() + " y " + Jugador2.get(1).getNombre());
                            Personaje personaje = Jugador1.get(0);
                            Personaje p3 = Jugador2.get(0);
                            while(personaje.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Elfo.EfectividadDisparo();
                                double vA = Elfo.ValorAtaque(Elfo.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), eD);
                                double efeD = Orco.EfectividadDisparo();
                                double valA = Orco.ValorAtaque(Orco.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ cont3++ +":");
                                personaje.setSalud((int) Elfo.restarSalud(personaje.getSalud(),Orco.Ataque(valA,efeD)));
                                if (personaje.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 2 mato al "+ personaje.getRaza() +" del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(personaje.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 2, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + personaje.getRaza()  + " del jugador 1 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Elfo.Ataque(vA,eD)));
                                if (p3.getSalud() >=0){
                                    Log.loguearLineaTexto("\nEl "+ personaje.getRaza() +" del jugador 1 mato al "+ p3.getRaza()+ " del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + personaje.getSalud());
                                    Jugador2.remove(0);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    double eD2 = Elfo.EfectividadDisparo();
                                    double vA2 = Elfo.ValorAtaque(Elfo.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), eD);
                                    double efecHumano = Humano.EfectividadDisparo();
                                    double valHumano = Humano.ValorAtaque(Humano.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecHumano);
                                    Log.loguearLineaTexto(personaje.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(personaje.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());
                                    personaje.setSalud((int) Orco.restarSalud(personaje.getSalud(),Humano.Ataque(valHumano,efecHumano)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador 2 fue de " + efecHumano);
                                    Log.loguearLineaTexto("\nEl " + personaje.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                    p3.setSalud((int) Humano.restarSalud(p3.getSalud(),Orco.Ataque(vA2,eD2)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(personaje.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador  fue de " + efecHumano);
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 1, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ personaje.getNombre()+" con "+ personaje.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecHumano + " mientras que la del jugador 2 fue de " + eD2);
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 2, era lo predecible..");
                                        break;
                                    }
                                }
                            }}
                        if (Jugador1.size() == 1 && Objects.equals(Jugador1.get(0).getRaza(), "Humano")){
                            Log.loguearLineaTexto(Jugador1.get(0).getNombre() + " va a pelear contra 2, contra " + Jugador2.get(0).getNombre() + " y " + Jugador2.get(1).getNombre());
                            Personaje personaje = Jugador1.get(0);
                            Personaje p3 = Jugador2.get(0);
                            while(personaje.getSalud() >= 0 && p3.getSalud() >= 0) {
                                double eD = Humano.EfectividadDisparo();
                                double vA = Humano.ValorAtaque(Humano.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), eD);
                                double efeD = Orco.EfectividadDisparo();
                                double valA = Orco.ValorAtaque(Orco.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efeD);
                                Log.loguearLineaTexto("\nRonda "+ cont3++ +":");
                                personaje.setSalud((int) Humano.restarSalud(personaje.getSalud(),Orco.Ataque(valA,efeD)));
                                if (personaje.getSalud() <= 0){
                                    Log.loguearLineaTexto("\nEl "+p3.getRaza()+" del jugador 2 mato al "+ personaje.getRaza() +" del jugador 1 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + p3.getSalud());
                                    Log.loguearLineaTexto(personaje.getNombre() + " no pudo ante el personaje restante del jugador 2 ("+ p3.getNombre()+"), haciendo quedar a este jugador como victorioso!");
                                    Log.loguearLineaTexto("Gano el jugador 2, sus felicitaciones al mismo");
                                    break;
                                }
                                Log.loguearLineaTexto("\nEl " + personaje.getRaza()  + " del jugador 1 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                p3.setSalud((int) Orco.restarSalud(p3.getSalud(),Humano.Ataque(vA,eD)));
                                if (p3.getSalud() >=0){
                                    Log.loguearLineaTexto("\nEl "+ personaje.getRaza() +" del jugador 1 mato al "+ p3.getRaza()+ " del jugador 2 con su ataque, haciendo que este no pueda reaccionar, quedando con una salud de " + personaje.getSalud());
                                    Jugador2.remove(0);
                                    Log.loguearLineaTexto("Esto produjo la ronda mas epica del juego, la ultima ronda, solo gana el que tiene mas vida");
                                    Log.loguearLineaTexto("\n-----------------------------------------------------------");
                                    Log.loguearLineaTexto("Damos inicio a la ultima ronda, la ultra definitoria, 1 vs 1 ");
                                    Log.loguearLineaTexto("En esta ronda cada jugador realiza un ataque, y al finalizar ");
                                    Log.loguearLineaTexto(" ...gana el que tiene mas vida que el otro, matar o morir... ");
                                    Log.loguearLineaTexto("-------------------------------------------------------------");
                                    Log.loguearLineaTexto("\n Ultima Ronda :");
                                    double eD2 = Humano.EfectividadDisparo();
                                    double vA2 = Humano.ValorAtaque(Humano.PoderDisparo(personaje.getDestreza(), personaje.getFuerza(), personaje.getNivel()), eD);
                                    double efecElfo = Elfo.EfectividadDisparo();
                                    double valElfo = Elfo.ValorAtaque(Elfo.PoderDisparo(p3.getDestreza(), p3.getFuerza(), p3.getNivel()), efecElfo);
                                    Log.loguearLineaTexto(personaje.getNombre().toUpperCase() + " VS " + p3.getNombre().toUpperCase());
                                    Log.loguearLineaTexto(personaje.getRaza().toUpperCase() + " VS " + p3.getRaza().toUpperCase());
                                    personaje.setSalud((int) Humano.restarSalud(personaje.getSalud(),Elfo.Ataque(valElfo,efecElfo)));
                                    Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador 2 fue de " + efecElfo);
                                    Log.loguearLineaTexto("\nEl " + personaje.getRaza()  +" del jugador 1 realiza un ataque y queda con una salud de " + personaje.getSalud());
                                    p3.setSalud((int) Elfo.restarSalud(p3.getSalud(),Humano.Ataque(vA2,eD2)));
                                    Log.loguearLineaTexto("\nEl " + p3.getRaza()  +" del jugador 2 realiza un ataque y queda con una salud de " + p3.getSalud());
                                    if(personaje.getSalud() > p3.getSalud()){
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + eD2 + " mientras que la del jugador  fue de " + efecElfo);
                                        Log.loguearLineaTexto("\nINCREIBLEMENTE GANO EL JUGADOR 1, CONTRA 2 JUGADORES, FUE INCREIBLE, gano "+ personaje.getNombre()+" con "+ personaje.getSalud() + " de salud mientras que "+ p3.getNombre() +" termino con "+ p3.getSalud() + " de salud." );
                                        break;
                                    }
                                    else{
                                        Log.loguearLineaTexto("la efectividad de disparo del jugador 1 fue de " + efecElfo + " mientras que la del jugador 2 fue de " + eD2);
                                        Log.loguearLineaTexto("\n ayy casi pero no, gano el jugador 2, era lo predecible..");
                                        break;
                                    }
                                }
                            }}
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
