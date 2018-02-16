import com.infoshareacademy.javaintroduction.datastructures.task7.domain.*;
import com.infoshareacademy.javaintroduction.datastructures.task7.service.UserService;
import com.infoshareacademy.javaintroduction.datastructures.task7.domain.Permission;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class UserServiceTest {
    @Test
    public void findUsersWhoHaveMoreThanOneAddress() {
        List<User> users = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        users.add(new User().setPersonDetails(new Person().setAddresses(new ArrayList<Address>(Arrays.asList(new Address(), new Address(), new Address())))));
        users.add(new User().setPersonDetails(new Person().setAddresses(new ArrayList<Address>(Arrays.asList(new Address(), new Address(), new Address())))));
        users.add(new User().setPersonDetails(new Person().setAddresses(new ArrayList<Address>(Arrays.asList(new Address(), new Address(), new Address(), new Address())))));
        users.add(new User().setPersonDetails(new Person().setAddresses(new ArrayList<Address>(Arrays.asList(new Address(), new Address(), new Address(), new Address())))));
        assertThat(UserService.findUsersWhoHaveMoreThanOneAddress(users)).hasSize(4);
    }

    @Test
    public void findOldestPerson() {
        //given
        User user1 = new User();
        Person person1 = new Person();
        person1.setAge(18);
        user1.setPersonDetails(person1);
        User user2 = new User();
        Person person2 = new Person();
        person2.setAge(50);
        user2.setPersonDetails((person2));
        //when
        Person oldestPerson = UserService.findOldestPerson(Arrays.asList(user1, user2));
        //then
        assertThat(oldestPerson).isEqualTo(person2);
        assertEquals("oldest person", person2, oldestPerson);
    }

    @Test
    public void shouldFindUserWithLongestUsername() {
        List<User> users = new ArrayList<>();
        users.add(new User().setName("Tesla"));
        users.add(new User().setName("Edison"));
        users.add(new User().setName("Copernicus"));
        assertThat(UserService.findUserWithLongestUsername(users).getName()).isEqualToIgnoringCase("Copernicus");
    }

    @Test
    public void findUserWithLongestUsername() {
        List<User> users = new ArrayList<>();
        users.add(new User().setName("Waldek"));
        users.add(new User().setName("Ryszard"));
        users.add(new User().setName("Zbigniew"));
        assertThat(UserService.findUserWithLongestUsername(users).getName()).containsIgnoringCase("Zbigniew");
    }

    @Test
    public void getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18() {
        User user1 = new User();
        Person personDetails1 = new Person();
        personDetails1.setAge(50);
        personDetails1.setName("Aleksander");
        personDetails1.setSurname("Kwaśniewski");
        User user2 = new User();
        Person personDetails2 = new Person();
        personDetails2.setAge(9);
        personDetails2.setName("Natalia");
        personDetails2.setSurname("Zwara");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        assertEquals("user above 18", 50, personDetails1.getAge());
        assertEquals("user under 18", 9, personDetails2.getAge());
    }

    @Test
    public void groupUsersByRole() {
        //given
        User user1 = new User();
        User user2 = new User();
        Role rola1 = new Role();
        rola1.setName("rola1");
        Person personDetails1 = new Person();
        personDetails1.setRole(rola1);
        user1.setPersonDetails(personDetails1);
        Role rola2 = new Role();
        rola2.setName("rola2");
        Person personDetails2 = new Person();
        personDetails2.setRole(rola2);
        user2.setPersonDetails(personDetails2);
        //when
        Map<Role, List<User>> role = UserService.groupUsersByRole(Arrays.asList(user1, user2));
        //then
        assertThat(role).isNotEmpty();
        assertThat(role).containsKey(rola1);
    }

    @Test
    public void partitionUserByUnderAndOver18() {
        //given
        User user1 = new User();
        Person person1 = new Person();
        person1.setAge(11);
        user1.setPersonDetails(person1);
        User user2 = new User();
        Person person2 = new Person();
        person2.setAge(55);
        user2.setPersonDetails((person2));
        //when
        Map<Boolean, List<User>> partitionedByAge = UserService.partitionUserByUnderAndOver18(Arrays.asList(user1, user2));
        //then
        assertThat(partitionedByAge).isNotNull();
        assertThat(partitionedByAge).containsKeys(true, false);
        assertThat(partitionedByAge.get(false)).contains(user1);
    }
}
