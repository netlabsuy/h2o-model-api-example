package uy.com.netlabs.h2omodelapi;


import hex.genmodel.easy.exception.PredictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
class ModelController {

    @PostMapping("/score")
    ResponseEntity<Map<String, Object>> newEmployee(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> result = ModelExecutor.getInstance().score(data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (PredictException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}