package vetcare;

import vetcare.api.ApiApplication;
import vetcare.gui.App;

public class Main {

	private static final boolean ENABLE_API = false;

	public static void main(String[] args) {
		if (ENABLE_API) {
			// Inicialização da API
			ApiApplication.initApi(new String[]{});

			// Teste da API
			System.out.println("\n=== Testando listarTodosVeterinarios ===");
			var veterinarios = ApiApplication.consultas.listarTodosVeterinarios();
			System.out.println("Lista de veterinários: " + veterinarios);
		}

		// Inicialização da interface
		App.init(args);
	}
}
