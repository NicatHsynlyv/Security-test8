//package com.example.SpringS.Controller;
//
//import com.example.SpringS.Service.ProductService;
//import io.swagger.v3.oas.annotations.Operation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @Operation(summary = "Get all products")
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }
//
//    @Operation(summary = "Get product by id")
//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable Long id) {
//        return productService.getProductById(id);
//    }
//
//    @Operation(summary = "Save a product")
//    @PostMapping
//    public Product saveProduct(@RequestBody Product product) {
//        return productService.saveProduct(product);
//    }
//
//    @Operation(summary = "Delete a product by id")
//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//    }
//}
