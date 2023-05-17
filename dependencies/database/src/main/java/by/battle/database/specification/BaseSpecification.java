package by.battle.database.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSpecification<E> {

    protected List<Specification<E>> specifications = new ArrayList<>();
    protected CompositeType compositeType;

    public BaseSpecification(CompositeType compositeType) {
        this.compositeType = compositeType;
    }

    protected void addSpecificationIfNotEmptyOrNull(Object param, Specification<E> specification) {
        if (!ObjectUtils.isEmpty(param)) {
            specifications.add(specification);
        }
    }

    public Specification<E> build() {
        return specifications.stream()
                .reduce((first, second) -> compositeType == CompositeType.AND ? first.and(second) : first.or(second))
                .orElse((root, query, cb) -> cb.conjunction());
    }
}
