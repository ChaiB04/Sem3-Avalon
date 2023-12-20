package individual.individualsem3backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
@EnableJpaRepositories
public class IndividualSem3BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndividualSem3BackendApplication.class, args);
    }

}
