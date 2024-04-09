package citycode.sata.util;

import citycode.sata.dto.request.AuthRequestDTOReq;
import citycode.sata.dto.response.AuthResponseDTORes;
import citycode.sata.model.CustomUser;
import citycode.sata.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginService implements ILoginService{

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private IUserRepository userRepository;

    @Override
    public AuthResponseDTORes authenticate(AuthRequestDTOReq request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        CustomUser user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var token = jwtUtils.generateToken(user);
        return AuthResponseDTORes.builder()
                .token(token)
                .build();
    }
}

