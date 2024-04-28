package calendariofestivo.calendariofestivo.core.interfaces.servicios.implementacion;

import java.util.List;

import org.springframework.stereotype.Service;

import calendariofestivo.calendariofestivo.core.dominio.Festivo;
import calendariofestivo.calendariofestivo.core.interfaces.repositorio.IFestivoRepositorio;
import calendariofestivo.calendariofestivo.core.interfaces.servicios.IfestivoServicio;

@Service
public class FestivoServicioImpl implements IfestivoServicio {

    public IFestivoRepositorio festivoRepository;

    public FestivoServicioImpl(IFestivoRepositorio festivoRepository) {
        this.festivoRepository = festivoRepository;
    }

    @Override
    public List<Festivo> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public Festivo obtener(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtener'");
    }

    @Override
    public List<Festivo> buscar(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }

    @Override
    public String validarSiEsFestivo(Integer dia, Integer mes, Integer ano) {

        boolean esFechaValida = esFechaValida(dia, mes, ano);
        if (!esFechaValida) {
            return "No es una fecha válida";
        }

        boolean esFestivo = esFestivo(dia, mes, ano);
        if (esFestivo) {
            return "Es Festivo";
        } else {
            return "No es festivo";
        }
    }

    public boolean esFechaValida(Integer dia, Integer mes, Integer ano) {
        return true;
    }

    public boolean esFestivo(Integer dia, Integer mes, Integer ano) {

        // Buscar la el dia, el mes en la base de datos
        Festivo festivo = this.festivoRepository.findByDiaAndMes(dia, mes);
        if (festivo != null) {
            if (festivo.getIdTipo() == 1) {
                return true;

            } else if (festivo.getIdTipo() == 2) {
                // Validar tipo 2
                return true;

            } else if (festivo.getIdTipo() == 3) {
                // Validar tipo 3
                return true;

            } else if (festivo.getIdTipo() == 4) {
                // Validar tipo 4
                return true;

            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
