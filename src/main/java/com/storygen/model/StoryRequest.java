package com.storygen.model;

import lombok.Data;

@Data
public class StoryRequest {
    private String value;       // e.g., Loyalty, Honesty, Courage
    private String ageGroup;    // e.g., "5-7", "8-10", "11-13"
    private String language;    // e.g., "English", "Hindi", "Telugu"
    private String childName;   // optional personalization
}
