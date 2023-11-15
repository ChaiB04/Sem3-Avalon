package individual.individualsem3backend.persistence;

import individual.individualsem3backend.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {


    @Query("SELECT p FROM ProductEntity p" +
            " WHERE (:name IS NULL or p.name = :name)" +
            "AND (:price=0 or p.price = :price)" +
            "AND (:color IS NULL or p.color = :color)")
    List<ProductEntity> findByFilter(@Param("name") String name, @Param("price") Double price, @Param("color") String color);
}
