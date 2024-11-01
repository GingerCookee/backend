package com.gingercookee.goty.domain.topic.dto;

import com.gingercookee.goty.domain.topic.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TopicResponseDto {
    private Long topicId;
    private int ranking;
    private String representationWord;

    public List<Map<String, String>> getRepresentationWordAsList() {
        // representationWord를 파싱하여 List<Map<String, String>> 형식으로 변환
        return Arrays.stream(representationWord.split("-"))
                .map(part -> {
                    String[] keyValue = part.split(":");
                    return Map.of(keyValue[0], keyValue[1]); // Map을 생성하여 반환
                })
                .collect(Collectors.toList());
    }

    public TopicResponseDto(Topic topic) {
        this.topicId = topic.getTopicId();
        this.ranking = topic.getRanking();
        this.representationWord = topic.getRepresentationWord();
    }
}
