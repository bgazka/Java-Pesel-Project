package pl.bgalazka.peselproject.person;

import org.springframework.stereotype.Component;

import java.util.Set;

import javax.validation.*;

@Component
public class PersonValidator {

  private final Validator validator;

  public PersonValidator(Validator validator) {this.validator = validator;}

  public boolean validate(Person person) {
    Set<ConstraintViolation<Person>> personViolations = validator.validate(person);
    if (!personViolations.isEmpty()) {
      System.out.println("Błąd:");
      personViolations.forEach(p -> System.out.println(p.getMessage()));
      return false;
    } else {
      System.out.println("Osoba poprawnie zwalidowana.");
      return true;
    }
  }


}
