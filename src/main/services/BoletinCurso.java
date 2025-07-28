package main.services;

import java.util.ArrayList;
import java.util.List;

public class BoletinCurso {
    private String asignatura;
    private List<String> actividades = new ArrayList<>();
    private Double primerParcial;
    private Double segundoParcial;

    public BoletinCurso(String asignatura) {
        this.asignatura = asignatura;
    }

    public void agregarNota(String tipo, String nombreEval, double nota) {
        switch (tipo.toLowerCase()) {
            case "actividad":
                actividades.add(nombreEval + ": " + nota);
                break;
            case "parcial1":
                primerParcial = nota;
                break;
            case "parcial2":
                segundoParcial = nota;
                break;
        }
    }

    public String getAsignatura() {
        return asignatura;
    }

    public String getActividadesStr() {
        return String.join(", ", actividades);
    }

    public Double getPrimerParcial() {
        return primerParcial;
    }

    public Double getSegundoParcial() {
        return segundoParcial;
    }

    public String getPromedio() {
        List<Double> notas = new ArrayList<>();
        if (primerParcial != null) notas.add(primerParcial);
        if (segundoParcial != null) notas.add(segundoParcial);
        for (String a : actividades) {
            try {
                String[] partes = a.split(":");
                notas.add(Double.parseDouble(partes[1].trim()));
            } catch (Exception ignored) {}
        }

        if (notas.isEmpty()) return "-";
        double suma = 0;
        for (double n : notas) suma += n;
        return String.format("%.2f", suma / notas.size());
    }
}

