import java.time.LocalDateTime;

public class Conversion {

    private String horaRegistro;
    private String tipoMonedaEntrada;
    private String tipoMonedaSalida;
    private double montoEntrada;
    private double montoSalida;

    public Conversion(String tipoMonedaEntrada, String tipoMonedaSalida, double montoMoneda, double montoSalida){
        this.tipoMonedaEntrada = tipoMonedaEntrada;
        this.tipoMonedaSalida = tipoMonedaSalida;
        this.montoEntrada = montoMoneda;
        this.montoSalida = montoSalida;
        this.horaRegistro = LocalDateTime.now().toString();
    }


    @Override
    public String toString() {
        return String.format("Conversión (%s -> %s) de %s es %s, fue hecha a las %s",tipoMonedaEntrada,tipoMonedaSalida,montoEntrada, montoSalida, horaRegistro);
    }
}