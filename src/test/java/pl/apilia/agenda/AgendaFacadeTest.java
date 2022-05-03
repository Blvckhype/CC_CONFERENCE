package pl.apilia.agenda;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class AgendaFacadeTest {

    @Test
    public void SHOULD_PREPARE_AGENDA_PLAN_CONTAINING_LUNCH() {
        //given
        String fileName = "correct-input.txt";
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        AgendaFacade agendaFacade = new AgendaFacade();

        //when
        String plan = agendaFacade.getAgendaPlan(path);

        //then
        assertTrue(plan.contains("12:00PM LUNCH"));
        assertTrue(plan.contains("Networking event"));
    }
}
