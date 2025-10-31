package com.jyothish.ecom_project.service;

import com.jyothish.ecom_project.dao.ProductRepository;
import com.jyothish.ecom_project.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts()
    {
        return repository.findAll();
    }

    public Product getProductById(int id)
    {
         return repository.findById(id).orElse(new Product());
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException
    {
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());
        }

        return repository.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile)throws IOException
    {
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
       return repository.save(product);
    }

    public void deleteProduct(int id)
    {
        repository.deleteById(id);
    }

    public List<Product> searchProducts(String keyword)
    {
        return repository.searchProducts(keyword);
    }
}
