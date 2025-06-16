import Observer.*;
import Subject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WeatherStationGUI extends JFrame {
    private WeatherData weatherData = new WeatherData();
    private JTextArea displayArea;
    private JTextField alertField;
    private JSpinner tempSpinner, humSpinner, presSpinner, aqiSpinner;

    private AlertSystem alertSystem; // Referencia para leer el estado de alerta

    public WeatherStationGUI() {
        setTitle("Estación Meteorológica - Patrón Observer");
        setSize(600, 550);
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

        // visualización
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // alerta
        alertField = new JTextField("Sistema de alertas: Sin alertas.");
        alertField.setEditable(false);
        add(alertField, BorderLayout.SOUTH);

        // Botones
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        JButton addCurrent = new JButton("Agregar Condiciones Actuales");
        JButton addStats = new JButton("Agregar Estadísticas");
        JButton addForecast = new JButton("Agregar Pronóstico");
        JButton addAQI = new JButton("Agregar Calidad Aire");

        buttonPanel.add(addCurrent);
        buttonPanel.add(addStats);
        buttonPanel.add(addForecast);
        buttonPanel.add(addAQI);

        add(buttonPanel, BorderLayout.EAST);


        alertSystem = new AlertSystem();
        weatherData.registerObserver(alertSystem);

        // Listeners
        updateBtn.addActionListener(e -> {
            float t = ((Double) tempSpinner.getValue()).floatValue();
            float h = ((Double) humSpinner.getValue()).floatValue();
            float p = ((Double) presSpinner.getValue()).floatValue();
            int aqi = (Integer) aqiSpinner.getValue();
            weatherData.setMeasurements(t, h, p, aqi);
            mostrarDatos();
            mostrarAlerta();
        });

        addCurrent.addActionListener(e -> weatherData.registerObserver(new CurrentConditionsDisplay()));
        addStats.addActionListener(e -> weatherData.registerObserver(new StatisticsDisplay()));
        addForecast.addActionListener(e -> weatherData.registerObserver(new ForecastDisplay()));
        addAQI.addActionListener(e -> weatherData.registerObserver(new AQIDisplay()));
    }

    private void mostrarDatos() {
        StringBuilder sb = new StringBuilder();
        for (Observer obs : weatherData.getObservers()) {
            sb.append(obs.display()).append("\n\n");
        }
        displayArea.setText(sb.toString());
    }

    private void mostrarAlerta() {
        alertField.setText(alertSystem.getEstadoAlerta());
    }
}
