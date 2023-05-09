package lk.ijse.lms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ReturnDTO implements SuperDTO {
    private String id;
    private String state;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate returnedDate;
}
