import java.io.Reader;
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
        //Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        long youngMan = persons.stream().filter(person -> person.getAge() < 18).count();

        //Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> listFamilies = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18 & person.getAge() <= 27)
                .map(Person::getFamily).map(String::new).collect(Collectors.toList());

        //Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
        // в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
        List<String> sortedListFamiliesOfPerson = persons.stream()
                .filter(person -> ((person.getAge() > 18 & person
                        .getAge() < 60) & person.getSex().equals(Sex.WOMAN))
                        | ((person.getSex().equals(Sex.MAN)) &
                        (person.getAge() > 18 & person.getAge() < 65)))
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .map(String::new).collect(Collectors.toList());

        //System.out.println(youngMan);
        //System.out.println(listFamilies);
        //System.out.println(sortedListFamiliesOfPerson);
    }
}