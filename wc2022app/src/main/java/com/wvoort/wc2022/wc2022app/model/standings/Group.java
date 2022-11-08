package com.wvoort.wc2022.wc2022app.model.standings;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Group {
    private String groupName;

    private List<TeamResult> teamResults =new ArrayList<>();


}
