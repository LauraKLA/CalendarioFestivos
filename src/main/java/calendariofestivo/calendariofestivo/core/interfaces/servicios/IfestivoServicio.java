package calendariofestivo.calendariofestivo.core.interfaces.servicios;

import java.util.List;

import calendariofestivo.calendariofestivo.core.dominio.Festivo;

public interface IfestivoServicio {

    public List<Festivo> listar();

    public Festivo obtener(int id);

    public List<Festivo> buscar(String nombre);

    public String validarSiEsFestivo(Integer dia, Integer mes, Integer ano);
}
