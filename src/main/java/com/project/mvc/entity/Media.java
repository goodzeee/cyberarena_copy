package com.project.mvc.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Media {

    private long mediaNo;
    private int categoryNo;
    private String mediaTitle;
    private String creator;
    private double rating;
    private String mediaDesc;
    private int createdAt;
    private String imageUrl;
}
