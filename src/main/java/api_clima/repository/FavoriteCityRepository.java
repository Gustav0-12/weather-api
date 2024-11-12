package api_clima.repository;

import api_clima.entities.FavoriteCity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long> {
    @Query("SELECT f FROM FavoriteCity f JOIN FETCH f.user u JOIN FETCH f.city c WHERE u.id = :userId")
    Page<FavoriteCity> findByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT f FROM FavoriteCity f JOIN f.user u JOIN f.city c WHERE c.name = :name " +
    "AND u.id = :userId")
    FavoriteCity findByCityNameAndUserId(@Param("name") String name, @Param("userId") Long userId);
}
