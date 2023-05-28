package com.project.ably.model.vo;

import java.util.List;

import com.project.ably.model.response.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Wish {
    private int folderNo;
    private int productNo;
    private int wishNo;
    private String folderName;
    private String registrationDate;

    private String serviceName;
    private String email;
    private List<Integer> wishNoList;
    private ProductResponse product;

    public boolean isNotEmptyFolder(){
        return this.folderNo > 0;
    }
}
