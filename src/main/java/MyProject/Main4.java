package MyProject;

import MyProject.entity.Employee;
import MyProject.hibernateSolutions.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;

public class Main4 {
    public static void main(String[] args) {

        //выборка полей
    //выбираем Employee в лист, но не со всеми полями
//select * from Employee
//select mark, model from Employee


        SessionFactory factory = HibernateUtil.getFactory();
        Session session = factory.openSession();
//для обычных селектов транзакцию не открываем

            //первый этап подготовки запроса
//Получаем объект CriteriaBuilder для открытия CriteriaQuery
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
// Получаем объект CriteriaQuery c помощью которого будем формироваь наш запрос
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Employee.class);//CriteriaQuery-подготовкаЗапроса
// получаем root - первостепенный, корневой ентити
            Root<Employee> root = criteriaQuery.from(Employee.class);//условия для запроса

//создаем массив Selection [] selections //занесем условия запроса в root
//и тогда надо чтоб подхоящий конструктор был
Selection[]selections={root.get("firstName"),root.get("phone")};//"firstName" и "phone"-надо писать как поля в классе

//указываем что мы хотим выбрать+
            criteriaQuery.select(criteriaBuilder.construct(Employee.class, selections));
          //  criteriaQuery.select(root);
            //в root -размещаем условия запроса(пример ниже)
//получить работника по имени
            // criteriaQuery.where( criteriaBuilder.equal( root.get("firstName"), "Bob" ) );


            //второй этап выполнение запроса
            Query query = session.createQuery(criteriaQuery);//сам запрос
            List<Employee> employees = query.getResultList();

            session.close();


            employees.forEach(System.out::println);

    }
}
