package lk.ijse.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Room {
    @Id
    private String room_type_id;
    private String type;
    private String key_money;
    private int qty;

    @OneToMany (mappedBy = "room" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Reservation> reservationList = new ArrayList<>();
}