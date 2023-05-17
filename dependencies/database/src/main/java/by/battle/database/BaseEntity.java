package by.battle.database;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.proxy.HibernateProxyHelper;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (HibernateProxyHelper.getClassWithoutInitializingProxy(this)
                != HibernateProxyHelper.getClassWithoutInitializingProxy(o)) {
            return false;
        }

        final BaseEntity that = (BaseEntity) o;

        return that.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
