package Observer;

public class ForecastDisplay implements Observer {
    private float lastPresion = 1013.0f;
    private String pronostico = "Sin cambios";

    @Override
    public void update(float temperatura, float humedad, float presion, int aqi) {
        if (presion > lastPresion) pronostico = "Mejorando el tiempo";
        else if (presion < lastPresion) pronostico = "Empeorando el tiempo";
        else pronostico = "Sin cambios";

        lastPresion = presion;
    }

    @Override
    public String display() {
        return "Pronostico: " + pronostico;
    }
}