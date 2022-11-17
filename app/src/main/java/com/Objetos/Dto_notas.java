package com.Objetos;

import java.io.Serializable;

public class Dto_notas  {
  String correo_usario;
  String descripcion;
  int estado;
  String fecha_hora_actual;
  String fecha_nota;
  String id_nota;
  String titulo;
  long timestamp;

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getCorreo_usario(String correo_usario) {
    return correo_usario;
  }

  public void setCorreo_usario(String correo_usario) {
    this.correo_usario = correo_usario;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getEstado(String estado) {
    return this.estado;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

  public String getFecha_hora_actual() {
    return fecha_hora_actual;
  }

  public void setFecha_hora_actual(String fecha_hora_actual) {
    this.fecha_hora_actual = fecha_hora_actual;
  }

  public String getFecha_nota(String fecha) {
    return fecha_nota;
  }

  public void setFecha_nota(String fecha_nota) {
    this.fecha_nota = fecha_nota;
  }

  public String getId_nota() {
    return id_nota;
  }

  public void setId_nota(String id_nota) {
    this.id_nota = id_nota;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  @Override
  public String toString(){ return titulo;}


}
