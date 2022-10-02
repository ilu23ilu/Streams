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
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long countYoung = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(countYoung);

        List<String> recruits = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        for (String surnames : recruits){
            System.out.println(surnames);
        }

        List<Person> workingMen = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 65)
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .collect(Collectors.toList());
        List<Person> workingWomen = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 60)
                .filter(person -> person.getSex() == Sex.WOMAN)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .collect(Collectors.toList());
        List<Person> workingPeople = Stream.concat(workingMen.stream(), workingWomen.stream())
                .collect(Collectors.toList());
        List<Person> sortedWorkingPeople = workingPeople.stream()
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        for (Person pers : sortedWorkingPeople){
            System.out.println(pers);
        }
    }
}
