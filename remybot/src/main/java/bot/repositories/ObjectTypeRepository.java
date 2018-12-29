package bot.repositories;
import bot.entities.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectTypeRepository extends JpaRepository<ObjectType, Long> {
}
