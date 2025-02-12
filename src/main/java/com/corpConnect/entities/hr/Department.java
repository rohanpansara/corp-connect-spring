package com.corpConnect.entities.hr;

import com.corpConnect.entities.common.NameWithDeleteEntity;
import com.corpConnect.entities.user.User;
import com.corpConnect.entities.user.userDetails.DepartmentDetail;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "HR_DEPARTMENTS")
public class Department extends NameWithDeleteEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_head_id")
    private User departmentHead;

    private String code;
    private String location;

    @Column(name = "phone_extension")
    private String phoneExtension;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DepartmentDetail> userDetailsDepartments = new HashSet<>();
}
