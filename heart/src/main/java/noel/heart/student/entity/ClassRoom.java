package noel.heart.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClassRoom {

    @Id
    @GeneratedValue
    @Column(name = "classRoom_id")
    private Long id;

    @Column
    int grade; // 학년

    @Column
    int number; // 반

}
