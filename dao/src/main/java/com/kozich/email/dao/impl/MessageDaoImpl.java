package com.kozich.email.dao.impl;


import com.kozich.email.dao.api.MessageDao;
import com.kozich.email.dao.entity.MessageEntity;
import com.kozich.email.dao.factory.DaoFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class MessageDaoImpl implements MessageDao {

    @Override
    public Optional<MessageEntity> get(Long id) {
        EntityManager entityManager = DaoFactory.getEntityManager();
        entityManager.getTransaction().begin();
        MessageEntity messageEntity = entityManager.find(MessageEntity.class, id);
        entityManager.getTransaction().commit();
        return Optional.ofNullable(messageEntity);
    }

    @Override
    public List<MessageEntity> get(Integer page, Integer size) {
        EntityManager entityManager = DaoFactory.getEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageEntity> query = criteriaBuilder.createQuery(MessageEntity.class);
        Root<MessageEntity> from = query.from(MessageEntity.class);

        List<MessageEntity> resultList = entityManager.createQuery(query)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
        entityManager.getTransaction().commit();

        return resultList;
    }

    @Override
    public MessageEntity create(MessageEntity messageEntity) {
        EntityManager entityManager = DaoFactory.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(messageEntity);

        entityManager.getTransaction().commit();

        return messageEntity;
    }
}
