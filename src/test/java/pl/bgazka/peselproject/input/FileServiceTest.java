package pl.bgalazka.peselproject.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.bgalazka.peselproject.file.FileService;
import pl.bgalazka.peselproject.person.Person;

import java.io.IOException;
import java.util.List;

class FileServiceTest {

  private static String path =  "src/test/resources/database.json";
  private static FileService fileService;

  @BeforeAll
  static void setUp() throws IOException, ClassNotFoundException {
    fileService = new FileService(path);
  }

  @AfterEach
  void cleanUp() {
    fileService.getFile().delete();
    System.out.println("Plik po tescie zostal usuniety!");
  }

  @Test
  void shouldCorrectlySavePersonToFileAndReadFromIt() {
    // given
    Person person = new Person("Pulawy", "Marian", "Kowalski", "82031204392");

    // when
    fileService.saveJsonToFile(person);

    // then
    assertThat(fileService.readPersonListFromFile().get(0).getName()).isEqualTo("Name");
  }

  @Test
  void shouldReplaceEntryIfPeselIsTheSame() {
    // given
    Person person1 = new Person("Pulawy", "Marian", "Kowalski", "82031204392");
    Person person2 = new Person("Gniezno", "Halina", "Tort", "52012566518");
    fileService.saveJsonToFile(person1);
    fileService.saveJsonToFile(person2);

    // when
    fileService.saveJsonToFile(new Person("Gniezno", "Krystyna", "Tort", "52012566518"));

    // then
    List<Person> people = fileService.readPersonListFromFile();
    Person changed = people.stream().filter(x -> x.getPesel().equals("52012566518")).findFirst().get();

    assertThat(changed.getName()).isEqualTo("Changed");
  }

}
