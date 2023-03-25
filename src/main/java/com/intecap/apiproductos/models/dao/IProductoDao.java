package com.intecap.apiproductos.models.dao;

import com.intecap.apiproductos.models.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoDao extends CrudRepository<Producto, Integer> {
}
