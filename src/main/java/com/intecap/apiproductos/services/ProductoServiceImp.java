package com.intecap.apiproductos.services;

import com.intecap.apiproductos.models.Fabricante;
import com.intecap.apiproductos.models.Producto;
import com.intecap.apiproductos.models.dao.IProductoDao;
import com.intecap.apiproductos.response.FabricanteResponseRest;
import com.intecap.apiproductos.response.ProductoResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductoServiceImp implements IService<ProductoResponseRest, Producto>{

    private static final Logger log = Logger.getLogger(ProductoServiceImp.class.getName());
    @Autowired
    private IProductoDao productoDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductoResponseRest> buscar() {
        log.info("Listando productos.");
        ProductoResponseRest response = new ProductoResponseRest();
        try{
            List<Producto> list = (List<Producto>) productoDao.findAll();
            response.getProductoResponse().setProductos(list);
            response.setMetadata("Respuesta OK","200", "Lista de productos.");
        }catch (Exception e){
            log.severe("Error al consultar, Productos."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductoResponseRest> buscarById(int id) {
        log.info("Listando productos.");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();
        try{
            Optional<Producto> producto = productoDao.findById(id);
            if(producto.isPresent()){
                list.add(producto.get());
                response.getProductoResponse().setProductos(list);
                response.setMetadata("Respuesta OK","200", "Producto encontrado.");
            }else{
                log.severe("No se encontro ningun registro con id: "+id);
                response.setMetadata("Respuesta Null","-1", "Producto no encontrado.");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.severe("Error al consultar el producto."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductoResponseRest> guardar(Producto producto) {
        log.info("Guardando producto.");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();
        try{
            Producto productoGuardado = productoDao.save(producto);
            if(productoGuardado != null){
                list.add(productoGuardado);
                response.getProductoResponse().setProductos(list);
                response.setMetadata("Respuesta OK","200", "Producto guardado.");
            }else{
                log.severe("No se pudo guardar.");
                response.setMetadata("Respuesta Null","-1", "Producto no guardado.");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.severe("Error al guardar el Producto."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductoResponseRest> actualizar(int id, Producto producto) {
        log.info("Actualizando producto.");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();
        try{
            Optional<Producto> productoBuscado = productoDao.findById(id);
            if(productoBuscado.isPresent()){
                productoBuscado.get().setNombre(producto.getNombre());
                productoBuscado.get().setPrecio(producto.getPrecio());
                productoBuscado.get().setFabricante(producto.getFabricante());
                Producto productoActualizado = productoDao.save(productoBuscado.get());
                if(productoActualizado != null){
                    list.add(productoActualizado);
                    response.getProductoResponse().setProductos(list);
                    response.setMetadata("Respuesta OK","200", "Producto actualizado.");
                }else{
                    log.severe("No se pudo actualizar.");
                    response.setMetadata("Respuesta Nula","-1", "Error al intentar actualizar.");
                    return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.severe("No se pudo actualizar.");
                response.setMetadata("Respuesta Nula","-1", "Producto no encontrado.");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.severe("Error al actualizar el Producto."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductoResponseRest> eliminar(int id) {
        log.info("Eliminando Producto.");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();
        try{
            Optional<Producto> producto= productoDao.findById(id);
            if(producto.isPresent()){
                productoDao.deleteById(id);
                list.add(producto.get());
                response.getProductoResponse().setProductos(list);
                response.setMetadata("Respuesta OK","200", "Producto eliminado.");
            }else{
                log.severe("No se pudo eliminar.");
                response.setMetadata("Respuesta Nula","-1", "Producto no encontrado.");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.severe("Error al eliminar el Producto."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }
}
