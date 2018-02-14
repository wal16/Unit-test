
import com.infoshareacademy.javaintroduction.datastructures.task7.domain.*;
import com.infoshareacademy.javaintroduction.datastructures.task7.service.UserService;
import com.infoshareacademy.javaintroduction.datastructures.task7.domain.Permission;
import org.junit.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class UserServiceTest {



        @Test
        public void findUsersWhoHaveMoreThanOneAddress() {

                List<User> users = new ArrayList<> ();
                List<Address> addresses = new ArrayList<>();
                users.add(new User().setPersonDetails(new Person().setAddresses(new ArrayList<Address>(Arrays.asList (new Address(), new Address())))));
                users.add(new User().setPersonDetails(new Person().setAddresses(new ArrayList<Address>(Arrays.asList (new Address())))));
                users.add(new User().setPersonDetails(new Person().setAddresses(new ArrayList<Address>(Arrays.asList (new Address(), new Address(), new Address())))));
                users.add(new User().setPersonDetails(new Person().setAddresses(new ArrayList<Address>(Arrays.asList (new Address(), new Address(), new Address())))));

              assertThat(UserService.findUsersWhoHaveMoreThanOneAddress(users)).hasSize(3);

        }

        @Test
        public void findOldestPersonTest() {

                User user1 = new User();
                Person person1 = new Person();
                person1.setAge(14);
                user1.setPersonDetails(person1);

                User user2 = new User();
                Person person2 = new Person();
                person2.setAge(18);
                user2.setPersonDetails(person2);

               List<User> users = new ArrayList<> ();
                users.add(user1);
                users.add(user2);

               Person oldestPerson = UserService.findOldestPerson(users);

                assertEquals("oldest person", person2, oldestPerson);
                assertThat(UserService.findOldestPerson(users).getAge()).isEqualTo(18);

        }

        @Test
        public void findOldestPersonTest2() {
                List<User> users = new ArrayList<> ();

                users.add(new User().setPersonDetails(new Person().setAge(20)));
                users.add(new User().setPersonDetails(new Person().setAge(40)));

                assertThat(UserService.findOldestPerson(users).getAge()).isEqualTo(40);

        }


        @Test
        public void findUserWithLongestUsernameTest() {

                User user1 = new User();
                user1.setName("Kowalski");

                User user2 = new User();
                user2.setName("Rak");

                List<User> users = new ArrayList<> ();
                users.add(user1);
                users.add(user2);

                User longestUserName = UserService.findUserWithLongestUsername(users);

                assertEquals("longestUserName", user1, longestUserName);
        }

        @Test
        public void findUserWithLongestUsernameTest2() {
                List<User> users = new ArrayList<> ();
                users.add(new User().setName("Tomasz"));
                users.add(new User().setName("Tom"));
                users.add(new User().setName("Hermenegilda"));

                assertThat(UserService.findUserWithLongestUsername(users).getName()).containsIgnoringCase("Hermenegilda");
        }


        @Test
        public void getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18Test() {


                User user1 = new User();
                Person personDetails1 = new Person();
                personDetails1.setAge(19);
                personDetails1.setName("Marek");
                personDetails1.setSurname("Sowa");

                User user2 = new User();
                Person personDetails2 = new Person();
                personDetails2.setAge(11);
                personDetails2.setName("Aga");
                personDetails2.setSurname("Sowa");

                List<User> users = new ArrayList<> ();
                users.add(user1);
                users.add(user2);

                assertEquals("user above 18", 19,  personDetails1.getAge());
                assertEquals("user under 18", 11, personDetails2.getAge());
                }


        @Test
        public void getSortedPermissionsOfUsersWithNameStartingWithATest() {

                List<User> users = new ArrayList<>();

                users.add(new User().setName("Ala").setPersonDetails(new Person().setRole(new Role()
                        .setPermissions(new ArrayList<>(Arrays.asList(new Permission().setName("p1"), new Permission().setName("p2")))))));

                users.add(new User().setName("Ania").setPersonDetails(new Person().setRole(new Role()
                        .setPermissions(new ArrayList<>(Arrays.asList(new Permission().setName("p3")))))));
                users.add(new User().setName("Tom").setPersonDetails(new Person()
                        .setRole(new Role().setPermissions(new ArrayList<>(Arrays.asList(new Permission().setName("p4")))))));

                assertThat(UserService.getSortedPermissionsOfUsersWithNameStartingWithA(users)).containsOnly("p1", "p2", "p3");

        }
}
