package com.employee_self_service.entities.hr;

import com.employee_self_service.entities.common.NameWithDeleteEntity;
import com.employee_self_service.entities.users.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "HR_DEPARTMENTS")
public class Departments extends NameWithDeleteEntity {

    @Column(name = "department_head_id")
    private Users departmentHead;

    private String code;
    private String location;
    private String phoneExtension;

}
