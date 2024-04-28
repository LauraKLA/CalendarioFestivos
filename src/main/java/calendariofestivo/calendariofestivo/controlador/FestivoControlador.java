package calendariofestivo.calendariofestivo.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import calendariofestivo.calendariofestivo.core.dominio.Festivo;
import calendariofestivo.calendariofestivo.core.interfaces.servicios.IfestivoServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("festivo")
public class FestivoControlador {

    private IfestivoServicio servicio;

    public FestivoControlador(IfestivoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("validar/{ano}/{mes}/{dia}")
    public String validarFecha(
        @PathVariable("dia") Integer dia, 
        @PathVariable("mes") Integer mes, 
        @PathVariable("ano") Integer ano) {

        return servicio.validarSiEsFestivo(dia, mes, ano);
    }

    @GetMapping("listar")
    public List<Festivo> listar() {
        return servicio.listar();
    }

}
