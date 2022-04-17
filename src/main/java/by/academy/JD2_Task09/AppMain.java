package by.academy.JD2_Task09;

import by.academy.JD2_Task09.Entity.Address;
import by.academy.JD2_Task09.Entity.People;

import javax.persistence.EntityManager;

public class AppMain {
    public static void main(String[] args) {

        People personIvan = People.builder().name("Ivan").surname("Petrov").patronymic("Sergeevich").build();
        People personOlga = People.builder().name("Olga").surname("Igorevna").patronymic("Semenova").build();
        People personElena = People.builder().name("Elena").surname("Ivanova").patronymic("Vladimirovna").build();
        People personPetr = People.builder().name("Petr").surname("Antonovich").patronymic("Gavrilov").build();
        People personSergey = People.builder().name("Sergey").surname("Zverev").patronymic("Aleksandrovich").build();

        Address street1 = Address.builder().city("Minsk").street("Vaneeva").house("55").build();
        Address street2 = Address.builder().city("Minsk").street("Lenina").house("20").build();
        Address street3 = Address.builder().city("Minsk").street("Kirova").house("13").build();
        Address street4 = Address.builder().city("Minsk").street("Levkova").house("15").build();
        Address street5 = Address.builder().city("Minsk").street("Kozlova").house("98").build();

        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(personElena);
        em.persist(personIvan);
        em.persist(personPetr);
        em.persist(personOlga);
        em.persist(personSergey);
        em.persist(street1);
        em.persist(street2);
        em.persist(street3);
        em.persist(street4);
        em.persist(street5);
        People peopleFindById = em.find(People.class, 3);
        peopleFindById.setName("Kirill");
        em.refresh(peopleFindById);
        Address address = em.find(Address.class, 5);
        em.remove(address);
        em.getTransaction().commit();
        em.close();

        HibernateUtil.close();
    }
}
