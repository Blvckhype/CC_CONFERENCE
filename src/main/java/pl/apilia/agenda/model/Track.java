package pl.apilia.agenda.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Track {

    private Track(SessionType sessionType) {
        this.sessionType = sessionType;
        this.remainingTime = sessionType.getDuration();
        this.conferences = new ArrayList<>();
    }

    private int remainingTime;

    private List<Conference> conferences;

    private SessionType sessionType;

    public static Track newInstance(SessionType sessionType) {
        return new Track(sessionType);
    }

    public void addConference(Conference conference) {
        this.conferences.add(conference);
        this.remainingTime = this.remainingTime - conference.getDuration();
    }
}
