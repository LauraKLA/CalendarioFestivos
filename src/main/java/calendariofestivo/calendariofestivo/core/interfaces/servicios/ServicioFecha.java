package calendariofestivo.calendariofestivo.core.interfaces.servicios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ServicioFecha {

    public static Date getDomingoRamos(int año) {
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        int dia = 15 + dias;
        int mes = 3;
        if (dia > 31) {
            dia = dia - 31;
            mes = 4;
        }
        return new Date(año - 1900, mes - 1, dia);
    }

    public static Date agregarDias(Date fecha, int dias) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, dias);
        return calendario.getTime();
    }

    public static Date siguienteLunes(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);

        // Obtener el día de la semana actual
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

        // Calcular cuántos días faltan para llegar al siguiente lunes
        int diasHastaLunes = Calendar.MONDAY - diaSemana;
        if (diasHastaLunes <= 0) {
            diasHastaLunes += 7;
        }
        calendario.add(Calendar.DAY_OF_MONTH, diasHastaLunes);

        Date siguienteLunes = calendario.getTime();

        return siguienteLunes;
    }

    public static Date getJuevesSanto(int ano) {
        Date domingoPascua = getDomingoPascua(ano);
        return agregarDias(domingoPascua, -3); // Jueves Santo es 3 días antes del Domingo de Pascua
    }

    public static Date getViernesSanto(int ano) {
        Date domingoPascua = getDomingoPascua(ano);
        return agregarDias(domingoPascua, -2); // Viernes Santo es 2 días antes del Domingo de Pascua
    }

    public static Date getDomingoPascua(int ano) {
        Date domingoRamos = getDomingoRamos(ano);
        return agregarDias(domingoRamos, 7); // Domingo de Pascua es 7 días después del Domingo de Ramos
    }

    public static Date getAscensionSenor(int ano) {
        Date domingoPascua = getDomingoPascua(ano);
        return agregarDias(domingoPascua, 40); // Ascensión del Señor es 40 días después del Domingo de Pascua

         //Date domingoPascua = getDomingoPascua(ano);
         //Date ascensionSenor = agregarDias(domingoPascua, 40); // Ascensión del Señores 40 días después del Domingo de Pascua

        // Calendar calendario = Calendar.getInstance();
         //calendario.setTime(ascensionSenor);

        // Verificar si la fecha de la Ascensión del Señor no cae en un lunes
         //if (calendario.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {

        //ascensionSenor = siguienteLunes(ascensionSenor);
        //}
        //return ascensionSenor;
    }

    public static Date getCorpusChristi(int ano) {
        Date domingoPascua = getDomingoPascua(ano);
        return agregarDias(domingoPascua, 61); // Corpus Christi es 61 días después del Domingo de Pascua
    }

    public static Date getSagradoCorazon(int ano) {
        Date domingoPascua = getDomingoPascua(ano);
        return agregarDias(domingoPascua, 68); // Sagrado Corazón de Jesuú es 68 días después del Domingo de Pascua
    }

    public static boolean sonFechasIguales(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date1).equals(sdf.format(date2));
    }

    public static void resetearHoraMinutosSegundos(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
