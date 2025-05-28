package com.apparel.controller;

import com.apparel.model.Design;
import com.apparel.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/designs")
public class DesignController {

    @Autowired
    private DesignService designService;



    @PostMapping
    public ResponseEntity<Design> createDesign(@RequestBody Design design)
    {
        Design createdDesign=designService.createDesign(design);

        URI location=URI.create("/designs/"+createdDesign.getId());

        return ResponseEntity.created(location).body(createdDesign);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Design> getDesignById(@PathVariable Long id)
    {
        return designService.getDesignById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Design> updateDesign(@PathVariable Long id,@RequestBody Design updatedDesign)
    {
        return designService.updateDesign(id,updatedDesign).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesign(@PathVariable Long id)
    {
        designService.deleteDesign(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<Design>> getAllDesigns()
    {
        List<Design> designs=designService.getAllDesigns();

        return ResponseEntity.ok(designs);
    }



    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Design>> getDesignsByBrand(@PathVariable String brand)
    {
        List<Design> designs=designService.getDesignsByBrand(brand);

        return designs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(designs);
    }


    @GetMapping("/material/{material}")
    public ResponseEntity<List<Design>> getDesignsByMaterial(@PathVariable String material)
    {
        List<Design> designs=designService.getDesignsByMaterial(material);

        return designs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(designs);
    }


    @GetMapping("/range/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Design>> getDesignsByPriceRange(@PathVariable BigDecimal minPrice,@PathVariable BigDecimal maxPrice)
    {
        List<Design> designs=designService.getDesignsByPriceRange(minPrice,maxPrice);

        return designs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(designs);
    }

}
