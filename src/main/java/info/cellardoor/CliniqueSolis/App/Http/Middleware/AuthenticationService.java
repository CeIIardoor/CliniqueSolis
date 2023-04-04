package info.cellardoor.CliniqueSolis.App.Http.Middleware;


import info.cellardoor.CliniqueSolis.App.Http.Request.AuthenticationRequest;
import info.cellardoor.CliniqueSolis.App.Http.Request.RegisterRequest;
import info.cellardoor.CliniqueSolis.App.Http.Response.AuthenticationResponse;
import info.cellardoor.CliniqueSolis.App.Service.JwtService;
import info.cellardoor.CliniqueSolis.Auth.Models.AppUser;
import info.cellardoor.CliniqueSolis.Auth.Models.Role;
import info.cellardoor.CliniqueSolis.Auth.Models.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest req) {
        var user = AppUser.builder()
                .email(req.getEmail())
                .mdp(passwordEncoder.encode(req.getPassword()))
                .nom(req.getNom())
                .prenom(req.getPrenom())
                .role(Role.ROLE_UTILISATEUR)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        ); //user is authenticated starting from here on
        var user = userRepository.findByEmail(req.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}