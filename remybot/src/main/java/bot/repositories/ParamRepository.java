package bot.repositories;
import bot.entities.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamRepository extends JpaRepository<Param, Long> {
}