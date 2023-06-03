package com.enset.ebank.DTO;



import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class AccountOperationPageDTO {


    private double accountBalance;



    private int currentPage;

    private int size;

    private long totalElements;

    private int totalPages;

    private List<AccountOperationDTO> operations;





}
