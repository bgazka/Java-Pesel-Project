package pl.bgalazka.peselproject.input;

import org.springframework.stereotype.Service;
import pl.bgalazka.peselproject.file.FileService;
import pl.bgalazka.peselproject.person.Person;
import pl.bgalazka.peselproject.person.PersonValidator;

import java.util.*;

@Service
public class InputService {

  private final Scanner scanner;
  private final PersonValidator personValidator;
  private final FileService fileService;

  public InputService(
      Scanner scanner,
      PersonValidator personValidator,
      FileService fileService
  ) { this.scanner = scanner;
    this.personValidator = personValidator;
    this.fileService = fileService;
  }

  public void start() {
    boolean again = true;

    do {
      System.out.println("Miasto: ");
      String city = scanner.nextLine();
      System.out.println("Imię, nazwisko i PESEL (w podanej kolejności, oddzielone spacjami):");
      String details = scanner.nextLine();

      Person personFromInput = createPersonFromInput(city, details);
      if (personFromInput == null) {
        System.out.println("Nie udało się utworzyć osoby");
      } else {
        System.out.println("Dodano osobę do pliku: " + personFromInput);
      }

      boolean goodAnswer = false;
      do {
        System.out.println("Chcesz dodac jeszcze jedna osobe? (T, N)");
        String answer = scanner.nextLine().toUpperCase();
        if("T".equals(answer)) {
          again = true;
          goodAnswer = false;
        } else if("N".equals(answer)) {
          goodAnswer = false;
          again = false;
        } else {
          System.out.println("Mozliwe odpowiedzi to 'T' lub 'N'");
          goodAnswer = true;
        }
      } while(goodAnswer);
    } while (again);
  }

  private Person createPersonFromInput(String city, String details) {
    List<String> divided = new ArrayList<>(Arrays.asList(details.split(" ")));
    if(divided.size() != 3) {
      System.out.println("Nie wszystkie pola zostały uzupełnione");
      return null;
    }
    Person person = new Person(city, divided.get(0), divided.get(1), divided.get(2));

    if (personValidator.validate(person)) {
      return fileService.saveJsonToFile(person);
    } else {
      return null;
    }
  }
}
