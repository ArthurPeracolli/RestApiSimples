package com.c6bank.cliente.Dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class ClienteDto {

    @Email
    private String email;

    @NotNull
    private String senha;

}
