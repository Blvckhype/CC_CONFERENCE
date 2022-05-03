package pl.apilia.agenda;

import org.junit.Test;
import pl.apilia.agenda.model.Conference;
import pl.apilia.agenda.model.Track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AgendaSchedulerTest {

    @Test
    public void SHOULD_SCHEDULE_CONFERENCES_INTO_TRACKS() {
        //given
        List<Conference> conferences = Arrays.asList(
                Conference.builder()
                        .title("Java for dummies")
                        .duration(60)
                        .planned(false)
                        .build(),
                Conference.builder()
                        .title("Python for dummies")
                        .duration(60)
                        .planned(false)
                        .build(),
                Conference.builder()
                        .title("C++ for dummies")
                        .duration(60)
                        .planned(false)
                        .build(),
                Conference.builder()
                        .title("JS for dummies")
                        .duration(60)
                        .planned(false)
                        .build(),
                Conference.builder()
                        .title("Java for dummies")
                        .duration(30)
                        .planned(false)
                        .build(),
                Conference.builder()
                        .title("CSS for dummies")
                        .duration(5)
                        .planned(false)
                        .build());

        AgendaScheduler scheduler = new AgendaScheduler();

        //when
        List<Track> tracks = scheduler.schedule(conferences);

        //then
        assertEquals(4, tracks.size());
        assertEquals(0, tracks.get(0).getRemainingTime());
        assertEquals(3, tracks.get(0).getConferences().size());
        assertEquals(3, tracks.get(1).getConferences().size());
        assertEquals(145, tracks.get(1).getRemainingTime());
        assertEquals(0, tracks.get(2).getConferences().size());
        assertEquals(180, tracks.get(2).getRemainingTime());
        assertEquals(0, tracks.get(3).getConferences().size());
        assertEquals(240, tracks.get(3).getRemainingTime());

    }

    @Test
    public void SHOULD_PREPARE_FOUR_EMPTY_TRACKS_FOR_EMPTY_CONFERENCE_LIST() {
        //given
        List<Conference> conferences = new ArrayList<>();
        AgendaScheduler scheduler = new AgendaScheduler();

        //when
        List<Track> tracks = scheduler.schedule(conferences);

        //then
        assertEquals(4, tracks.size());
        tracks.forEach(track -> assertEquals(0, track.getConferences().size()));
    }
}
