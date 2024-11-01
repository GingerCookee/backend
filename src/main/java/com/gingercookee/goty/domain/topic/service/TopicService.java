package com.gingercookee.goty.domain.topic.service;

import com.gingercookee.goty.domain.review.repository.ReviewRepository;
import com.gingercookee.goty.domain.topic.dto.TopicRequestDto;
import com.gingercookee.goty.domain.topic.dto.TopicResponseDto;
import com.gingercookee.goty.domain.topic.entity.Topic;
import com.gingercookee.goty.domain.topic.repository.TopicRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final ReviewRepository reviewRepository;

    public List<TopicResponseDto> getTopicResult(Long appId, String sentiment, String month) {
        Map<String, Integer> sentimentMap = new HashMap<>() {{
            put("pos", 1);
            put("neg", -1);
        }};
        String[] parts = month.split("-");
        int monthValue = Integer.parseInt(parts[1]);

        System.out.println(sentimentMap.get(sentiment));
        List<Topic> topics = reviewRepository.findDistinctTopicsByAppIdAndMonthAndSentiment(appId, sentimentMap.get(sentiment), monthValue);

        System.out.println(topics);
        return reviewRepository.findDistinctTopicsByAppIdAndMonthAndSentiment(appId, sentimentMap.get(sentiment), monthValue).stream()
                .map(TopicResponseDto::new) // Topic 객체를 TopicResponseDto로 변환
                .collect(Collectors.toList());


    }
}
