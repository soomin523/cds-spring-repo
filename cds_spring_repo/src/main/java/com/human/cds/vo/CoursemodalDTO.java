package com.human.cds.vo;

import lombok.Data;

@Data
public class CoursemodalDTO {
    private String title;
    private String map_x;
    private String map_y;
    private String overview;
    private String first_image;  // 이미지 필드 추가
}
