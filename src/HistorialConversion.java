import java.util.ArrayList;
import java.util.List;

public class HistorialConversion {
    private List<Conversion> listaConversiones;

    public HistorialConversion(){
        this.listaConversiones = new ArrayList<>();
    }

    public List<Conversion> getListaConversiones() {
        return listaConversiones;
    }

    public void agregarConversion(Conversion c){
        this.listaConversiones.add(c);
    }

}
