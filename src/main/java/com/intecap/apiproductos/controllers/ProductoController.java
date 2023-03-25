package com.intecap.apiproductos.controllers;

import com.intecap.apiproductos.models.Producto;
import com.intecap.apiproductos.response.ProductoResponseRest;
import com.intecap.apiproductos.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producto")
public class ProductoController {

    @Autowired
    private IService<ProductoResponseRest, Producto> productoService;

    @GetMapping("")
    public ResponseEntity<ProductoResponseRest> getProductos(){
        return productoService.buscar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseRest> getProductoById(@PathVariable int id){
        return productoService.buscarById(id);
    }

    @PostMapping("")
    public ResponseEntity<ProductoResponseRest> saveProductos(@RequestBody Producto producto){
        return productoService.guardar(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseRest> updateProducto(@PathVariable int id, @RequestBody Producto producto){
        return productoService.actualizar(id,producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoResponseRest> deleteProducto(@PathVariable int id){
        return productoService.eliminar(id);
    }
}
