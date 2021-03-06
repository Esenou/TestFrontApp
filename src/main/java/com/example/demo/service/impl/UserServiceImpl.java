package com.example.demo.service.impl;

import com.example.demo.config.RestTemplateConfig;
import com.example.demo.enums.UserStatus;
import com.example.demo.model.Token;
import com.example.demo.model.UserCreat;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserPage;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;


@Service
public class UserServiceImpl implements UserService {

    private static String token = "";
    final private String baseUrl = "http://localhost:8080/api/user/";
    final private RestTemplateConfig restTemplate;

    public UserServiceImpl(RestTemplateConfig restTemplateConfig) {
        this.restTemplate = restTemplateConfig;
        restTemplateConfig.getRestTemplate(token);

    }


    @Override
    public UserPage findAll(String page) {

        ResponseEntity<UserPage> response = restTemplate.getRestTemplate(token).exchange(
                baseUrl + "findAllByUsername" + "?searchResult=" + "&page=" + page,
                HttpMethod.GET,
                null,
                UserPage.class
        );
        return response.getBody();
    }

    @Override
    public UserPage findAllByUsername(String username, String page) {
        ResponseEntity<UserPage> response = restTemplate.getRestTemplate(token).exchange(
                baseUrl + "findAllByUsername" + "?searchResult=" + username + "&page=" + page,
                HttpMethod.GET,
                null,
                UserPage.class
        );
        return response.getBody();
    }

    @Override
    public UserModel findById(Long id) {
        return restTemplate.getRestTemplate(token).getForObject(baseUrl + "findById/" + id, UserModel.class);
    }

    @Override
    public void updateUser(Long id, UserModel userModel) {
        restTemplate.getRestTemplate(token).execute(baseUrl + "update/" + id,
                HttpMethod.PUT,
                requestCallback(userModel),
                clientHttpResponse -> null);
    }

    RequestCallback requestCallback(final UserModel updatedInstance) {
        return clientHttpRequest -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(clientHttpRequest.getBody(), updatedInstance);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        };
    }

    @Override
    public UserModel findByUsernameAndStatus(String username, UserStatus status) {
        ResponseEntity<UserModel> response = restTemplate.getRestTemplate(token).exchange(
                baseUrl + "findByUsernameAndUserStatus" + "?username=" + username + "&userStatus=" + status,
                HttpMethod.GET,
                null,
                UserModel.class
        );
        return response.getBody();
    }

    @Override
    public void signIn(UserCreat userCreat) {
        token = restTemplate.getRestTemplate("")
                .postForObject("http://localhost:8080/api/auth/signin", userCreat, Token.class)
                .getToken();
    }


    @Override
    public void addUser(UserModel userModel) {
        restTemplate.getRestTemplate(token).postForEntity(baseUrl + "creat", userModel, String.class);
    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.getRestTemplate(token).delete(baseUrl + "delete/" + id);
    }
}
