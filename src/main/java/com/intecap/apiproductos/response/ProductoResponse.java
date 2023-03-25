package com.intecap.apiproductos.response;

import com.intecap.apiproductos.models.Producto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter@Getter
public class ProductoResponse {

    private List<Producto> productos;
}
