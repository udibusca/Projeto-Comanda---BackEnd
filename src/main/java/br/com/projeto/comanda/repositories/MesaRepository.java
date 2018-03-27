package br.com.projeto.comanda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.comanda.domain.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long>{

}
