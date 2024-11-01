package com.gingercookee.goty.domain.topic.controller;

import com.gingercookee.goty.domain.topic.dto.TopicRequestDto;
import com.gingercookee.goty.domain.topic.dto.TopicResponseDto;
import com.gingercookee.goty.domain.topic.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class TopicController {
    private final TopicService topicService;
    @GetMapping("/{appId}/topicChart/{sentiment}/{month}")
    @ResponseBody
    public ResponseEntity<List<TopicResponseDto>> getTopicResult(@PathVariable Long appId, @PathVariable String sentiment, @PathVariable String month){
        return ResponseEntity.ok(topicService.getTopicResult(appId, sentiment, month));
    }
}
