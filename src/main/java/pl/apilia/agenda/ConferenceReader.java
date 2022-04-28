package pl.apilia.agenda;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.apilia.agenda.model.Conference;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ConferenceReader {
    private static final String FIVE_MINUTES_DURATION_CONFERENCE_LABEL = "lightning";
    private static final Pattern LIGHTNING_CONFERENCE_NAME_PATTERN = Pattern.compile("^[a-zA-Z].*" + FIVE_MINUTES_DURATION_CONFERENCE_LABEL + "$");
    private static final Pattern REGULAR_CONFERENCE_NAME_PATTERN = Pattern.compile("^[a-zA-Z].*\\d\\dmin");


    public List<Conference> readFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int conferenceAmount = Integer.parseInt(reader.readLine());

            if (conferenceAmount < 1 || conferenceAmount > 20) {
                throw new IllegalStateException("Conference amount should be between 1 and 20");
            }

            return readConferencesFromFile(reader, conferenceAmount);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }
    }

    private List<Conference> readConferencesFromFile(BufferedReader br, int conferenceAmount) throws IOException {
        int agendaDuration = 840;
        List<Conference> conferences = new ArrayList<>();
        for (int i = 0; i < conferenceAmount; i++) {
            String name = br.readLine().toLowerCase(Locale.ROOT);
            if (isConferenceNameValid(name)) {
                int duration = extractDuration(name);
                agendaDuration -=  duration;
                if (agendaDuration <= 0) {
                    throw new IllegalStateException("Agenda has only 840 minutes. Invalid input");
                }
                conferences.add(Conference.builder()
                        .title(name)
                        .duration(duration)
                        .planned(false)
                        .build());
            }
        }
        return conferences;
    }

    private boolean isConferenceNameValid(String name) {
        return LIGHTNING_CONFERENCE_NAME_PATTERN.matcher(name).matches() || REGULAR_CONFERENCE_NAME_PATTERN.matcher(name).matches();
    }

    private int extractDuration(String name) {
        if (LIGHTNING_CONFERENCE_NAME_PATTERN.matcher(name).matches()) {
            return 5;
        }
        String duration = name.replaceAll("[a-zA-Z].*[^\\dmin]", "")
                .replaceAll("\\D", "")
                .trim();
        return Integer.parseInt(duration);
    }

//    private String extractTitle(String name) {
//        return name.replaceAll(FIVE_MINUTES_DURATION_CONFERENCE_LABEL, "")
//                .replaceAll("[0-9]{2}min", "")
//                .trim();
//    }
}
