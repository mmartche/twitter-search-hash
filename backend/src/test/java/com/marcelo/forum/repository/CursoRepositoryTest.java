package com.marcelo.forum.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.marcelo.api.modelo.Curso;
import com.marcelo.api.repository.CursoRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void deveriaCarregarConteudoAoBuscarPorNomefindByNome() {
		String nomeCurso = "HTML 5";
		
		Curso html5 = new Curso();
		html5.setNome(nomeCurso);
		html5.setCategoria("programacao");
		em.persist(html5);
		
		Curso curso = repository.findByNome(nomeCurso);
		Assert.assertNotNull(curso);
		Assert.assertNotNull(nomeCurso, curso.getNome());
	}
	
	@Test
	public void naoDeveriaCarregarConteudoAoBuscarPorNomeErradofindByNome() {
		String nomeCurso = "nome errado";
		Curso curso = repository.findByNome(nomeCurso);
		Assert.assertNull(curso);
	}

}
