package ar.utn.frbb.tup.juego.rol;

public class Personaje {
    private String raza;
    private String nombre;
    private String apodo;
    private String fechaNac;
    private int edad;
    private int salud;
    private int velocidad;
    private int destreza;
    private int fuerza;
    private int nivel;
    private int armadura;


    public Personaje() {
    }


    public Personaje(String raza, String nombre, String apodo, String fechaNac, int edad, int salud, int velocidad, int destreza, int fuerza, int nivel, int armadura) {
        this.raza = raza;
        this.nombre = nombre;
        this.apodo = apodo;
        this.fechaNac = fechaNac;
        this.edad = edad;
        this.salud = salud;
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.nivel = nivel;
        this.armadura = armadura;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public static double PoderDisparo(int destreza, int fuerza, int nivel){
      return destreza * fuerza * nivel;
    };

    public static double EfectividadDisparo(){
        double numero = (int)(Math. random()*100+1);
        return numero / 100;
    };

    public static double ValorAtaque(double PoderDisparo, double EfectividadDisparo){
        return PoderDisparo * EfectividadDisparo;
    };

    public static double PoderDefensa(Personaje JugadorDefensa){
        int PDEF = JugadorDefensa.getArmadura() * JugadorDefensa.getVelocidad();
        return PDEF;
    };


    @Override
    public String toString() {
        return "Personaje{" +
                "raza='" + raza + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apodo='" + apodo + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", edad=" + edad +
                ", salud=" + salud +
                ", velocidad=" + velocidad +
                ", destreza=" + destreza +
                ", fuerza=" + fuerza +
                ", nivel=" + nivel +
                ", armadura=" + armadura +
                '}';
    }
}



