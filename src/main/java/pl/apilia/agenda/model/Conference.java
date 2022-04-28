package pl.apilia.agenda.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Conference {

    private String title;

    private int duration;

    private boolean planned;

    public void planned() {
        this.planned = true;
    }
}
