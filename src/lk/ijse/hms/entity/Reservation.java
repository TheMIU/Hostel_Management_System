package lk.ijse.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Reservation {
    @Id
    private String res_id;
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id" , insertable = false )
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_type_id" , insertable = false )
    private Room room;
}
