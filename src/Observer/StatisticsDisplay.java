package Observer;

public class StatisticsDisplay implements Observer {
    private float suma_temp = 0;
    private int lecturas = 0;
    private float maxTemp = Float.MIN_VALUE;
    private float minTemp = Float.MAX_VALUE;

    @Override
    public void update(float temperature, float humidity, float pressure, int aqi) {
        suma_temp += temperature;
        lecturas++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }
        if (temperature < minTemp) {
            minTemp = temperature;
        }
    }

    @Override
    public String display() {
        float promedio = lecturas > 0 ? suma_temp / lecturas : 0;
        return String.format(
                "Estadísticas:\nPromedio Temp: %.2f°C\nMáxima Temp: %.2f°C\nMínima Temp: %.2f°C\n",
                promedio, maxTemp, minTemp
        );
    }
}