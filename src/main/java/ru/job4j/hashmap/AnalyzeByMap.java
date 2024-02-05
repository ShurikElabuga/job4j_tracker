package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int sbjts = 0;
        double scr = 0;
        for (Pupil pup : pupils) {
            for (Subject sbj : pup.subjects()) {
                sbjts++;
                scr += sbj.score();
            }
        }
        return scr / sbjts;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        for (Pupil pup : pupils) {
            int sbjts = 0;
            double scr = 0;
            String name = pup.name();
            for (Subject sbj : pup.subjects()) {
                sbjts++;
                scr += sbj.score();
            }
            double aver = scr / sbjts;
            Label pupil = new Label(name, aver);
            list.add(pupil);
        }
        return list;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        Map<String, Integer> listSubj = new LinkedHashMap<>();
        int pupil = 0;
        for (Pupil pup : pupils) {
            pupil++;
            for (Subject sbj : pup.subjects()) {
                if (!listSubj.containsKey(sbj.name())) {
                    listSubj.put(sbj.name(), sbj.score());
                } else {
                    listSubj.put(sbj.name(), listSubj.get(sbj.name()) + sbj.score());
                }
            }
        }
        for (Map.Entry<String, Integer> entry : listSubj.entrySet()) {
            String name = entry.getKey();
            Integer average = entry.getValue() / pupil;
            Label sjt = new Label(name, average);
            list.add(sjt);
        }
        return list;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> bst = new ArrayList<>();
        for (Pupil psp : pupils) {
            String name = psp.name();
            int sum = 0;
            for (Subject sbj : psp.subjects()) {
                sum += sbj.score();
            }
            Label pupil = new Label(name, sum);
            bst.add(pupil);
        }
            bst.sort(Comparator.naturalOrder());
            Label best = bst.get(bst.size() - 1);
        return best;
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        Map<String, Integer> listSubj = new LinkedHashMap<>();
        for (Pupil pup : pupils) {
            for (Subject sbj : pup.subjects()) {
                if (!listSubj.containsKey(sbj.name())) {
                    listSubj.put(sbj.name(), sbj.score());
                } else {
                    listSubj.put(sbj.name(), listSubj.get(sbj.name()) + sbj.score());
                }
            }
        }
        for (Map.Entry<String, Integer> entry : listSubj.entrySet()) {
            String name = entry.getKey();
            Integer sum = entry.getValue();
            Label sjt = new Label(name, sum);
            list.add(sjt);
        }
        list.sort(Comparator.naturalOrder());
        Label best = list.get(list.size() - 1);
        return best;
    }
}
