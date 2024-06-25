package modelo;

import javax.swing.JOptionPane;
import controlador.ClienteControlador;

public class Cliente {
    private int id_huesped; // ID huesped = DNI 
    private String nombre_huesped;
    private String apellido;
    private String correo;
    private String telefono;
    private String resena;
   


    public Cliente(int id_huesped, String nombre_huesped, String apellido, String correo, String telefono, String resena) {
        this.id_huesped = id_huesped;
        this.nombre_huesped = nombre_huesped;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.resena = resena;
    }

	public int getId_huesped() {
        return id_huesped;
    }

    public void setId_huesped(int id_huesped) {
        this.id_huesped = id_huesped;
    }

    public String getNombre_huesped() {
        return nombre_huesped;
    }

    public void setNombre_huesped(String nombre_huesped) {
        this.nombre_huesped = nombre_huesped;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }

    @Override
    public String toString() {
        return "Cliente [id_huesped=" + id_huesped + ", nombre_huesped=" + nombre_huesped + ", apellido=" + apellido
                + ", correo=" + correo + ", telefono=" + telefono + ", resena=" + resena + "]";
    }

    public static Cliente pedirDatosCliente() {
    	int dni = Integer.parseInt(JOptionPane.showInputDialog("Ingresar DNI"));
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:\n tene en cuenta que este es el nombre que se guarda en la reserva");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del cliente:");
        String correo = JOptionPane.showInputDialog("Ingrese el correo del cliente:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del cliente:");
        String resena = "";
        
        Cliente cliente = new Cliente(dni, nombre, apellido, correo, telefono, resena);
        ClienteControlador clienteControlador = new ClienteControlador();
        clienteControlador.addCliente(cliente);
        return cliente;
    }

   
}
