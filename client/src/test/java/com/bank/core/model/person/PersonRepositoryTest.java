package com.bank.core.model.person;

import static com.bank.core.data.DataUtil.buildPersonData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class PersonRepositoryTest {
    @Mock
    private PersonRepository personRepository;

    @Test
    void givenValidDataToSaveAPerson_thenSuccess_ok() {
        Person person = buildPersonData();
        when(personRepository.save(any(Person.class)))
                .thenReturn(person);
        Person personSaved = this.personRepository.save(person);
        assertThat(personSaved).isNotNull();
        verify(personRepository).save(any(Person.class));
    }

    @Test
    void givenValidDataToFindAllPersons_thenSuccess_ok() {
        List<Person> personList = new ArrayList<>();
        Person person = buildPersonData();
        personList.add(person);
        when(personRepository.findAll())
                .thenReturn(personList);
        List<Person> response = this.personRepository.findAll();
        assertThat(response.get(0)).isNotNull();
        verify(personRepository).findAll();
    }

    @Test
    void givenValidDataToFindIdPersons_thenSuccess_ok() {
        Person person = buildPersonData();
        when(personRepository.findById(anyLong()))
                .thenReturn(Optional.of(person));
        Optional<Person> response = this.personRepository.findById(1L);
        assertThat(response).isNotNull();
        verify(personRepository).findById(1L);
    }
}
