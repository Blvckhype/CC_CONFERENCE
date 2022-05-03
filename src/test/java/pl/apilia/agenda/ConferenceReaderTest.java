package pl.apilia.agenda;

import org.junit.Test;
import pl.apilia.agenda.model.Conference;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ConferenceReaderTest {

    @Test
    public void SHOULD_READ_CONFERENCE() {
        //given
        String fileName = "correct-input.txt";
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        ConferenceReader reader = new ConferenceReader();

        //when
        List<Conference> conferences = reader.readFromFile(path);

        //then
        assertTrue(conferences.size() > 0);
    }

    @Test
    public void SHOULD_THROW_EXCEPTION_WHEN_CONFERENCE_AMOUNT_IS_MISSING() {
        //given
        String fileName = "missing-conference-amount-input.txt";
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        ConferenceReader reader = new ConferenceReader();

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> reader.readFromFile(path));

        //then
        assertTrue(exception.getMessage().contains("File should contains conference amount in 1st line"));
    }

    @Test
    public void SHOULD_THROW_EXCEPTION_FOR_INVALID_FILE() {
        //given
        String fileName = "too-many-conferences-input.txt";
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        ConferenceReader reader = new ConferenceReader();

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> reader.readFromFile(path));

        //then
        assertTrue(exception.getMessage().contains("Conference amount should be between 1 and 20"));
    }
}
