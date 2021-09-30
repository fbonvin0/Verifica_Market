package it.apulia.Verifica.config;
import it.apulia.Verifica.Cliente.Role;
import it.apulia.Verifica.User.UserService;
import it.apulia.Verifica.User.Utente;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner( UserService userService) {
        return args -> {
            userService.resetAll();
            /*
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
*/

            userService.saveRole(new Role("ROLE_USER"));
            userService.saveRole(new Role("ROLE_MANAGER"));
            userService.saveRole(new Role("ROLE_ADMIN"));
            userService.saveRole(new Role("ROLE_SUPER_ADMIN"));

            userService.saveUtente(new Utente(null, "John Travolta", 22, "john","1234", new ArrayList<>()));
            userService.saveUtente(new Utente(null, "Will Smith", 26.3, "will","ciao", new ArrayList<>()));
            userService.saveUtente(new Utente(null, "Arnold Schwarzenegger", 40.1, "arnold","1234", new ArrayList<>()));


            userService.addRoleToUtente("john", "ROLE_USER");
            userService.addRoleToUtente("will", "ROLE_MANAGER");
            userService.addRoleToUtente("jim", "ROLE_ADMIN");
            userService.addRoleToUtente("arnold", "ROLE_SUPER_ADMIN");
            userService.addRoleToUtente("arnold", "ROLE_ADMIN");
            userService.addRoleToUtente("arnold", "ROLE_USER");


        };

    }
}
