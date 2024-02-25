package noel.heart.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;
    private String name;
    private String password;
    @JoinColumn(name = "classRoom_id")
    @ManyToOne
    private ClassRoom classRoom;
    private long heartCnt; //하트수
}
