package js.pekah.gooom.controller;

import js.pekah.gooom.payload.UserIdentityAvailability;
import js.pekah.gooom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/checkIdAvailability")
    public ResponseEntity<UserIdentityAvailability> checkUsernameAvailability(@RequestParam(value = "id") String id) {
        UserIdentityAvailability userIdentityAvailability = userService.checkUsernameAvailability(id);

        return new ResponseEntity< >(userIdentityAvailability, HttpStatus.OK);
    }
}
