package com.juliankuipers.repositories;

import com.juliankuipers.entities.Game;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GameRepositoryImplementation {
    @PersistenceContext
    private EntityManager em;

    public List<Game> findTop(int limit) {
        return em.createQuery("SELECT g FROM Game g ORDER BY g.id desc").setMaxResults(limit).getResultList();
    }
}
