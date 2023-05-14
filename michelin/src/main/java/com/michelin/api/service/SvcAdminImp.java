package com.michelin.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ProductDto;
import com.michelin.api.dto.SalesmanDto;
import com.michelin.api.entity.Product;
import com.michelin.api.entity.Salesman;
import com.michelin.api.repository.RepoProduct;
import com.michelin.api.repository.RepoSalesman;
import com.michelin.exception.ApiException;

@Service
public class SvcAdminImp implements SvcAdmin {

    @Autowired
    RepoSalesman repo;

    @Autowired
    RepoProduct repoProduct;

     /*
     * Product Section
     */

    @Override
    public ApiResponse registerSalesman(SalesmanDto in) {
        Salesman salesman = repo.findByEmail(in.getEmail());

        if (salesman != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El correo ya esta registrado");
        }

        String password = generateNewPassword(10);
        try {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(in.getDateOfBirth());
        repo.createClient(in.getName(), in.getEmail(), password, date);
        } catch (ParseException pe) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Formato de fecha incorrecto");
        }
        return new ApiResponse("registro exitoso");
    }   

    private String generateNewPassword(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
    
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public ApiResponse deleteSalesman(Integer salesman_id) {
        return null;
     }

    public List<SalesmanDto> getAll() {
        return null;
    }

    public SalesmanDto getSalesmanById(Integer salesman_id) {
        return null;
    }



    /*
     * Product Section
     */

     @Override
     public List<Product> getProducts() {
        return null;
     }

     @Override
     public ApiResponse addProduct(ProductDto product, Integer administrator_id) {
        return null;
     }
 
     @Override
     public ApiResponse updateProduct(Integer product_id, ProductDto product) {
        return null;
     }
 
     @Override
     public ApiResponse deleteProduct(Integer product_id) {
        return null;
     }
 
}   
