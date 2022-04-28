package pl.apilia.agenda.model;

public enum SessionType {
    MORNING_SESSION(180, 540),
    AFTERNOON_SESSION(240, 780);

    private final int duration;

    private final int startTime;

    SessionType(int duration, int startTime) {
        this.duration = duration;
        this.startTime = startTime;
    }

    public int getDuration(){
        return this.duration;
    }

    public int getStartTime(){
        return this.startTime;
    }
}
