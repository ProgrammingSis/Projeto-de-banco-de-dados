package vetcare.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import vetcare.api.controller.ConsultasController;
import vetcare.api.controller.PacientesController;
import vetcare.api.service.AtendimentoService;
import vetcare.api.service.ClienteService;

@SpringBootApplication
public class ApiApplication {
	public static ConsultasController consultas;
	public static PacientesController pacientes;
	public static ClienteService clientes;
	public static AtendimentoService atendimentos;

	public static void initApi(String[] args) {
		// Inicializar o contexto Spring
		ApplicationContext context = SpringApplication.run(ApiApplication.class, args);

		// Obter o bean PacientesController
		consultas = context.getBean(ConsultasController.class);
		pacientes = context.getBean(PacientesController.class);
		clientes = context.getBean(ClienteService.class);
		atendimentos = context.getBean(AtendimentoService.class);


		//String s = consultas.notificarConsulta();
		//System.out.println(s);
	}
}
