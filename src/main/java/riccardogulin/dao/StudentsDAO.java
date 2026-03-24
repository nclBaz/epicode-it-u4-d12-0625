package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Student;
import riccardogulin.exceptions.NotFoundException;

public class StudentsDAO {
	// DAO == Data Access Object
	// E' un'astrazione, nel senso che, siccome le varie operazioni di interazione col DB
	// possono essere complesse (richiedono transazioni, persist, remove,...) è meglio
	// "nascondere" queste complessità fornendo a chi avrà bisogno (main) dei metodi più
	// comodi da usare

	private final EntityManager entityManager;

	public StudentsDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Student newStudent) {
		// 1. Chiediamo all'Entity Manager di creare una nuova transazione
		EntityTransaction transaction = this.entityManager.getTransaction();

		// 2. Facciamo partire la transazione
		transaction.begin();

		// 3. Aggiungiamo newStudent (oggetto NON MANAGED) al PersistenceContext, facendolo
		// quindi diventare MANAGED (cioè monitorato dall'EntityManager). Non sarà ancora parte
		// del DB però
		entityManager.persist(newStudent);

		// 4. Per far si che l'oggetto managed diventi una nuova riga della tabella dobbiamo
		// fare il commit della transazione
		transaction.commit();

		// 5. Log di avvenuto salvataggio
		System.out.println("Lo studente " + newStudent.getSurname() + " è stato salvato con successo!");
	}

	public Student findById(long studentId) {
		Student found = entityManager.find(Student.class, studentId);
		if (found == null) throw new NotFoundException(studentId);
		return found;
	}

	public void findByIdAndDelete(long studentId) {
		// 1. Cerco lo studente tramite id
		Student found = this.findById(studentId); // Riutilizzo il metodo di sopra visto che mi gestisce anche il caso null

		// 2. Chiediamo all'Entity Manager di creare una nuova transazione
		EntityTransaction transaction = this.entityManager.getTransaction();

		// 3. Facciamo partire la transazione
		transaction.begin();

		// 4. Rimuoviamo lo studente trovato dal PersistenceContext tramite metodo .remove() dell'EntityManager.
		// Non sarà ancora rimosso dal DB però
		entityManager.remove(found);

		// 5. Per far si che l'oggetto diventi effettivamente cancellato dalla tabella dobbiamo
		// fare il commit della transazione
		transaction.commit();

		// 6. Log di avvenuta cancellazione
		System.out.println("Lo studente " + found.getId() + " è stato eliminato con successo!");

	}
}
