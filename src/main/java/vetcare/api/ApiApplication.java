package vetcare.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import vetcare.api.controller.ConsultasController;

@SpringBootApplication
public class ApiApplication {
	public static ConsultasController consultas;

	public static void initApi(String[] args) {
		// Inicializar o contexto Spring
		ApplicationContext context = SpringApplication.run(ApiApplication.class, args);

		// Obter o bean PacientesController
		consultas = context.getBean(ConsultasController.class);
	}
}
