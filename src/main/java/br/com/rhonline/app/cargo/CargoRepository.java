package br.com.rhonline.app.cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    @Query("from Cargo c where c.nomeCargo =:nomeCargo and c.id <> :id")
    Cargo findByNomeCargo(@Param("nomeCargo") String nomeCargo,
                          @Param("id") Long id);
}
