package pack.Component;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class SscStatusAuthenticationProvider implements AuthenticationProvider {


    private final Userr s;

    public SscStatusAuthenticationProvider(String username, String password) {
        this.s = new Userr(username,password);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> authorityList = new ArrayList<>();

        if (this.s.match(name,password))
        {
            return new UsernamePasswordAuthenticationToken(
                    name, password,authorityList);
        }
        else return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private static class Userr {
        private String name;
        private String password;

        public Userr(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public boolean match(String name, String password) {
            return this.name.equals(name) && this.password.equals(password);
        }
    }
}
