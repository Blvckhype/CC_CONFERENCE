package pl.apilia.agenda;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.apilia.agenda.model.Conference;
import pl.apilia.agenda.model.SessionType;
import pl.apilia.agenda.model.Track;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class AgendaWriter {

    private static final String LUNCH_TIME = "12:00PM LUNCH\n";
    private static final String TRACK_1 = "TRACK 1:\n";
    private static final String TRACK_2 = "TRACK 2:\n";
    private static final String NETWORKING_EVENT = "Networking event\n";
    private static final String TIME_PATTERN = "hh:mma";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);

    public String writeContentToString(List<Track> tracks) {
        StringBuilder builder = new StringBuilder();

        for (Track track : tracks) {
            if (track.getSessionType() == SessionType.MORNING_SESSION) {
                builder.append(TRACK_1);
            } else {
                builder.append(TRACK_2);
            }
            writeSession(builder, track);
        }

        return builder.toString();
    }

    private void writeSession(StringBuilder sb, Track track) {
        int nextEventStartMinutes = track.getSessionType().getStartTime();
        for (Conference conference : track.getConferences()) {
            String formattedStartTime = getStartTimeOfEvent(nextEventStartMinutes);
            nextEventStartMinutes += conference.getDuration();
            sb.append(formattedStartTime)
                    .append(" ")
                    .append(conference.getTitle())
                    .append("\n");
        }

        if (nextEventStartMinutes < 1020 && track.getSessionType() == SessionType.AFTERNOON_SESSION) {
            sb.append(getStartTimeOfEvent(nextEventStartMinutes))
                    .append(" ")
                    .append(NETWORKING_EVENT);
        } else {
            sb.append(LUNCH_TIME);
        }
    }

    private String getStartTimeOfEvent(int minOfDay) {
        return LocalTime.of((minOfDay / 60) % 24, minOfDay % 60).format(TIME_FORMATTER);
    }
}
