package lk.ijse.lms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ReturnDTO implements SuperDTO {
    private String r_id;
    private String state;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnedDate;
}
