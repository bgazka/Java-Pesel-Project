package pl.bgalazka.peselproject.person;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

class PersonValidatorTest {

  private static final String CITY = "miasto";
  private static final String FIRST_NAME = "imie";
  private static final String LAST_NAME = "nazwisko";
  private static final String CORRECT_PESEL = "92012509898";
  private static final String INCORRECT_PESEL = "12345623452";

  private static PersonValidator personValidator;

  @BeforeAll
  static void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    personValidator = new PersonValidator(factory.getValidator());
  }

  @Test
  void shouldAcceptCorrectPesel() {
    // given
    Person person = new Person(CITY, FIRST_NAME, LAST_NAME, CORRECT_PESEL);

    // when
    boolean validate = personValidator.validate(person);

    // then
    assertThat(validate).isTrue();
  }

  @Test
  void shouldNotAcceptIncorrectPesel() {
    // given
    Person person = new Person(CITY, FIRST_NAME, LAST_NAME, INCORRECT_PESEL);

    // when
    boolean validate = personValidator.validate(person);

    // then
    assertThat(validate).isFalse();
  }

  @Test
  void shouldNotAcceptWhenCityIsNotPassed() {
    // given
    Person person = new Person("", FIRST_NAME, LAST_NAME, CORRECT_PESEL);

    // when
    boolean validate = personValidator.validate(person);

    // then
    assertThat(validate).isFalse();
  }

  @Test
  void shouldNotAcceptWhenFirstNameIsNotPassed() {
    // given
    Person person = new Person(CITY, "", LAST_NAME, CORRECT_PESEL);

    // when
    boolean validate = personValidator.validate(person);

    // then
    assertThat(validate).isFalse();
  }

  @Test
  void shouldNotAcceptWhenLastNameIsNotPassed() {
    // given
    Person person = new Person(CITY, FIRST_NAME, "", CORRECT_PESEL);

    // when
    boolean validate = personValidator.validate(person);

    // then
    assertThat(validate).isFalse();
  }
}
