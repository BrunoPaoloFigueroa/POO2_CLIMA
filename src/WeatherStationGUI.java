import Observer.*;
import Subject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WeatherStationGUI extends JFrame {
    private WeatherData weatherData = new WeatherData();
    private JTextArea displayArea;
    private JSpinner tempSpinner, humSpinner, presSpinner, aqiSpinner;

    public WeatherStationGUI() {
        setTitle("Estación Meteorológica - Patrón Observer");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        tempSpinner = new JSpinner(new SpinnerNumberModel(20.0, -50.0, 60.0, 0.1));
        humSpinner = new JSpinner(new SpinnerNumberModel(50.0, 0.0, 100.0, 1.0));
        presSpinner = new JSpinner(new SpinnerNumberModel(1013.0, 800.0, 1100.0, 0.1));
        aqiSpinner = new JSpinner(new SpinnerNumberModel(50, 0, 500, 1));

        inputPanel.add(new JLabel("Temperatura (°C):"));
        inputPanel.add(tempSpinner);
        inputPanel.add(new JLabel("Humedad (%):"));
        inputPanel.add(humSpinner);
        inputPanel.add(new JLabel("Presión (hPa):"));
        inputPanel.add(presSpinner);
        inputPanel.add(new JLabel("Calidad Aire (AQI):"));
        inputPanel.add(aqiSpinner);

        JButton updateBtn = new JButton("Actualizar Datos");
        inputPanel.add(updateBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Área de visualización
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Botones para agregar observadores
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        JButton addCurrent = new JButton("Agregar Condiciones Actuales");
        JButton addStats = new JButton("Agregar Estadísticas");
        JButton addForecast = new JButton("Agregar Pronóstico");
        JButton addAQI = new JButton("Agregar Calidad Aire");
        JButton addAlerts = new JButton("Agregar Sistema Alertas");

        buttonPanel.add(addCurrent);
        buttonPanel.add(addStats);
        buttonPanel.add(addForecast);
        buttonPanel.add(addAQI);
        buttonPanel.add(addAlerts);

        add(buttonPanel, BorderLayout.EAST);

        // Listeners
        updateBtn.addActionListener(e -> {
            float t = ((Double) tempSpinner.getValue()).floatValue();
            float h = ((Double) humSpinner.getValue()).floatValue();
            float p = ((Double) presSpinner.getValue()).floatValue();
            int aqi = (Integer) aqiSpinner.getValue();
            weatherData.setMeasurements(t, h, p, aqi);
            mostrarDatos();
        });

        addCurrent.addActionListener(e -> weatherData.registerObserver(new CurrentConditionsDisplay()));
        addStats.addActionListener(e -> weatherData.registerObserver(new StatisticsDisplay()));
        addForecast.addActionListener(e -> weatherData.registerObserver(new ForecastDisplay()));
        addAQI.addActionListener(e -> weatherData.registerObserver(new AQIDisplay()));
        addAlerts.addActionListener(e -> weatherData.registerObserver(new AlertSystem()));
    }

    private void mostrarDatos() {
        StringBuilder sb = new StringBuilder();
        for (Observer obs : weatherData.getObservers()) {
            sb.append(obs.display()).append("\n\n");
        }
        displayArea.setText(sb.toString());
    }


}