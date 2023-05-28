package com.project.ably.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Folder {
    private int folderNo;
    private String folderName;
    private String email;
    private String serviceName;
    private String registrationDate;

    @Builder.Default
    private String defaultYn = "N";
}
