package com.emtrafesa.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class AsientoDetalleDTO {
    private int piso;
    private int numeroAsiento;
    private Double precio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsientoDetalleDTO that = (AsientoDetalleDTO) o;
        return piso == that.piso && numeroAsiento == that.numeroAsiento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piso, numeroAsiento);
    }
}
