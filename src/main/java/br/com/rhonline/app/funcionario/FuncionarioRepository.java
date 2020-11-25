package br.com.rhonline.app.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("select count(f.id) from Funcionario f where f.matricula =:matricula and f.id <> :id")
    long matriculaExists(@Param("matricula") String matricula, @Param("id") Long id);
}
