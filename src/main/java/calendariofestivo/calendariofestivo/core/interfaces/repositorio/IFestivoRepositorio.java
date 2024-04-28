package calendariofestivo.calendariofestivo.core.interfaces.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import calendariofestivo.calendariofestivo.core.dominio.Festivo;

public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {

    Festivo findByDiaAndMes(Integer dia, Integer mes);
}
