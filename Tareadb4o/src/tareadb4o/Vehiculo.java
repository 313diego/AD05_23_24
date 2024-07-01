/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareadb4o;

/**
 *
 * @author diego
 */
public class Vehiculo {
    
    private String matricula;
    private String bastidor;
    private int cilindrada;
    private String combustible;
    private String distintivo_ambiental;

    public Vehiculo() {
    }

    public Vehiculo(String matricula, String bastidor, int cilindrada, String combustible, String distintivo_ambiental) {
        this.matricula = matricula;
        this.bastidor = bastidor;
        this.cilindrada = cilindrada;
        this.combustible = combustible;
        this.distintivo_ambiental = distintivo_ambiental;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getBastidor() {
        return bastidor;
    }

    public void setBastidor(String bastidor) {
        this.bastidor = bastidor;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getDistintivo_ambiental() {
        return distintivo_ambiental;
    }

    public void setDistintivo_ambiental(String distintivo_ambiental) {
        this.distintivo_ambiental = distintivo_ambiental;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "matricula=" + matricula + ", bastidor=" + bastidor + ", cilindrada=" + cilindrada +
                ", combustible=" + combustible + ", distintivo_ambiental=" + distintivo_ambiental + '}';
    }

    
    
}
