/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ni.jug.pico.core.boundary;

import org.ni.jug.pico.core.model.CuentaTipo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Gustavo Castro <gacsnic75@gmail.com>
 * @version 0.0.1
 */
@Service
@Transactional
public class CuentaTipoService {

     @PersistenceContext
     EntityManager em;

     public List<CuentaTipo> buscarTodos() {
          CriteriaBuilder builder = em.getCriteriaBuilder();
          CriteriaQuery<CuentaTipo> criteriaQuery = builder.createQuery(CuentaTipo.class);
          Root<CuentaTipo> cuentaTipoRoot = criteriaQuery.from(CuentaTipo.class);
          criteriaQuery.select(cuentaTipoRoot);
          return em.createQuery(criteriaQuery).getResultList();
     }

     public void actualizar(CuentaTipo cuentaTipo) {
          em.merge(cuentaTipo);
     }
}
