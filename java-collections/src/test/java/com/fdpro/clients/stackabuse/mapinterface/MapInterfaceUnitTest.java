package com.fdpro.clients.stackabuse.mapinterface;

import org.assertj.core.data.MapEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

class MapInterfaceUnitTest {
    private Map<String, Integer> wordsCount;

    @BeforeEach
    void beforeEach() {
        wordsCount = new HashMap<>();
    }

    @Nested
    class AddingElementsSection {
        @Test
        void givenNonExistingKey_whenPut_thenNewValueAndNothingReturned() {
            assertThat(wordsCount.put("the", 153)).isNull();
            assertThat(wordsCount.get("the")).isEqualTo(153);
        }

        @Test
        void givenExistingKey_whenPut_thenNewValueAndPreviousValueReturned() {
            wordsCount.put("the", 152);

            assertThat(wordsCount.put("the", 153)).isEqualTo(152);
            assertThat(wordsCount.get("the")).isEqualTo(153);
        }

        @Test
        void givenNonExistingKey_whenPutIfAbsent_thenNewValueAndNothingReturned() {
            assertThat(wordsCount.putIfAbsent("the", 153)).isNull();
            assertThat(wordsCount.get("the")).isEqualTo(153);
        }

        @Test
        void givenExistingKey_whenPutIfAbsent_thenPreviousValueAndPreviousValueReturned() {
            wordsCount.put("the", 152);

            assertThat(wordsCount.putIfAbsent("the", 153)).isEqualTo(152);
            assertThat(wordsCount.get("the")).isEqualTo(152);
        }

        @Test
        void givenNonExistingKey_whenComputeIfAbsent_thenNewValueAndNothingReturnedAndInitialized() {
            AtomicBoolean initialized = new AtomicBoolean(false);

            assertThat(wordsCount.computeIfAbsent("the", key -> {
                initialized.set(true);
                return 3 + 150;
            })).isEqualTo(153);
            assertThat(wordsCount.get("the")).isEqualTo(153);
            assertThat(initialized).isTrue();
        }

        @Test
        void givenExistingKey_whenComputeIfAbsent_thenPreviousValueAndPreviousValueReturnedAndNotInitialized() {
            AtomicBoolean initialized = new AtomicBoolean(false);
            wordsCount.put("the", 152);

            assertThat(wordsCount.computeIfAbsent("the", key -> {
                initialized.set(true);
                return 3 + 150;
            })).isEqualTo(152);
            assertThat(wordsCount.get("the")).isEqualTo(152);
            assertThat(initialized).isFalse();
        }
    }

    @Nested
    class RetrievingElementsSection {
        @Test
        void givenExistingKey_whenGet_thenValue() {
            wordsCount.put("the", 152);

            assertThat(wordsCount.get("the")).isEqualTo(152);
        }

        @Test
        void givenNonExistingKey_whenGet_thenNull() {
            assertThat(wordsCount.get("the")).isNull();
        }

        @Test
        void givenExistingKey_whenGetOrDefault_thenValue() {
            wordsCount.put("the", 152);

            assertThat(wordsCount.getOrDefault("the", 0)).isEqualTo(152);
        }

        @Test
        void givenNonExistingKey_whenGetOrDefault_thenValue() {
            assertThat(wordsCount.getOrDefault("the", 0)).isEqualTo(0);
        }

        @Test
        void givenMap_whenEntrySet_thenContainedEntries() {
            wordsCount.put("the", 153);
            wordsCount.put("duck", 2);

            assertThat(wordsCount.entrySet())
              .containsExactly(MapEntry.entry("the", 153), MapEntry.entry("duck", 2));
        }

        @Test
        void givenMap_whenKeySet_thenContainedKeys() {
            wordsCount.put("the", 153);
            wordsCount.put("duck", 2);

            assertThat(wordsCount.keySet())
              .containsExactly("the", "duck");
        }

        @Test
        void givenMap_whenValues_thenContainedValues() {
            wordsCount.put("the", 153);
            wordsCount.put("duck", 2);

            assertThat(wordsCount.values())
              .containsExactly(153, 2);
        }
    }

    @Nested
    class RemovingElementsSection {
        @Test
        void givenExistingKey_whenRemoveKey_thenValue() {
            wordsCount.put("the", 153);

            assertThat(wordsCount.remove("the")).isEqualTo(153);
        }

        @Test
        void givenNonExistingKey_whenRemoveKey_thenNull() {
            assertThat(wordsCount.remove("the")).isNull();
        }

        @Test
        void givenExistingKeyAndMatchingValue_whenRemoveKeyValue_thenTrue() {
            wordsCount.put("the", 153);

            assertThat(wordsCount.remove("the", 153)).isTrue();
        }

        @Test
        void givenExistingKeyAndNoMatchingValue_whenRemoveKeyValue_thenFalse() {
            wordsCount.put("the", 153);

            assertThat(wordsCount.remove("the", 152)).isFalse();
        }

        @Test
        void givenNonExistingKey_whenRemoveKeyValue_thenFalse() {
            assertThat(wordsCount.remove("the", 152)).isFalse();
        }
    }

    @Nested
    class IteratingOverElementsSection {
        @Test
        void forEachLoop() {
            wordsCount.put("the", 153);
            wordsCount.put("duck", 2);

            List<String> output = new ArrayList<>();
            for (Map.Entry<String, Integer> wordCount: wordsCount.entrySet()) {
                output.add(wordCount.getKey() + " appears " + wordCount.getValue() + " times");
            }

            assertThat(output)
              .containsExactlyInAnyOrder("the appears 153 times", "duck appears 2 times");
        }

        @Test
        void forEachMethod() {
            wordsCount.put("the", 153);
            wordsCount.put("duck", 2);

            List<String> output = new ArrayList<>();
            wordsCount.forEach((word, count) -> output.add(word + " appears " + count + " times"));

            assertThat(output)
              .containsExactlyInAnyOrder("the appears 153 times", "duck appears 2 times");
        }
    }

    @Nested
    class CheckingForAnElementPresenceSection {
        @Test
        void givenNonExistingKey_whenContainsKey_thenFalse() {
            assertThat(wordsCount.containsKey("the")).isFalse();
        }

        @Test
        void givenExistingKey_whenContainsKey_thenTrue() {
            wordsCount.put("the", 153);

            assertThat(wordsCount.containsKey("the")).isTrue();
        }

        @Test
        void givenNonExistingValue_whenContainsValue_thenFalse() {
            assertThat(wordsCount.containsValue(153)).isFalse();
        }

        @Test
        void givenExistingValue_whenContainsValue_thenTrue() {
            wordsCount.put("the", 153);

            assertThat(wordsCount.containsValue(153)).isTrue();
        }
    }

    @Nested
    class RetrievingSizeAndCheckingForEmptynessSection {
        @Test
        void givenEmptyMap_whenSize_thenZero() {
            assertThat(wordsCount.size()).isEqualTo(0);
        }

        @Test
        void givenOneElementMap_whenSize_thenOne() {
            wordsCount.put("the", 153);

            assertThat(wordsCount.size()).isEqualTo(1);
        }

        @Test
        void givenEmptyMap_whenIsEmpty_thenTrue() {
            assertThat(wordsCount.isEmpty()).isTrue();
        }

        @Test
        void givenOneElementMap_whenIsEmpty_thenFalse() {
            wordsCount.put("the", 153);

            assertThat(wordsCount.isEmpty()).isFalse();
        }
    }

    @Nested
    class InterfacesSections {
        @Test
        void sortedMapFirstKey() {
            SortedMap<String, Integer> wordsCount = new TreeMap<>();
            wordsCount.put("the", 150);
            wordsCount.put("ball", 4);
            wordsCount.put("duck", 2);

            assertThat(wordsCount.firstKey()).isEqualTo("ball");
        }

        @Test
        void sortedMapLastKey() {
            SortedMap<String, Integer> wordsCount = new TreeMap<>();
            wordsCount.put("the", 150);
            wordsCount.put("ball", 4);
            wordsCount.put("duck", 2);

            assertThat(wordsCount.lastKey()).isEqualTo("the");
        }

        @Test
        void navigableMapLowerEntry() {
            NavigableMap<String, Integer> wordsCount = new TreeMap<>();
            wordsCount.put("the", 150);
            wordsCount.put("ball", 4);
            wordsCount.put("duck", 2);

            assertThat(wordsCount.lowerEntry("duck")).isEqualTo(MapEntry.entry("ball", 4));
        }
    }
}
