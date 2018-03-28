package top.oahnus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import top.oahnus.controller.pageForm.CategoryForm;
import top.oahnus.domain.Category;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by oahnus on 2018/2/27
 * 11:47.
 */
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    default Page<Category> findByForm(CategoryForm pageForm) {
        Specification<Category> specification = (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();

            String name = pageForm.getName();
            Date createTime = pageForm.getCreateTime();
            Long parentId = pageForm.getParentId();


            if (!StringUtils.isEmpty(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%")); // where name like '%' + name +  '%'
            }
            if (createTime != null) {
                predicates.add(criteriaBuilder.equal(root.get("createTime"), createTime)); // where createTime = ?
            }
            if (parentId != null) {
                predicates.add(criteriaBuilder.equal(root.get("parentId"), parentId)); // where parentId = ?
            }

//            predicates.add(criteriaBuilder.le(root.get("heat"), 1)); // heat <= 1
//            predicates.add(criteriaBuilder.ge(root.get("heat"), 1)); // heat >= 1

            predicates.add(criteriaBuilder.equal(root.get("delFlag"), false));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime"))); // order by createTime desc
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        return findAll(specification, pageForm.getPage());
    }
}
