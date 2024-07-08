import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class ServicioConversion {

    public double consultarConversiones(String tipoMonedaEntrada, String tipoMonedaSalida, double montoMoneda){
        URI direction = URI.create("https://v6.exchangerate-api.com/v6/e5e6ab07d71c48e6da91fb2a/latest/"+tipoMonedaEntrada);
        double montoConversion = 0;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direction)
                .build();
        try {
            HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
            ConversionOmdb c = new Gson().fromJson(response.body(), ConversionOmdb.class);
            montoConversion = c.conversion_rates().get(tipoMonedaSalida);
            return montoConversion*montoMoneda;


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            System.out.printf(e.getMessage());
        }
        return montoConversion;
    }

    public ArrayList<String> consultarMonedas(){
        ArrayList<String> monedas = new ArrayList<>();
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/e5e6ab07d71c48e6da91fb2a/latest/USD");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            ConversionOmdb c = new Gson().fromJson(response.body(),ConversionOmdb.class);
            monedas.addAll(c.conversion_rates().keySet());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return monedas;
    }



}
