package riccardogulin;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4d12pu");
	// Per connettere l'applicazione al DB dobbiamo usare la Persistence Unit descritta dentro il file persistence.xml
	// Per farlo, creiamo un attributo statico di tipo EntityManagerFactory passandogli il nome della Persistence Unit

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
