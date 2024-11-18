package com.Pfe.Comparateur.Controller;


import com.Pfe.Comparateur.Repository.UserAlertRepository;
import com.Pfe.Comparateur.Service.UserAlertService;
import com.Pfe.Comparateur.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Alert")
public class UserAlertController {
    @Autowired
    UserAlertService userAlertService;
    @Autowired
    UserService userService;
    @Autowired
    UserAlertRepository userAlertRepository;

@GetMapping("/send-budget/{id}/{_id}/{budget}")
public ResponseEntity<?> sendBudget(@PathVariable String id,@PathVariable String _id, @PathVariable Double budget) throws ParseException {

    //userService.checkPriceAndSendAlerts();
    return userAlertService.sendBudget(id,_id,budget);
}


}

