package com.apparel.service;

import com.apparel.model.Design;
import com.apparel.repository.DesignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DesignService {

    @Autowired
    private DesignRepository designRepository;



    public Design createDesign(Design design)
    {
        return designRepository.save(design);
    }

    public Optional<Design> getDesignById(Long id)
    {
        return designRepository.findById(id);
    }

    public Optional<Design> updateDesign(Long id, Design updatedDesign)
    {
        return designRepository.findById(id).map(design -> {
            design.setName(updatedDesign.getName());
            design.setColor(updatedDesign.getColor());
            design.setBrand(updatedDesign.getBrand());
            design.setMaterial(updatedDesign.getMaterial());
            design.setPrice(updatedDesign.getPrice());

            return designRepository.save(design);

        });
    }

    public void deleteDesign(Long id)
    {
        designRepository.deleteById(id);
    }

    public List<Design> getAllDesigns()
    {
        return designRepository.findAll();
    }



    public List<Design> getDesignsByBrand(String brand)
    {
        return designRepository.findByBrand(brand);
    }

    public List<Design> getDesignsByMaterial(String material)
    {
        return designRepository.findByMaterial(material);
    }

    public List<Design> getDesignsByPriceRange(BigDecimal minPrice,BigDecimal maxPrice)
    {
        return designRepository.findByPriceBetween(minPrice,maxPrice);
    }
}
