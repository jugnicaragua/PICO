package org.ni.jug.pico.core.boundary;

import org.ni.jug.pico.core.model.MiEmpresa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * @author Gustavo Castro <gacsnic75@gmail.com>
 * @version 0.0.1
 */
@Service
@Transactional
public class EmpresaService {

     @PersistenceContext
     EntityManager em;

     public void actualizar(MiEmpresa empresa) {
          em.merge(empresa);
     }

     public Optional<MiEmpresa> buscar() {
          CriteriaBuilder builder = em.getCriteriaBuilder();
          CriteriaQuery<MiEmpresa> criteriaQuery = builder.createQuery(MiEmpresa.class);
          Root<MiEmpresa> miEmpresaRoot = criteriaQuery.from(MiEmpresa.class);
          criteriaQuery.select(miEmpresaRoot);
          List<MiEmpresa> empresas = em.createQuery(criteriaQuery).getResultList();
          return empresas.isEmpty() ? Optional.empty() : Optional.of(empresas.get(0));
     }
}
