package br.com.projeto.comanda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.comanda.domain.Adicional;

@Repository
public interface AdicionalRepository extends JpaRepository<Adicional, Long>{

	
}
