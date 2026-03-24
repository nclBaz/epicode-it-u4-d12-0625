package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.StudentsDAO;
import riccardogulin.entities.Student;
import riccardogulin.entities.StudentType;
import riccardogulin.exceptions.NotFoundException;

public class Application {
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4d12pu");
	// Per connettere l'applicazione al DB dobbiamo usare la Persistence Unit descritta dentro il file persistence.xml
	// Per farlo, creiamo un attributo statico di tipo EntityManagerFactory passandogli il nome della Persistence Unit

	public static void main(String[] args) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		StudentsDAO studentsDAO = new StudentsDAO(entityManager);

		Student aldo = new Student("Aldo", "Baglio", StudentType.FULLTIME);
		Student giovanni = new Student("Giovanni", "Storti", StudentType.PARTTIME);
		Student giacomo = new Student("Giacomo", "Poretti", StudentType.FULLTIME);

//		studentsDAO.save(aldo);
//		studentsDAO.save(giovanni);
//		studentsDAO.save(giacomo);

		try {
			Student aldoFromDB = studentsDAO.findById(5);
			System.out.println(aldoFromDB);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		try {
			studentsDAO.findByIdAndDelete(3);
		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}


		// Best Practice. Di norma è bene chiudere EntityManager e EntityManagerFactory quando non si utilizzano più
		// (nel nostro caso è irrilevante in quanto l'applicazione si avvia ma si chiude chiudendo anche tutte le cose lasciate aperte)
		entityManager.close();
		entityManagerFactory.close();
	}
}
