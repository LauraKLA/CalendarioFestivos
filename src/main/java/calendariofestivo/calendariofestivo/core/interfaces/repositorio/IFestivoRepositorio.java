package calendariofestivo.calendariofestivo.core.interfaces.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import calendariofestivo.calendariofestivo.core.dominio.Festivo;

public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {
    List<Festivo> findByDiaAndMes(Integer dia, Integer mes);
    List<Festivo> findByNombre(String nombre);
}
