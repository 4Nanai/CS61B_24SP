import deque.Deque61B;
import edu.princeton.cs.algs4.StdAudio;
import gh2.GuitarString;
import org.junit.jupiter.api.Test;

public class TestPlayHaruhikage {
    private static final int QUARTER = 30000;
    private static final int EIGHTH = 15000;
    private static final int SIXTEENTH = 7500;

    @Test
    public void testPlayHaruhikage() {
        GuitarString E5 = new GuitarString(659.26);
        GuitarString D5 = new GuitarString(587);
        GuitarString C5 = new GuitarString(523.25);
        GuitarString F5 = new GuitarString(698.46);

        tic(E5, QUARTER);
        tic(D5, EIGHTH);
        tic(C5, QUARTER);
        tic(D5, EIGHTH);
        tic(E5, EIGHTH + EIGHTH / 2);
        tic(F5, SIXTEENTH);
        tic(E5, EIGHTH);
        tic(D5, QUARTER + QUARTER / 2);

        tic(E5, QUARTER);
        tic(D5, EIGHTH);
        tic(C5, QUARTER);
        tic(D5, EIGHTH);
        tic(E5, EIGHTH + EIGHTH / 2);
        tic(F5, SIXTEENTH);
        tic(E5, EIGHTH);
        tic(D5, QUARTER + QUARTER / 2);
    }

    public void tic(GuitarString play, int time) {
        play.pluck();
        for (int i = 0; i < time; i++) {
            StdAudio.play(play.sample());
            play.tic();
        }
        play.pluck();
    }
}
