package com.web.base.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkDay {

    private String date;
    private String name;
    private String holiday;
    private String wage;
    private String target;

}
