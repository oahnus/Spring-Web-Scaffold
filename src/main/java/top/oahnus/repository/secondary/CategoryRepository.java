package top.oahnus.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.secondary.Category;

/**
 * Created by oahnus on 2017/11/28
 * 12:05.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
