package com.gestioncitas_trabajofinal.strategy;
import com.gestioncitas_trabajofinal.view.paciente.HomePaciente;
import com.gestioncitas_trabajofinal.model.Paciente;

public class PacienteLoginStrategy implements LoginStrategy<Paciente> 
{
    @Override
    public void login(Paciente paciente) 
    {
        new HomePaciente(paciente).setVisible(true);
    }
}
