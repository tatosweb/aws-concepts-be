package co.com.aws.training.awstrainingbe.component.poll.io.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1", produces = "application/json;charset=UTF-8")
public class HealthCheckController {
    @GetMapping(value="/check")
    public ResponseEntity<String> validate(){
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
