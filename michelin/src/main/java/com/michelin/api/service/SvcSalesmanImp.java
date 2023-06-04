package com.michelin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.dto.SalesmanLoginDto;
import com.michelin.api.entity.Salesman;
import com.michelin.api.repository.RepoSalesman;
import com.michelin.exception.ApiException;

@Service
public class SvcSalesmanImp implements SvcSalesman {

    @Autowired
    RepoSalesman repo;

    @Override 
    public ApiResponse updatePassword(PasswordDto in, Integer salesman_id) {
        Salesman salesman = repo.findBySalesmanId(salesman_id);
        if (salesman == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El vendedor no existe");
        }
        repo.updatePassword(in.getNewPassword(), salesman_id);
        return new ApiResponse("Contraseña actualizada");
    }


    @Override
    public ApiResponse loginSalesman(SalesmanLoginDto in){

       Salesman salesman = repo.findByEmail(in.getEmail());
       if(salesman == null){
           throw new ApiException(HttpStatus.BAD_REQUEST, "El correo no ha sido registrado");
       }

   
       String passwordRepo = salesman.getPassword();
       String passwordIn =in.getPassword();

       if(!passwordRepo.equals(passwordIn)){
           throw new ApiException(HttpStatus.BAD_REQUEST, "La contraseña es incorrecta");
       }


       return new ApiResponse("Login exitoso ");
    }
}
