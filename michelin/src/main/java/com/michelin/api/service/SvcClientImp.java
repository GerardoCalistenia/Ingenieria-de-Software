package com.michelin.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.michelin.api.controller.CtrlLoginLoad;
import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ClientDto;
import com.michelin.api.dto.LoginDto;
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.entity.Administrator;
import com.michelin.api.entity.Client;
import com.michelin.api.entity.Product;
import com.michelin.api.entity.Salesman;
import com.michelin.api.repository.RepoAdministrator;
import com.michelin.api.repository.RepoClient;
import com.michelin.api.repository.RepoOrder;
import com.michelin.api.repository.RepoProduct;
import com.michelin.api.repository.RepoSale;
import com.michelin.api.repository.RepoSalesman;
import com.michelin.exception.ApiException;

@Service
public class SvcClientImp implements SvcClient {
    
    @Autowired
    RepoClient repo;
    
    @Autowired
    RepoAdministrator repoAdmin;

    @Autowired
    RepoProduct repoProduct;

    @Autowired
    RepoSalesman repoSalesman;

    @Autowired
    RepoClient repoClient;

    @Autowired
    RepoSale repoSale;

    @Autowired
    RepoOrder repoOrder;

    @Autowired
    JavaMailSender emailSender;
    
    @Autowired
    CtrlLoginLoad ctrlLogin;
    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public ApiResponse registerClient(ClientDto in) throws MessagingException {

        String name = in.getName();
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Debes registrar un nombre");
        }

        if (!isValidEmail(in.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Formato de correo electrónico incorrecto");
        }
        
        Administrator admin = repoAdmin.findByEmail(in.getEmail());
        if (admin != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El correo ya esta registrado en administrador");
        }

        Salesman salesman = repoSalesman.findByEmail(in.getEmail());
        if (salesman != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El correo ya esta registrado en vendedor");
        }
        
        Client client = repo.findByEmail(in.getEmail());

        if (client != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "El correo ya esta registrado");
        }

        String password = generateNewPassword(10);

        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(in.getDateOfBirth());

            if (date == null || !isValidDate(date)) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Fecha de nacimiento inválida");
            }

            repo.createClient(in.getName(), in.getEmail(), password, date);
        } catch (ParseException pe) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Formato de fecha incorrecto");
        }
        sendPasswordEmail(in.getEmail(), password);
        return new ApiResponse("registro exitoso");
    }

    private boolean isValidDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
    
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (year < 1000 || year > 2023){
            return false;
        }

        if (month < 0 || month > 12){
            return false;
        }

        if (day < 1 || day > 31){
            return false;
        }
    
        return true;
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

    private void sendPasswordEmail(String email, String password) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setSubject("Michelin: Nueva contraseña generada!");
        helper.setText("Su nueva contraseña es: " + password);

        emailSender.send(message);
    }



    @Override
    public ApiResponse updatePassword(PasswordDto in, String mail) throws MessagingException {
        Client client = repo.findByEmail(mail);
        if (client == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El cliente con dicho correo no existe");
        }
        repo.updatePassword(in.getNewPassword(), mail);

        String email = client.getEmail();
        sendPasswordEmail(email, in.getNewPassword());

        return new ApiResponse("Contraseña actualizada");

    }

    public List<Product> getAllProducts() {
        return repoProduct.getAllProducts();
    }

    public ApiResponse createOrder(Integer product_id, Integer client_id) {
        Client client = repoClient.findByClientId(client_id);

        if (client == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El cliente no existe");  
        }

        Product product = repoProduct.findProductById(product_id);

        if (product == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El producto no esta disponible"); 
        }      

       Integer sale_id =  repoSale.createSale(product.getPrice(), 3, new Date());
       System.out.println(sale_id);
       
       repoOrder.createSale(4, 5, client_id);

        return new ApiResponse("pedido en proceso");   
    }

    @Override
    public ApiResponse login(LoginDto in) {
        Client client = repo.findByEmail(in.getEmail());
        if (client == null) {
            return null;
        }

        if (!client.getPassword().equals(in.getPassword())) {
            throw new ApiException(HttpStatus.NOT_FOUND, "La contraseña es incorrecta");
        }

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        // Sesión en cookie
        Cookie cookie = new Cookie("nombreCookie", client.getEmail());
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        ctrlLogin.homePage();

        return new ApiResponse("login exitoso");
    }

}