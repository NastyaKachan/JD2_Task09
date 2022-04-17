package by.academy.JD2_Task09;

import by.academy.JD2_Task09.Entity.Address;
import by.academy.JD2_Task09.Entity.People;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppMainTest {
    private EntityManager em;
    People peopleTest;
    Address addressTest;

    @Before
    public void ShouldConnect() {
        peopleTest = People.builder().name("Artem").surname("Volnyi").patronymic("Petrovich").build();
        addressTest = Address.builder().city("Minsk").street("Klumova").house("18").build();
    }

    @Test
    public void aAddTest() {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(peopleTest);
        em.persist(addressTest);
        String expectedPeople = peopleTest.toString();
        String expectedAddress = addressTest.toString();
        String actualPeople = em.find(People.class, peopleTest.getId()).toString();
        String actualAddress = em.find(Address.class, addressTest.getId()).toString();
        em.getTransaction().commit();
        assertEquals(expectedPeople, actualPeople);
        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void bUpdateTest() {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(peopleTest);
        em.persist(addressTest);
        em.find(People.class, peopleTest.getId()).setName("Vlad");
        em.find(Address.class, addressTest.getId()).setCity("Grodno");
        em.getTransaction().commit();
        peopleTest.setName("Vlad");
        addressTest.setCity("Grodno");
        String expectedPeople = em.find(People.class,peopleTest.getId()).toString();
        String expectedAddress = em.find(Address.class,addressTest.getId()).toString();
        String actualPeople = em.find(People.class,peopleTest.getId()).toString();
        String actualAddress = em.find(Address.class,addressTest.getId()).toString();
        assertEquals(expectedPeople, actualPeople);
        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void cDeleteTest() {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(peopleTest);
        em.persist(addressTest);
        People findPeople = em.find(People.class, peopleTest.getId());
        Address findAddress = em.find(Address.class, addressTest.getId());
        em.remove(findPeople);
        em.remove(findAddress);
        em.getTransaction().commit();
        assertNull(em.find(People.class, peopleTest.getId()));
        assertNull(em.find(Address.class, addressTest.getId()));
    }

}
