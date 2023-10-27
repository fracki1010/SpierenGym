package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Detail;
import com.spieren.spierengym.models.Exercise;

public class DetailDTO {
    private Long id;
    private int repeat;
    private int weight;
    private int series;

    public DetailDTO(Detail detail) {
        this.id = detail.getId();
        this.repeat = detail.getRepeat();
        this.weight = detail.getWeight();
        this.series = detail.getSeries();
    }

    public Long getId() {
        return id;
    }

    public int getRepeat() {
        return repeat;
    }

    public int getWeight() {
        return weight;
    }

    public int getSeries() {
        return series;
    }
}
