import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque61BTest {
    @Test
    public void LinkedListDequeIterTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        for (int i : deque) {
            System.out.println(i);
        }
    }

    @Test
    public void LinkedListDequeIsEqualTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        Deque61B<Integer> other = new LinkedListDeque61B<>();
        other.addFirst(1);
        other.addLast(2);
        other.addFirst(3);
        other.addLast(4);

        assertThat(deque).isEqualTo(other);

        other.removeFirst();

        assertThat(deque).isNotEqualTo(other);
    }

    @Test
    public void LinkedListDequeToStringTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        System.out.println(deque);
    }
}
