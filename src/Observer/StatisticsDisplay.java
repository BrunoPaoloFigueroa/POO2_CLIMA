package Observer;

public class StatisticsDisplay implements Observer {
    private float max = Float.MIN_VALUE, min = Float.MAX_VALUE, sum = 0;
    private int count = 0;

    @Override
    public void update(float temperatura, float humedad, float presion, int aqi) {
        sum += temperatura;
        count++;
        if (temperatura > max) max = temperatura;
        if (temperatura < min) min = temperatura;
    }

    @Override
    public String display() {
        if (count == 0) return "No hay datos suficientes";
        return String.format("Prom: %.1f°C | Máx: %.1f°C | Mín: %.1f°C", sum / count, max, min);
    }
}