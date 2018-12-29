package bot.repositories;
import bot.entities.Object;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectRepository extends JpaRepository<Object, Long> {
}
