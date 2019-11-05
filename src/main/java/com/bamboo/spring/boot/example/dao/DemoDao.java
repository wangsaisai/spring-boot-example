package com.bamboo.spring.boot.example.dao;

import com.bamboo.spring.boot.example.entity.Demo;
import com.bamboo.spring.boot.example.model.DemoSearchInfo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoDao {

  @Autowired
  EntityManager em;

  public List<Demo> search(DemoSearchInfo demoSearchInfo) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Demo> cq = cb.createQuery(Demo.class);

    Root<Demo> demoRoot = cq.from(Demo.class);

    List<Predicate> predicates = new ArrayList<>(5);

    if (demoSearchInfo.getDemoType() != null) {
      predicates.add(cb.equal(demoRoot.get("demoType"), demoSearchInfo.getDemoType()));
    }

    String name = demoSearchInfo.getName();
    if (name != null && !name.trim().isEmpty()) {
      predicates.add(cb.like(demoRoot.get("name"), "%" + name.trim() + "%"));
    }

    if (demoSearchInfo.getEnable() != null) {
      predicates.add(cb.equal(demoRoot.get("enable"), demoSearchInfo.getEnable()));
    }

    cq.where(predicates.toArray(new Predicate[0]));

    if (demoSearchInfo.getOrderByName() != null) {
      Expression orderExp = demoRoot.get(demoSearchInfo.getOrderByName());

      Order order;
      if ("desc".equals(demoSearchInfo.getOrderByType())) {
        order = cb.desc(orderExp);
      } else {
        order = cb.asc(orderExp);
      }
      cq.orderBy(order);
    }

    TypedQuery<Demo> query = em.createQuery(cq);

    return query.getResultList();
  }

}
