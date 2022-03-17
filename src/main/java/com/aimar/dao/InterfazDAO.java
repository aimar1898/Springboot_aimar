package com.aimar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aimar.entities.Usuario;

public interface InterfazDAO extends JpaRepository<Usuario, Integer> {

	@Query(" from Usuario as usu where usu.edad between 28 and 55")
	public Iterable<Usuario> getMayor28();
	
	@Query(" from Usuario as usu where usu.apellido = 'Arrizabalaga'")
	public Iterable<Usuario> findArrizabalagas();
}
