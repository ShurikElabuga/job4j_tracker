package ru.job4j.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class ReconstructPhrase {
    private final Deque<Character> descendingElements;

    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder rsl = new StringBuilder();
        int i = 0;
        for (Character ch : evenElements) {
            if (i % 2 == 0) {
                rsl.append(ch);
            }
            i++;
        }
        return String.valueOf(rsl);
    }

    private String getDescendingElements() {
        StringBuilder rsl = new StringBuilder();
        int number = descendingElements.size();
        for (int i = 0; i < number; i++) {
            rsl.append(descendingElements.pollLast());
        }
        return String.valueOf(rsl);
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}
