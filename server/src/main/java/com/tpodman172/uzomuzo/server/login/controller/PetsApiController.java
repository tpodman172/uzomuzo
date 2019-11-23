package com.tpodman172.uzomuzo.server.login.controller;

import com.tpodman172.uzomuzo.server.login.appService.model.Pet;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-23T21:04:26.392Z[GMT]")

@Controller
@RequestMapping("${openapi.swaggerPetstore.base-path:/v1}")
public class PetsApiController implements PetsApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PetsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<Pet>> listPets(@Valid Integer limit) {
        List<Pet> petList = LongStream.range(1, 10).mapToObj(l -> {
            Pet pet = new Pet();
            pet.setId(l);
            pet.setName("aaaa");
            pet.setTag("dog");
            return pet;
        }).collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8081");
        //headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "X-SPECIAL-TOKEN");
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");//上だとローカルで開くと怒られる
        ResponseEntity responseEntity = new ResponseEntity(petList, headers, HttpStatus.OK);
        return responseEntity;
    }
}
