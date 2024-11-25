package vetcare;

import vetcare.api.ApiApplication;
import vetcare.gui.VetCareApp;

public class Main {

	private static final boolean ENABLE_API = true;

	public static void main(String[] args) {
		if (ENABLE_API) {
			// Inicialização da API
			ApiApplication.initApi(new String[]{});
		}

		// Inicialização da interface
		VetCareApp.init(args);
	}
}
