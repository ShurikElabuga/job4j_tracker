package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int subject = 0;
        double sum = 0;
        for (Pupil pupil : pupils) {
            for (Subject unit : pupil.subjects()) {
                subject++;
                sum += unit.score();
            }
        }
        return sum / subject;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int subject = 0;
            double sum = 0;
            String name = pupil.name();
            for (Subject unit : pupil.subjects()) {
                subject++;
                sum += unit.score();
            }
            double aver = sum / subject;
            Label person = new Label(name, aver);
            list.add(person);
        }
        return list;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        Map<String, Integer> listSubject = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                listSubject.merge(subject.name(), subject.score(), (oldScore, newScore) -> oldScore + subject.score());
            }
        }
        for (Map.Entry<String, Integer> entry : listSubject.entrySet()) {
            String name = entry.getKey();
            int average = entry.getValue() / pupils.size();
            Label unit = new Label(name, average);
            list.add(unit);
        }
        return list;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> bestOfBest = new ArrayList<>();
        for (Pupil pupil : pupils) {
            String name = pupil.name();
            int sum = 0;
            for (Subject subject : pupil.subjects()) {
                sum += subject.score();
            }
            Label person = new Label(name, sum);
            bestOfBest.add(person);
        }
        double max = bestOfBest.get(0).score();
        Label result = bestOfBest.get(0);
        for (int i = 1; i < bestOfBest.size(); i++) {
            Label best = bestOfBest.get(i);
            if (best.score() > max) {
                result = bestOfBest.get(i);
            }
        }
        return result;
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        Map<String, Integer> listSubject = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                listSubject.merge(subject.name(), subject.score(), (oldScore, newScore) -> oldScore + subject.score());
            }
        }
        for (Map.Entry<String, Integer> entry : listSubject.entrySet()) {
            String name = entry.getKey();
            Integer sum = entry.getValue();
            Label bestOfSubject = new Label(name, sum);
            list.add(bestOfSubject);
        }
        double max = list.get(0).score();
        Label result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Label best = list.get(i);
            if (best.score() > max) {
                result = list.get(i);
            }
        }
        return result;
    }
}
