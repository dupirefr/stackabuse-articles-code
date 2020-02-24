package com.fdpro.clients.stackabuse.queuedequeinterfaces;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DequeInterfaceUnitTest {
    @Nested
    class AddingElementSection {
        @Test
        void givenEmptyDeque_whenAddThree_thenThreeInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();

            deque.add(3);

            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenEmptyDeque_whenAddFirstThree_thenThreeInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();

            deque.addFirst(3);

            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenEmptyDeque_whenOfferFirstThree_thenThreeInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();

            deque.offerFirst(3);

            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenEmptyDeque_whenPushThree_thenThreeInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();

            deque.push(3);

            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenEmptyDeque_whenAddLastThree_thenThreeInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();

            deque.addLast(3);

            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenEmptyDeque_whenOfferLastThree_thenThreeInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();

            deque.offerLast(3);

            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenNonEmptyDeque_whenAddThree_thenThreeLastInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(4);

            deque.add(3);

            assertThat(deque).containsExactly(4, 3);
        }

        @Test
        void givenNonEmptyDeque_whenAddFirstThree_thenThreeFirstInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(4);

            deque.addFirst(3);

            assertThat(deque).containsExactly(3, 4);
        }

        @Test
        void givenNonEmptyDeque_whenOfferFirstThree_thenThreeFirstInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(4);

            deque.offerFirst(3);

            assertThat(deque).containsExactly(3, 4);
        }

        @Test
        void givenNonEmptyDeque_whenPushThree_thenThreeFirstInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(4);

            deque.push(3);

            assertThat(deque).containsExactly(3, 4);
        }

        @Test
        void givenNonEmptyDeque_whenAddLastThree_thenThreeLastInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(4);

            deque.addLast(3);

            assertThat(deque).containsExactly(4, 3);
        }

        @Test
        void givenNonEmptyDeque_whenOfferLastThree_thenThreeLastInDeque() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.add(4);

            deque.offerLast(3);

            assertThat(deque).containsExactly(4, 3);
        }
    }

    @Nested
    class RetrievingElementSection {
        @Test
        void givenEmptyDeque_whenGetFirst_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThrows(NoSuchElementException.class, deque::getFirst);
        }

        @Test
        void givenEmptyDeque_whenRemoveFirst_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThrows(NoSuchElementException.class, deque::removeFirst);
        }

        @Test
        void givenEmptyDeque_whenRemove_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThrows(NoSuchElementException.class, deque::remove);
        }

        @Test
        void givenEmptyDeque_whenPop_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThrows(NoSuchElementException.class, deque::pop);
        }

        @Test
        void givenEmptyDeque_whenPollFirst_thenNull() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThat(deque.pollFirst()).isNull();
        }

        @Test
        void givenEmptyDeque_whenPoll_thenNull() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThat(deque.poll()).isNull();
        }

        @Test
        void givenEmptyDeque_whenPeekFirst_thenNull() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThat(deque.peekFirst()).isNull();
        }

        @Test
        void givenEmptyDeque_whenPeek_thenNull() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThat(deque.peek()).isNull();
        }

        @Test
        void givenEmptyDeque_whenGetLast_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThrows(NoSuchElementException.class, deque::getLast);
        }

        @Test
        void givenEmptyDeque_whenRemoveLast_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThrows(NoSuchElementException.class, deque::removeLast);
        }

        @Test
        void givenEmptyDeque_whenPeekLast_thenNull() {
            Deque<Integer> deque = new ArrayDeque<>();

            assertThat(deque.peekLast()).isNull();
        }

        @Test
        void givenNonEmptyDeque_whenGetFirst_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.getFirst()).isEqualTo(4);
            assertThat(deque).containsExactly(4, 3);
        }

        @Test
        void givenNonEmptyDeque_whenRemoveFirst_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.removeFirst()).isEqualTo(4);
            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenNonEmptyDeque_whenRemove_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.remove()).isEqualTo(4);
            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenNonEmptyDeque_whenPop_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.pop()).isEqualTo(4);
            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenNonEmptyDeque_whenPollFirst_thenNull() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.pollFirst()).isEqualTo(4);
            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenNonEmptyDeque_whenPoll_thenNull() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.poll()).isEqualTo(4);
            assertThat(deque).containsExactly(3);
        }

        @Test
        void givenNonEmptyDeque_whenPeekFirst_thenNull() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.peekFirst()).isEqualTo(4);
            assertThat(deque).containsExactly(4, 3);
        }

        @Test
        void givenNonEmptyDeque_whenPeek_thenNull() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.peek()).isEqualTo(4);
            assertThat(deque).containsExactly(4, 3);
        }

        @Test
        void givenNonEmptyDeque_whenGetLast_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.getLast()).isEqualTo(3);
            assertThat(deque).containsExactly(4, 3);
        }

        @Test
        void givenNonEmptyDeque_whenRemoveLast_thenNoSuchElementException() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.removeLast()).isEqualTo(3);
            assertThat(deque).containsExactly(4);
        }

        @Test
        void givenNonEmptyDeque_whenPeekLast_thenNull() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            assertThat(deque.peekLast()).isEqualTo(3);
            assertThat(deque).containsExactly(4, 3);
        }
    }

    @Nested
    class IteratingOverElementsSection {
        @Test
        void forEachLoop() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            List<Integer> output = new ArrayList<>();
            for (Integer element: deque) {
                output.add(element);
            }

            assertThat(output)
              .containsExactlyInAnyOrder(4, 3);
        }

        @Test
        void forEachMethod() {
            Deque<Integer> deque = new ArrayDeque<>();
            deque.push(3);
            deque.push(4);

            List<Integer> output = new ArrayList<>();
            deque.forEach(output::add);

            assertThat(output)
              .containsExactlyInAnyOrder(4, 3);
        }
    }
}
