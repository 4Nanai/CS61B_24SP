import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
    public void testAddFirstBasic() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        assertThat(deque.toList()).containsExactly(3, 2, 1);
    }

    @Test
    public void testAddLastBasic() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertThat(deque.toList()).containsExactly(1, 2, 3);
    }

    @Test
    public void testAddFirstAndLastBasic() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);

        assertThat(deque.toList()).containsExactly(4, 1, 2, 3);
    }

    @Test
    public void testValidGet() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);

        assertThat(deque.get(2)).isEqualTo(2);
    }

    @Test
    public void testInvalidGet() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);

        assertThat(deque.get(100)).isEqualTo(null);
    }

    @Test
    public void testIsNotEmpty() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(1);
        deque.addLast(2);

        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void testIsEmpty() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    public void testSize() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);
        deque.addLast(5);

        assertThat(deque.size()).isEqualTo(5);
    }

    @Test
    public void testRemoveFirstBasic() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);

        assertThat(deque.removeFirst()).isEqualTo(1);
        assertThat(deque.toList()).containsExactly(2, 3, 4);
    }

    @Test
    public void testRemoveLastBasic() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);

        assertThat(deque.removeLast()).isEqualTo(6);
        assertThat(deque.toList()).containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    public void testAddFirstMoreThanCapacity() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addFirst(9);
        deque.addLast(10);
        deque.addLast(11);
        deque.addLast(12);

        assertThat(deque.toList()).containsExactly(9, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12);
    }

    @Test
    public void testAddLastMoreThanCapacity() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addFirst(10);
        deque.addLast(11);
        deque.addLast(12);

        assertThat(deque.toList()).containsExactly(10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12);
    }

    @Test
    public void testRemoveFirstMoreThanLimit() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 16; i++) {
            assertThat(deque.removeFirst()).isEqualTo(i);
        }
        assertThat(deque.toList()).containsExactly(16, 17, 18, 19);
    }

    @Test
    public void testRemoveLastMoreThanLimit() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 16; i++) {
            assertThat(deque.removeLast()).isEqualTo(19 - i);
        }
        assertThat(deque.toList()).containsExactly(0, 1, 2, 3);
    }
}
