package study.domain.common;

import javax.persistence.*;

import java.time.LocalDateTime;
import lombok.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseTime {

    @CreatedDate
    private LocalDateTime createdAt;
    //private Timestamp createdAt; 와의 차이

    @LastModifiedDate
    private LocalDateTime updatedAt;
}