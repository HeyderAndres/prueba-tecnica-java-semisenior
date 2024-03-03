/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.puntored.movies.dto;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author heiderarellano
 */
@Data
public class ApiResponseDTO<T> implements Serializable {
    private boolean state = false;
    private String message = null;
    private String code = null;
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
