package vetcare.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import vetcare.api.controller.ConsultasController;

@SpringBootApplication
public class ApiApplication {
	/*public static void main(String[] args) {

		// Inicializar o contexto Spring
		ApplicationContext context = SpringApplication.run(ApiApplication.class, args);

		// Obter o bean PacientesController
		ConsultasController consultasController = context.getBean(ConsultasController.class);


		System.out.println("\n=== Testando listarTodosVeterinarios ===");
		var veterinarios = consultasController.listarTodosVeterinarios();
		System.out.println("Lista de veterinários: " + veterinarios);

	}*/

	public static ConsultasController cc;

	public static void initApi(String[] args) {
		// Inicializar o contexto Spring
		ApplicationContext context = SpringApplication.run(ApiApplication.class, args);

		// Obter o bean PacientesController
		ConsultasController consultasController = context.getBean(ConsultasController.class);

		System.out.println("\n=== Testando listarTodosVeterinarios ===");
		var veterinarios = consultasController.listarTodosVeterinarios();
		System.out.println("Lista de veterinários: " + veterinarios);

		cc = consultasController;
	}
}