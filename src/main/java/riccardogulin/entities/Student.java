package riccardogulin.entities;

import jakarta.persistence.*;

@Entity // Annotazione obbligatoria per tutte le entities. Serve per definire una classe che verrà mappata ad
// una specifica tabella del DB
// Sarà Hibernate a creare in automatico la tabella se stiamo usando l'impostazione <property name="hibernate.hbm2ddl.auto" value="update"/>
// se la tabella non esiste. Se la tabella già esiste, cercherà (se ci riesce) di aggiornarla con gli attributi che sono
// dichiarati in questa classe
// N.B. Errore tipico è quello di dimenticarsi di aggiungere la classe al persistence.xml <class>riccardogulin.entities.Student</class>
@Table(name = "students") // Annotazione opzionale. Molto utile però per personalizzare il nome della tabella
public class Student {
	@Id // Annotazione OBBLIGATORIA. Serve per dichiarare che questo attributo sarà PRIMARY KEY della tabellas students
	@Column(name = "student_id")
	private long id;

	@Column(name = "first_name", nullable = false, length = 30)
	private String name;
	@Column(name = "last_name", nullable = false, length = 30)
	private String surname;
	@Enumerated(EnumType.STRING) // Di default gli ENUM vengono trattati come NUMERI (di solito non è quello che vogliamo)
	// Per dire a JPA creami una colonna di tipo testuale dobbiamo quindi utilizzare l'annotazione @Enumerated
	@Column(name = "student_type", nullable = false)
	// Usando gli Enum JPA automaticamente imposterà un controllo nella colonna che va a validare inseriti. Cioè nel nostro caso
	// si potranno inserire in quella colonna solo i valori FULLTIME o PARTTIME, non altri
	private StudentType studentType;
}
