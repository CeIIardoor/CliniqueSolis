package info.cellardoor.CliniqueSolis.Auth.Controller;


import info.cellardoor.CliniqueSolis.App.Http.Middleware.AuthenticationService;
import info.cellardoor.CliniqueSolis.App.Http.Request.AuthenticationRequest;
import info.cellardoor.CliniqueSolis.App.Http.Request.RegisterRequest;
import info.cellardoor.CliniqueSolis.App.Http.Response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest req
    ) {
        return ResponseEntity.ok(authenticationService.register(req));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest req
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(req));

    }
}