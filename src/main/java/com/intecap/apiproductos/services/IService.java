package com.intecap.apiproductos.services;

import com.intecap.apiproductos.response.Response;
import org.springframework.http.ResponseEntity;

public interface IService<T, E> {

    public ResponseEntity<T> buscar();
    public ResponseEntity<T> buscarById(int id);
    public ResponseEntity<T> guardar(E e);
    public ResponseEntity<T> actualizar(int id,E e);
    public ResponseEntity<T> eliminar(int id);
}
