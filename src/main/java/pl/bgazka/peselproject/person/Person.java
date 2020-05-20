package pl.bgalazka.peselproject.person;

import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;


import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@ToString
@NoArgsConstructor
public class Person {

  private static Long ID = 1L;

  private Long id;
  @NotEmpty(message = "Brak miasta")
  private String city;
  @NotEmpty(message = "Brak imienia")
  private String name;
  @NotEmpty(message = "Brak nazwiska")
  private String lastName;
  @PESEL(message = "niepoprawny PESEL")
  private String pesel;

  public Person(
      @NotEmpty(message = "Brak miasta") String city,
      @NotEmpty(message = "Brak imienia") String name,
      @NotEmpty(message = "Brak nazwiska") String lastName,
      @PESEL(message = "niepoprawny PESEL") String pesel
  ) {
    this.id =  ID++;
    this.city = city;
    this.name = name;
    this.lastName = lastName;
    this.pesel = pesel;
  }
}
