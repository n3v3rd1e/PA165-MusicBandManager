package cz.muni.fi.pa165.security;

import cz.fi.muni.pa165.dto.UserAuthDTO;
import cz.fi.muni.pa165.facade.ManagerFacade;
import cz.fi.muni.pa165.facade.MemberFacade;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Iurii xkuznetc Kuznetcov
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private ManagerFacade managerFacade;

    @Inject
    private MemberFacade memberFacade;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserAuthDTO fakeAuthDTO = new UserAuthDTO();
        fakeAuthDTO.setEmail(email);
        fakeAuthDTO.setPassword(password);

        boolean memberAuthenticatedSuccefully = memberFacade.authenticate(fakeAuthDTO);
        boolean managerAuthenticatedSuccefully = managerFacade.authenticate(fakeAuthDTO);

        if (managerAuthenticatedSuccefully) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + "MANAGER"));
            return new UsernamePasswordAuthenticationToken(email, password, grantedAuths);
        } else if (memberAuthenticatedSuccefully) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + "MEMBER"));
            return new UsernamePasswordAuthenticationToken(email, password, grantedAuths);
        } else {
            throw new BadCredentialsException("Invalid credentials.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
