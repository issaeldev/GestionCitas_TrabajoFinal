package com.gestioncitas_trabajofinal.strategy;
import com.gestioncitas_trabajofinal.view.medico.HomeMedico;
import com.gestioncitas_trabajofinal.model.Medico;

public class MedicoLoginStrategy implements LoginStrategy<Medico> 
{
    @Override
    public void login(Medico medico) 
    {
        new HomeMedico(medico).setVisible(true);
    }
}
