package ar.utn.frbb.tup.juego.rol;

public class Humano extends Personaje {
    public Humano(String raza, String nombre, String apodo, String fechaNac, int edad, int salud, int velocidad, int destreza, int fuerza, int nivel, int armadura) {
        super(raza, nombre, apodo, fechaNac, edad, salud, velocidad, destreza, fuerza, nivel, armadura);
    }

    public static double Ataque(double ValorAtaque, double EfectividadDisparo){
        double v = (((ValorAtaque * EfectividadDisparo) / 500) * 100) * 1.07;
        return v;
    };

    public static double restarSalud(int salud, double Ataque){
        return salud - Ataque;
    };
};


