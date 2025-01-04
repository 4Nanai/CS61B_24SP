import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    public static class StringFirstLetterComparator implements Comparator<String> {
        public int compare(String a, String b) {
            if (a == "") {
                return -1;
            }
            if (b == "") {
                return 1;
            }
            return a.charAt(0) - b.charAt(0);
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
        assertThat(mad.max(new StringFirstLetterComparator())).isEqualTo("fury road");

    }
}
