package vet.care.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DashboardController {

    private final ConsultasController consultasController;
    private final EstoqueController estoqueController;
    private final FinanceiroController financeiroController;
    private final PacientesController pacientesController;
}
