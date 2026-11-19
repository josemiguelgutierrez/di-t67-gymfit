package es.medac.josemiguel.gymfit;

public class Socio {
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String provincia;
    private String cuota;
    private String clase;

    public Socio(String nombre, String apellidos, String fechaNacimiento,
                 String provincia, String cuota, String clase) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.provincia = provincia;
        this.cuota = cuota;
        this.clase = clase;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getProvincia() { return provincia; }
    public String getCuota() { return cuota; }
    public String getClase() { return clase; }
}