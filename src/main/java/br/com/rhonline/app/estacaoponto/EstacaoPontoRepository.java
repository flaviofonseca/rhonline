package br.com.rhonline.app.estacaoponto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacaoPontoRepository extends JpaRepository<EstacaoPonto, Long> {
}
