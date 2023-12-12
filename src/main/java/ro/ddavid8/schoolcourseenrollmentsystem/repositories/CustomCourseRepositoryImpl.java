package ro.ddavid8.schoolcourseenrollmentsystem.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ro.ddavid8.schoolcourseenrollmentsystem.models.entities.Course;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CustomCourseRepositoryImpl implements CustomCourseRepository {

    private final EntityManager entityManager;

    public List<Course> findCoursesByCriteria(String courseName, String description, Sort sort) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        List<Predicate> predicates = new ArrayList<>();
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        if (courseName != null && !courseName.isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(courseRoot.get("courseName")), "%" + courseName.toLowerCase() + "%"));
        }
        if (description != null && !description.isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(courseRoot.get("description")), "%" + description.toLowerCase() + "%"));
        }

        List<Order> orders = new ArrayList<>();
        for (Sort.Order sortOrder : sort) {
            String property = sortOrder.getProperty();
            if (sortOrder.isAscending()) {
                orders.add(criteriaBuilder.asc(courseRoot.get(property)));
            } else {
                orders.add(criteriaBuilder.desc(courseRoot.get(property)));
            }
        }
        criteriaQuery.orderBy(orders);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Course> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}