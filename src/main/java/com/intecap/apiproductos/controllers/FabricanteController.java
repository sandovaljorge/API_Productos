package com.intecap.apiproductos.controllers;

import com.intecap.apiproductos.models.Fabricante;
import com.intecap.apiproductos.response.FabricanteResponseRest;
import com.intecap.apiproductos.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fabricante")
public class FabricanteController {
    @Autowired
    private IService<FabricanteResponseRest, Fabricante> fabricanteService;

    @GetMapping("")
    public ResponseEntity<FabricanteResponseRest> getFabricantes(){
        return fabricanteService.buscar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricanteResponseRest> getFabricanteById(@PathVariable int id){
        return fabricanteService.buscarById(id);
    }

    @PostMapping("")
    public ResponseEntity<FabricanteResponseRest> saveFabricante(@RequestBody Fabricante fabricante){
        return fabricanteService.guardar(fabricante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FabricanteResponseRest> updateFabricante(@PathVariable int id,@RequestBody Fabricante fabricante){
        return fabricanteService.actualizar(id, fabricante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FabricanteResponseRest> deleteFabricante(@PathVariable int id){
        return fabricanteService.eliminar(id);
    }
}
