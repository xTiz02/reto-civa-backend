package org.prd.civaback.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "marcas")
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "marca", fetch = FetchType.LAZY)
    @JsonIgnore //Creando records personalizados evitamos la recursión infinita
    private List<BusEntity> buses;

    public MarcaEntity() {
    }

    public List<BusEntity> getBuses() {
        return buses;
    }

    public void setBuses(List<BusEntity> buses) {
        this.buses = buses;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "MarcaEntity{" +
                "buses=" + buses.size() +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}