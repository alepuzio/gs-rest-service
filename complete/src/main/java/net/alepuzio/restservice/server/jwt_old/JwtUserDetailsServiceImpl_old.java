package net.alepuzio.restservice.server.jwt_old;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl_old implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User_old user = new User_old();
        user.setUsername(username);
        user.setEnabled(true);
        user.setAuthorities(null);
        user.setPassword("ahahah");
        return JwtUserFactory_old.create(user);

//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
//        } else {
//            return JwtUserFactory.create(user);
//        }
    }
}