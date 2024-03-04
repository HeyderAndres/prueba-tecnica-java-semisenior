/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.puntored.movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author heiderarellano
 */
@Data
@Schema(name = "ApiResponseDTO", description = "Respuesta Genérica de los servicios")
public class ApiResponseDTO<T> implements Serializable {
    @Schema(description = "Estado de la petición, true para exitosa y false para fallida")
    private boolean state = false;

    @Schema(description = "Mensaje de respuesta de la petición")
    private String message = null;

    @Schema(description = "Código de respuesta de la petición, Exitosa 0")
    private String code = null;

    @Schema(description = "Objeto que contiene los datos de respuesta de la petición")
    private T data = null;

    public void setSuccesTrasaction(T data) {
        this.setMessage("Transaccion Exitosa");
        this.setCode("0");
        this.setState(Boolean.TRUE);
        this.setData(data);
    }

    public void setSuccesQuery(T data) {
        this.setMessage("Consulta Exitosa");
        this.setCode("200");
        this.setState(Boolean.TRUE);
        this.setData(data);
    }

    public void setFailRequestParams() {
        this.setMessage("Datos incorrectos");
        this.setCode("400");
        this.setState(Boolean.FALSE);
    }

    public void setFailService() {
        this.setMessage("Fallo del sistema");
        this.setCode("500");
        this.setState(Boolean.FALSE);
    }

    public void setDeclinedTrasaction(T data) {
        this.setMessage("Transaccion Declinada");
        this.setCode("404");
        this.setState(Boolean.FALSE);
        this.setData(data);
    }

    public void setDeclinedTrasaction(T data, String message) {
        this.setMessage(message);
        this.setCode("404");
        this.setState(Boolean.FALSE);
        this.setData(data);
    }

    public void setFailQuery(T data) {
        this.setMessage("Consulta Fallida");
        this.setCode("404");
        this.setState(Boolean.FALSE);
        this.setData(data);
    }

    public void setFailQuery(String message) {
        this.setMessage(message);
        this.setCode("404");
        this.setState(Boolean.FALSE);
        this.setData(null);
    }
}
