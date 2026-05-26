package com.storygen.service;

import com.storygen.model.StoryRequest;
import com.storygen.model.StoryResponse;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StoryService {

    @Autowired
    private ChatClient chatClient;

    private static final String STORY_PROMPT = """
            You are a warm, wise storyteller who creates short moral stories for children.
            
            Generate a children's story with the following details:
            - Core Value/Lesson: {value}
            - Age Group: {ageGroup} years old
            - Language: {language}
            - Child's Name (use as main character if provided): {childName}
            
            Rules for the story:
            1. Keep it short — 8 to 12 sentences only
            2. Use simple words appropriate for the age group
            3. Include an animal, child, or relatable character
            4. The character must face a real temptation or challenge related to the value
            5. Show clearly how choosing the right value leads to a good outcome
            6. Make it emotional and memorable, not preachy
            7. Write entirely in {language} language
            8. If child's name is provided, make them the hero of the story
            
            Respond in this EXACT format (no extra text):
            TITLE: [story title]
            STORY: [the full story]
            MORAL: [one powerful sentence moral]
            """;

    public StoryResponse generateStory(StoryRequest request) {
        String childName = (request.getChildName() == null || request.getChildName().isBlank())
                ? "a young child"
                : request.getChildName();

        PromptTemplate promptTemplate = new PromptTemplate(STORY_PROMPT);
        Prompt prompt = promptTemplate.create(Map.of(
                "value", request.getValue(),
                "ageGroup", request.getAgeGroup(),
                "language", request.getLanguage(),
                "childName", childName
        ));

        String response = chatClient.call(prompt).getResult().getOutput().getContent();

        return parseResponse(response, request);
    }

    private StoryResponse parseResponse(String raw, StoryRequest request) {
        String title = extractSection(raw, "TITLE:");
        String story = extractSection(raw, "STORY:");
        String moral = extractSection(raw, "MORAL:");

        return new StoryResponse(
                title,
                story,
                moral,
                request.getValue(),
                request.getLanguage(),
                request.getAgeGroup()
        );
    }

    private String extractSection(String raw, String key) {
        try {
            int start = raw.indexOf(key) + key.length();
            String[] lines = raw.substring(start).split("\n");
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                if (line.contains("TITLE:") || line.contains("STORY:") || line.contains("MORAL:")) break;
                sb.append(line.trim()).append(" ");
            }
            return sb.toString().trim();
        } catch (Exception e) {
            return "Could not parse section: " + key;
        }
    }
}
