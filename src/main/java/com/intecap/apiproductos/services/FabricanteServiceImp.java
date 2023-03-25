package com.intecap.apiproductos.services;

import com.intecap.apiproductos.models.Fabricante;
import com.intecap.apiproductos.models.dao.IFabricanteDao;
import com.intecap.apiproductos.response.FabricanteResponseRest;
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
public class FabricanteServiceImp implements IService<FabricanteResponseRest, Fabricante>{

    private static final Logger log = Logger.getLogger(FabricanteServiceImp.class.getName());
    @Autowired
    private IFabricanteDao fabricanteDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscar() {
        log.info("Listando fabricantes.");
        FabricanteResponseRest response = new FabricanteResponseRest();
        try{
            List<Fabricante> list = (List<Fabricante>) fabricanteDao.findAll();
            response.getFabricanteResponse().setFabricantes(list);
            response.setMetadata("Respuesta OK","200", "Lista de fabricantes.");
        }catch (Exception e){
            log.severe("Error al consultar, Fabricantes."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarById(int id) {
        log.info("Buscando fabricante por id.");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();
        try{
            Optional<Fabricante> fabricante= fabricanteDao.findById(id);
            if(fabricante.isPresent()){
                list.add(fabricante.get());
                response.getFabricanteResponse().setFabricantes(list);
                response.setMetadata("Respuesta OK","200", "Fabricante encontrado.");
            }else{
                log.severe("No se encontro ningun registro con id: "+id);
                response.setMetadata("Respuesta Null","-1", "Fabricante no encontrado.");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            log.severe("Error al consultar el Fabricante."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> guardar(Fabricante fabricante) {
        log.info("Guardando fabricante.");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();
        try{
            Fabricante fabricanteGuardado = fabricanteDao.save(fabricante);
            if(fabricanteGuardado != null){
                list.add(fabricanteGuardado);
                response.getFabricanteResponse().setFabricantes(list);
                response.setMetadata("Respuesta OK","200", "Fabricante guardado.");
            }else{
                log.severe("No se pudo guardar.");
                response.setMetadata("Respuesta Null","-1", "Fabricante no guardado.");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.severe("Error al guardar el Fabricante."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> actualizar(int id, Fabricante fabricante) {
        log.info("Actualizando fabricante.");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();
        try{
            Optional<Fabricante> fabricanteBuscado= fabricanteDao.findById(id);
            if(fabricanteBuscado.isPresent()){
                fabricanteBuscado.get().setNombre(fabricante.getNombre());
                Fabricante fabricanteActualizado = fabricanteDao.save(fabricanteBuscado.get());
                if(fabricanteActualizado != null){
                    list.add(fabricanteActualizado);
                    response.getFabricanteResponse().setFabricantes(list);
                    response.setMetadata("Respuesta OK","200", "Fabricante actualizado.");
                }else{
                    log.severe("No se pudo actualizar.");
                    response.setMetadata("Respuesta Nula","-1", "Error al intentar actualizar.");
                    return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.severe("No se pudo actualizar.");
                response.setMetadata("Respuesta Nula","-1", "Fabricante no encontrado.");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.severe("Error al actualizar el Fabricante."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> eliminar(int id) {
        log.info("Eliminando fabricante.");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();
        try{
            Optional<Fabricante> fabricante= fabricanteDao.findById(id);
            if(fabricante.isPresent()){
                fabricanteDao.deleteById(id);
                list.add(fabricante.get());
                response.getFabricanteResponse().setFabricantes(list);
                response.setMetadata("Respuesta OK","200", "Fabricante eliminado.");
            }else{
                log.severe("No se pudo eliminar.");
                response.setMetadata("Respuesta Nula","-1", "Fabricante no encontrado.");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.severe("Error al eliminar el Fabricante."+e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Mala.","-1","Respuesta incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }


}
