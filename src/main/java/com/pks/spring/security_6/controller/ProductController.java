package com.pks.spring.security_6.controller;



import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private record Product(
            Integer productId,
            String productName,
            double price
    ){}

    List<Product> products = new ArrayList<>(
    List.of( new Product(1,"iphone",990.90),
            new Product(2,"tecno",69.90),
            new Product(3,"samsumg",99.90)
    )
      );

    @GetMapping
    public List<Product> getProducts(){
        return products;
    }

    @PostMapping("/{id}")
    public Product getProductById(@PathVariable Integer id){
       return  products.stream().filter(p-> p.productId == id).findFirst()
               .orElseThrow(()->new RuntimeException("No product with id found"));
    }

    @PostMapping
    public List<Product> addProduct(
            @RequestBody Product newProduct){
        final Integer count = Math.toIntExact(products.stream().count());

      Product LatestRecord = new Product(
              count+1,
                newProduct.productName,
                newProduct.price);
      products.add(LatestRecord);
return products;

    }
}
