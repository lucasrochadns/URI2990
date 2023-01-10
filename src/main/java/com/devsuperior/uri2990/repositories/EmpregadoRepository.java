package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2990.entities.Empregado;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true, value = "SELECT e.cpf, e.enome, d.dnome " +
            "FROM empregados AS e INNER JOIN departamentos AS d ON e.dnumero = d.dnumero " +
            "WHERE e.cpf NOT IN (SELECT empregados.cpf FROM empregados INNER JOIN trabalha ON trabalha.cpf_emp = e.cpf) ORDER BY e.cpf")
    List<EmpregadoDeptProjection> searchEmpregadoByDep();

    /**RETORNA FUNCIONARIOS QUE NÃO ESTAO CONTIDOS NA projetosOndeTrabalha*/
    @Query(value="SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(e.cpf, e.enome, e.departamento.dnome) FROM Empregado e "+
    "WHERE e.cpf NOT IN (SELECT e.cpf FROM Empregado e INNER JOIN e.projetosOndeTrabalha) ORDER BY e.cpf")
    List<EmpregadoDeptDTO> searchEmpregadoByDepartamento();
}
