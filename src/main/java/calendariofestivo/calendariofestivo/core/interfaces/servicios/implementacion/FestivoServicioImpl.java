package calendariofestivo.calendariofestivo.core.interfaces.servicios.implementacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import calendariofestivo.calendariofestivo.core.dominio.Festivo;
import calendariofestivo.calendariofestivo.core.interfaces.repositorio.IFestivoRepositorio;
import calendariofestivo.calendariofestivo.core.interfaces.servicios.IfestivoServicio;
import calendariofestivo.calendariofestivo.core.interfaces.servicios.ServicioFecha;

@Service
public class FestivoServicioImpl implements IfestivoServicio {

    private final IFestivoRepositorio festivoRepository;

    public FestivoServicioImpl(IFestivoRepositorio festivoRepository) {
        this.festivoRepository = festivoRepository;
    }

    @Override
    public List<Festivo> listar() {
        return festivoRepository.findAll();
    }

    @Override
    public Festivo obtener(int id) {
        return festivoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Festivo> buscar(String nombre) {
        return festivoRepository.findByNombre(nombre);
    }

    @Override
    public String validarSiEsFestivo(Integer dia, Integer mes, Integer ano) {
        boolean esFechaValida = esFechaValida(dia, mes, ano);
        if (!esFechaValida) {
            return "No es una fecha válida";
        }

        return determinarMensajeFestivo(dia, mes, ano);
    }

    public boolean esFechaValida(Integer dia, Integer mes, Integer ano) {
        Calendar calendario = Calendar.getInstance();
        calendario.setLenient(false);
        calendario.set(ano, mes - 1, dia);
        try {
            calendario.getTime();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String determinarMensajeFestivo(Integer dia, Integer mes, Integer ano) {
        Festivo festivo = festivoRepository.findByDiaAndMes(dia, mes).stream().findFirst().orElse(null);
        if (festivo != null) {
            if (festivo.getIdTipo() == 1) {
                return "Es festivo = " + " " + festivo.getNombre();
            }
            if (festivo.getIdTipo() == 2) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(ano, mes - 1, dia);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.MONDAY) {
                    return "Es festivo = " + " " + festivo.getNombre();
                } else {
                    Date fecha = calendar.getTime();
                    Date nuevoFestivo = ServicioFecha.siguienteLunes(fecha);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    return "No es festivo, se traslada al siguiente lunes " + sdf.format(nuevoFestivo) + " : "
                            + festivo.getNombre();
                }
            }
        }
        return determinarOtrosFestivos(ano, mes, dia);
    }

    public String determinarOtrosFestivos(Integer ano, Integer mes, Integer dia) {
        Date juevesSanto = ServicioFecha.getJuevesSanto(ano);
        Date viernesSanto = ServicioFecha.getViernesSanto(ano);
        Date domingoPascua = ServicioFecha.getDomingoPascua(ano);
        Date ascensionSenor = ServicioFecha.getAscensionSenor(ano);
        Date corpuschristi = ServicioFecha.getCorpusChristi(ano);
        Date sagradocorazon = ServicioFecha.getSagradoCorazon(ano);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Calendar fechaIngresada = Calendar.getInstance();
        fechaIngresada.set(ano, mes - 1, dia);
        ServicioFecha.resetearHoraMinutosSegundos(fechaIngresada);

        if (ServicioFecha.sonFechasIguales(juevesSanto, fechaIngresada.getTime())) {
            return "Es festivo: Jueves Santo";

        } else if (ServicioFecha.sonFechasIguales(viernesSanto, fechaIngresada.getTime())) {
            return "Es festivo: Viernes Santo";

        } else if (ServicioFecha.sonFechasIguales(domingoPascua, fechaIngresada.getTime())) {
            return "Es festivo: Domingo de Pascua";

        } else if (ServicioFecha.sonFechasIguales(corpuschristi, fechaIngresada.getTime())) {
            if (fechaIngresada.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                Date siguienteLunes = ServicioFecha.siguienteLunes(fechaIngresada.getTime());
                return "No es festivo, se traslada al siguiente lunes " + sdf.format(siguienteLunes);
            } else {
                return "Es festivo: Corpus Christi";
            }

        } else if (ServicioFecha.sonFechasIguales(sagradocorazon, fechaIngresada.getTime())) {
            if (fechaIngresada.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                Date siguienteLunes = ServicioFecha.siguienteLunes(fechaIngresada.getTime());
                return "No es festivo, se traslada al siguiente lunes " + sdf.format(siguienteLunes) + " : " + "Sagrado Corazón de Jesús";
            } else {
                return "Es festivo: Sagrado Corazón de Jesús";
            }

            

        } else if (ServicioFecha.sonFechasIguales(ascensionSenor, fechaIngresada.getTime())) {
            if (fechaIngresada.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                Date siguienteLunes = ServicioFecha.siguienteLunes(fechaIngresada.getTime());
                return "No es festivo, se traslada al siguiente lunes " + sdf.format(siguienteLunes);
            } else {
                return "Es festivo: Ascensión del Señor";
            }
        }
        return "No es festivo";
    }
}
