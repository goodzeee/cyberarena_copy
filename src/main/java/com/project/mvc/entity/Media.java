package com.project.mvc.entity;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    @Setter
    private String mediaTitle;
    private String creator;
    private double rating;
    private String mediaDesc;
    private int createdAt;
    private String imageUrl;
}
