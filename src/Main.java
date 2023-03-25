import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        Long count = persons.stream()
                .filter((age1) -> age1.getAge() < 18)
                .count();
        System.out.println(count);

        List<String> recruitFamilies = persons.stream()
                .filter(age1 -> (age1.getAge() >= 18 && age1.getAge() <= 27))
                .map(recruit -> recruit.getFamily().toString())
                .collect(Collectors.toList());
        System.out.println(recruitFamilies);

        List<String> workerFamilies = persons.stream()
                .filter(age2 -> (age2.getAge() >= 18))
                .filter((age3) -> {
                    if (age3.getSex().equals(Sex.MAN) && age3.getAge() <= 65) {
                        return true;
                    }
                    if (age3.getSex().equals(Sex.WOMAN) && age3.getAge() <= 60) {
                        return true;
                    }
                    return false;
                })
                .filter(ed -> ed.getEducation().equals(Education.HIGHER))
                .map(worker -> worker.getFamily().toString())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(workerFamilies);

    }

}