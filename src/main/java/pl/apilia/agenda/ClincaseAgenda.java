package pl.apilia.agenda;

public class ClincaseAgenda {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Invalid input argument amount");
        }
        AgendaFacade agendaFacade = new AgendaFacade();
        String plan = agendaFacade.getAgendaPlan(args[0]);

        System.out.println(plan);
    }
}
