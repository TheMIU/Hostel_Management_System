package lk.ijse.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Student {
    @Id
    private String id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String contact_no;
    @Column(columnDefinition = "DATE")
    private LocalDate dob;
    private String gender;

    @OneToMany (mappedBy = "student" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Reservation> reservationList = new ArrayList<>();
}
