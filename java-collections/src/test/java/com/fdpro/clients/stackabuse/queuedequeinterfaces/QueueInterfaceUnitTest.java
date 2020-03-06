package com.fdpro.clients.stackabuse.queuedequeinterfaces;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueInterfaceUnitTest {
    @Nested
    class AddingElementSection {
        @Test
        void givenEmptyQueue_whenAddThree_thenThreeInQueue() {
            Queue<Integer> queue = new ArrayDeque<>();

            queue.add(3);

            assertThat(queue).containsExactly(3);
        }

        @Test
        void givenEmptyQueue_whenOfferFour_thenFourInQueue() {
            Queue<Integer> queue = new ArrayDeque<>();

            queue.offer(4);

            assertThat(queue).containsExactly(4);
        }

        @Test
        void givenBoundedQueue_whenAddTwoTimes_thenException() {
            Queue<Integer> queue = new LinkedBlockingQueue<>(1);
            queue.add(3);

            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> queue.add(4));
            exception.printStackTrace();
        }

        @Test
        void givenBoundedQueue_whenOfferTwoTimes_thenFalse() {
            Queue<Integer> queue = new LinkedBlockingQueue<>(1);
            queue.offer(3);

            assertThat(queue.offer(4)).isFalse();
        }
    }

    @Nested
    class RetrievingElementSection {
        @Test
        void givenTwoElementsQueue_whenPollAndPeek_thenOneElementRemains() {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(3);
            queue.offer(4);

            assertThat(queue.poll()).isEqualTo(3);
            assertThat(queue).containsExactly(4);
            assertThat(queue.peek()).isEqualTo(4);
            assertThat(queue).containsExactly(4);
        }

        @Test
        void givenEmptyQueue_whenRemove_thenException() {
            Queue<Integer> queue = new ArrayDeque<>();

            assertThrows(NoSuchElementException.class, queue::remove);
        }

        @Test
        void givenEmptyQueue_whenElement_thenException() {
            Queue<Integer> queue = new ArrayDeque<>();

            assertThrows(NoSuchElementException.class, queue::element);
        }
    }

    @Nested
    class IteratingOverElementsSection {
        @Test
        void forEachLoop() {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(3);
            queue.offer(4);

            List<Integer> output = new ArrayList<>();
            for (Integer element: queue) {
                output.add(element);
            }

            assertThat(output)
              .containsExactlyInAnyOrder(3, 4);
        }

        @Test
        void forEachMethod() {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(3);
            queue.offer(4);

            List<Integer> output = new ArrayList<>();
            queue.forEach(output::add);

            assertThat(output)
              .containsExactlyInAnyOrder(3, 4);
        }
    }

    @Nested
    class ImplementationsSection {
        @Test
        void givenMoreSevereAnomalyAddedLater_whenPoll_thenMoreSevereAnomaly() {
            Queue<Anomaly> anomalies = new PriorityQueue<>();

            Anomaly optionalInformationNotRetrievedAnomaly = new Anomaly("Couldn't retrieve optional information", Anomaly.Severity.LOW);
            anomalies.offer(optionalInformationNotRetrievedAnomaly);

            Anomaly databaseNotReachableAnomaly = new Anomaly("Couldn't contact database", Anomaly.Severity.HIGH);
            anomalies.offer(databaseNotReachableAnomaly);

            assertThat(anomalies.poll()).isEqualTo(databaseNotReachableAnomaly);
        }
        @Test
        void givenMoreSevereAnomalyAddedLaterAndReversedNaturalOrder_whenPoll_thenLessSevereAnomaly() {
            Queue<Anomaly> anomalies = new PriorityQueue<>(Comparator.reverseOrder());

            Anomaly optionalInformationNotRetrievedAnomaly = new Anomaly("Couldn't retrieve optional information", Anomaly.Severity.LOW);
            anomalies.offer(optionalInformationNotRetrievedAnomaly);

            Anomaly databaseNotReachableAnomaly = new Anomaly("Couldn't contact database", Anomaly.Severity.HIGH);
            anomalies.offer(databaseNotReachableAnomaly);

            assertThat(anomalies.poll()).isEqualTo(optionalInformationNotRetrievedAnomaly);
        }
    }

    private static class Anomaly implements Comparable<Anomaly> {
        private String log;
        private Severity severity;

        public Anomaly(String log, Severity severity) {
            this.log = log;
            this.severity = severity;
        }

        @Override
        public int compareTo(Anomaly o) {
            return severity.compareTo(o.severity);
        }

        private enum Severity {
            HIGH,
            MEDIUM,
            LOW
        }
    }
}
