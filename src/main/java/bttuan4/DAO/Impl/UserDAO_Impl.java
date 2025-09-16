package bttuan4.DAO.Impl;

import bttuan4.config.JpaUtils;
import bttuan4.DAO.UserDAO;
import bttuan4.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDAO_Impl implements UserDAO {

    @Override
    public User findByUsername(String username) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username = :uname", User.class);
            q.setParameter("uname", username);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public User login(String username, String password) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username = :uname AND u.password = :pass", User.class);
            q.setParameter("uname", username);
            q.setParameter("pass", password);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean insert(User user) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean update(User user) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean updatePassword(String email, String newPass) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            q.setParameter("email", email);
            User user = q.getSingleResult();
            if (user == null) return false;
            user.setPassword(newPass);
            em.merge(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :uname", Long.class)
                    .setParameter("uname", username)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }
}
