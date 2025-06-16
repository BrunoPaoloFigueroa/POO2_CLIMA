package Observer;

public class AQIDisplay implements Observer {
    private int aqi;
    private String calificacion;

    @Override
    public void update(float temperatura, float humedad, float presion, int aqi) {
        this.aqi = aqi;
        if (aqi <= 50) calificacion = "Buena (Verde)";
        else if (aqi <= 100) calificacion = "Moderada (Amarillo)";
        else if (aqi <= 150) calificacion = "Poco saludable (Naranja)";
        else if (aqi <= 200) calificacion = "Insalubre (Rojo)";
        else if (aqi <= 300) calificacion = "Muy insalubre (Morado)";
        else calificacion = "Peligrosa (Rojo oscuro)";
    }

    @Override
    public String display() {
        return String.format("AQI: %d (%s)", aqi, calificacion);
    }
}