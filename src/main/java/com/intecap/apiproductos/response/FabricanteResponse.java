package com.intecap.apiproductos.response;

import com.intecap.apiproductos.models.Fabricante;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class FabricanteResponse {

    private List<Fabricante> fabricantes;

}
