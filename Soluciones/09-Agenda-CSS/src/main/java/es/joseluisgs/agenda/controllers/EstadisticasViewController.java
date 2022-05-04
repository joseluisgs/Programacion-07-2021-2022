package es.joseluisgs.agenda.controllers;

import es.joseluisgs.agenda.models.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class EstadisticasViewController {
    private final ObservableList<String> nombresMes = FXCollections.observableArrayList();
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis ejeX;

    @FXML
    private void initialize() {
        //Onbtenemos el array de meses con la nomenclatura del sistema
        // Locale locale = new Locale(es_ES);
        String[] meses = DateFormatSymbols.getInstance(Locale.getDefault()).getMonths();
        // La convierto en una lista de observables --> reactividad
        nombresMes.addAll(Arrays.asList(meses));

        // Asignamos los nombres de los meses a la escala X
        ejeX.setCategories(nombresMes);
    }

    public void setPersonData(List<Persona> personas) {
        // Contamos los meses
        int[] vecesPorMes = new int[12];
        personas.forEach(persona -> {
            int month = persona.getCumpleaños().getMonthValue() - 1;
            vecesPorMes[month]++;
        });

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Creamos la gráfica
        for (int i = 0; i < vecesPorMes.length; i++) {
            series.getData().add(new XYChart.Data<>(nombresMes.get(i), vecesPorMes[i]));
        }

        barChart.getData().add(series);
    }
}
