package com.drem.gasto.model.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("usuarios")
public class Usuario {

    @Id
    private Integer id;
    private String nombre;
    private String email;
    private String contrasena;
    private String identifier;

}
