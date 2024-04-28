package calendariofestivo.calendariofestivo.core.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "festivo")

public class Festivo {
    @Id
    @GeneratedValue
    
    private int id;
    private String nombre;
    private int dia;
    private int mes;
    private int diaspascua;
    private int idtipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDiasPascua() {
        return diaspascua;
    }

    public void setDiasPascua(int diaspascua) {
        this.diaspascua = diaspascua;
    }

    public int getIdTipo() {
        return idtipo;
    }

    public void setIdTipo(int idtipo) {
        this.idtipo = idtipo;
    }

}
