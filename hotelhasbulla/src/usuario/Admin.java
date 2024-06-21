package usuario;

import java.util.LinkedList;

public class Admin {
    private int habitacionesDisponibles;
    private int canchasFutbol11;
    private int canchasFutbol8;
    private int canchasFutbol5;
    private int capacidadPileta;
    private int personasEnPileta;
    private LinkedList<Integer> canchasTenis;

    public Admin() {
        habitacionesDisponibles = 100;
        canchasFutbol11 = 2;
        canchasFutbol8 = 3;
        canchasFutbol5 = 4;
        capacidadPileta = 50;
        personasEnPileta = 0;
        canchasTenis = new LinkedList<>();
        canchasTenis.add(3); // cemento
        canchasTenis.add(2); // cesped
        canchasTenis.add(1); // polvo_de_ladrillo
    }

    public void checkIn(int habitacionesNecesarias) {
        if (habitacionesDisponibles >= habitacionesNecesarias) {
            habitacionesDisponibles -= habitacionesNecesarias;
            System.out.println("Check-in exitoso para " + habitacionesNecesarias + " habitaciones.");
        } else {
            System.out.println("No hay suficientes habitaciones disponibles.");
        }
    }

    public void checkOut(int habitacionesLibres) {
        habitacionesDisponibles += habitacionesLibres;
        System.out.println("Check-out exitoso para " + habitacionesLibres + " habitaciones.");
    }

    public void reservarCanchaFutbol(int tipo, int cantidad) {
        if (tipo == 11) {
            if (canchasFutbol11 >= cantidad) {
                canchasFutbol11 -= cantidad;
                System.out.println("Se reservaron " + cantidad + " canchas de fútbol 11.");
            } else {
                System.out.println("No hay suficientes canchas de fútbol 11 disponibles.");
            }
        } else if (tipo == 8) {
            if (canchasFutbol8 >= cantidad) {
                canchasFutbol8 -= cantidad;
                System.out.println("Se reservaron " + cantidad + " canchas de fútbol 8.");
            } else {
                System.out.println("No hay suficientes canchas de fútbol 8 disponibles.");
            }
        } else if (tipo == 5) {
            if (canchasFutbol5 >= cantidad) {
                canchasFutbol5 -= cantidad;
                System.out.println("Se reservaron " + cantidad + " canchas de fútbol 5.");
            } else {
                System.out.println("No hay suficientes canchas de fútbol 5 disponibles.");
            }
        } else {
            System.out.println("Tipo de cancha de fútbol no válido.");
        }
    }

    public void gestionarPileta(int personas) {
        if (personasEnPileta + personas <= capacidadPileta) {
            personasEnPileta += personas;
            System.out.println("Se permitió la entrada de " + personas + " personas a la pileta.");
        } else {
            System.out.println("La pileta ha alcanzado su capacidad máxima.");
        }
    }

    public void salirPileta(int personas) {
        if (personasEnPileta >= personas) {
            personasEnPileta -= personas;
            System.out.println("Salieron " + personas + " personas de la pileta.");
        } else {
            System.out.println("No hay suficientes personas en la pileta.");
        }
    }

    public void reservarCanchaTenis(String tipo, int cantidad) {
        int index = 0;
        switch (tipo) {
            case "cemento":
                index = 0;
                break;
            case "cesped":
                index = 1;
                break;
            case "polvo_de_ladrillo":
                index = 2;
                break;
            default:
                System.out.println("Tipo de cancha de tenis no válido.");
                return;
        }

        if (index < canchasTenis.size() && canchasTenis.get(index) >= cantidad) {
            canchasTenis.set(index, canchasTenis.get(index) - cantidad);
            System.out.println("Se reservaron " + cantidad + " canchas de tenis de " + tipo + ".");
        } else {
            System.out.println("No hay suficientes canchas de tenis de " + tipo + " disponibles.");
        }
    }

    public static void main(String[] args) {
        Admin administrador = new Admin();
        administrador.checkIn(3);
        administrador.reservarCanchaFutbol(11, 1);
        administrador.gestionarPileta(30);
        administrador.salirPileta(10);
        administrador.reservarCanchaTenis("cemento", 2);
    }
}
