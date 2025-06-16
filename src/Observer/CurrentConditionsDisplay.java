package Observer;

public class CurrentConditionsDisplay implements Observer {
    private float temperatura, humedad;

    @Override
    public void update(float temperatura, float humedad, float presion, int aqi) {
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    @Override
    public String display() {
        return String.format("Temperatura: %.1f °C\nHumedad: %.1f %%", temperatura, humedad);
    }
}