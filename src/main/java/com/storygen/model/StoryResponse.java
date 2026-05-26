package com.storygen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryResponse {
    private String title;
    private String story;
    private String moral;
    private String value;
    private String language;
    private String ageGroup;
}
