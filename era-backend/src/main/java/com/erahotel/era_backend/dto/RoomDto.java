package com.erahotel.era_backend.dto;

<<<<<<< HEAD
import com.erahotel.era_backend.entity.Reservation;
import lombok.Data;
=======
>>>>>>> 539ae98d14fa3b1de7ad69506803434caf165626
import java.math.BigDecimal;
import java.util.List;

@Data
public class RoomDto {
    private Long roomId;
    private String roomNumber;
<<<<<<< HEAD
    private String roomType;
    private BigDecimal pricePerNight;
    private List<Reservation> reservations;
=======
    private String description;
    private BigDecimal price;
    private List<String> roomReservations;
>>>>>>> 539ae98d14fa3b1de7ad69506803434caf165626
}
