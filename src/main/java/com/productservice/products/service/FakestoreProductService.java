package com.productservice.products.service;

import com.netflix.discovery.converters.Auto;
import com.productservice.products.dtos.ProductRequestDtoFS;
import com.productservice.products.dtos.ProductResponseDtoFS;
import com.productservice.products.exceptions.ProductNotPresentException;
import com.productservice.products.models.Category;
import com.productservice.products.models.Product;
import com.productservice.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("ProductService")
public class FakestoreProductService implements IProductService{


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getSingleProduct(Long id) throws ProductNotPresentException {

        if(id>20 && id<=40){
            throw new ProductNotPresentException();
        }
        if(id>40){
            throw new ArithmeticException();
        }
        ProductResponseDtoFS response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                ProductResponseDtoFS.class);

        return getProductFromResponseDTO(response);
    }

    @Override
    public List<Product> getAllProducts() {
        ProductResponseDtoFS[] products = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                ProductResponseDtoFS[].class);


        List<Product> output = new ArrayList<>();
        for(ProductResponseDtoFS productResponseDto: products){
            output.add(getProductFromResponseDTO(productResponseDto));
        }

        return output;
    }

    @Override
    public ProductResponseDtoFS createProduct(ProductRequestDtoFS product) {
        Product p = new Product();
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        p.setDescr(product.getDescription());
        productRepository.save(p);

        ProductResponseDtoFS productResponse = new ProductResponseDtoFS();
        productResponse.setTitle(p.getTitle());
        productResponse.setPrice(p.getPrice());
        return productResponse;
    }

    private Product getProductFromResponseDTO(ProductResponseDtoFS response) {

        Product product = new Product();
        product.setId(response.getId());
        product.setName(response.getTitle());
        product.setDescr(response.getDescription());
        product.setPrice(response.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(response.getCategory());
        product.setImage(response.getImage());

        return product;
    }
}
