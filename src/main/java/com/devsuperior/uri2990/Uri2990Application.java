package com.devsuperior.uri2990;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2990.repositories.EmpregadoRepository;

import java.util.List;

@SpringBootApplication
public class Uri2990Application implements CommandLineRunner {

	@Autowired
	private EmpregadoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2990Application.class, args);
	}

	@Autowired
	private EmpregadoRepository empregadoRepository;

	@Override
	public void run(String... args) throws Exception {
		List<EmpregadoDeptProjection> emp = empregadoRepository.searchEmpregadoByDep();
		emp.forEach(x -> System.out.println(x.getCpf()+" | " + x.getEnome()+" | " + x.getDnome()));
		System.out.println("\n\n\n JPQL -> ");
		List<EmpregadoDeptDTO> empregado = empregadoRepository.searchEmpregadoByDepartamento();
		empregado.forEach(x -> System.out.println(x.getCpf()+" | " + x.getEnome()+" | " + x.getDnome()));
	}
}
