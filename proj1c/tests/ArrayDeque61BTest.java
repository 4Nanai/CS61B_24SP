import deque.ArrayDeque61B;
import deque.Deque61B;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {
    @Test
    public void ArrayDeque61BIterTest() {
        Deque61B<Integer> array = new ArrayDeque61B<>();
        array.addFirst(1);
        array.addLast(2);
        array.addFirst(3);
        array.addLast(4);

        /* 3, 1, 2, 4 */
        for (int i: array) {
            System.out.println(i);
        }
    }

    @Test
    public void ArrayDeque61BIsEqualTest() {
        Deque61B<Integer> array = new ArrayDeque61B<>();
        array.addFirst(1);
        array.addLast(2);
        array.addFirst(3);
        array.addLast(4);

        Deque61B<Integer> other = new ArrayDeque61B<>();
        other.addFirst(1);
        other.addLast(2);
        other.addFirst(3);
        other.addLast(4);

        assertThat(array).isEqualTo(other);

        other.removeFirst();

        assertThat(array).isNotEqualTo(other);
    }

    @Test
    public void ArrayDeque61BToStringTest() {
        Deque61B<Integer> array = new ArrayDeque61B<>();
        array.addFirst(1);
        array.addLast(2);
        array.addFirst(3);
        array.addLast(4);

        System.out.println(array);
    }
}
