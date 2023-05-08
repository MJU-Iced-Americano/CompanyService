package com.mju.company.presentation.controller;

import com.mju.company.domain.service.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@Slf4j
@RequestMapping("/company-service")
public class AlarmController {

    public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    public Map<String, SseEmitter> mapEmitters = new ConcurrentHashMap<>();

    // method for client subscription
    @CrossOrigin
    @GetMapping(value = "/review/ring")
    public SseEmitter subscribe(@RequestParam String lecturerId) {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sendInitEvent(sseEmitter);

        logFormListEmitters(sseEmitter);
        logFormMapEmitters(lecturerId, sseEmitter);

        return sseEmitter;
    }

    // method for dispatching events to specific clients
    @CrossOrigin
    @PostMapping(value = "/dispatchEventToSpecific")
    public void dispatchEventToSpecificClients(@RequestParam String title, @RequestParam String text, @RequestParam Long lecturerId){
        Map<String, String> mapForJson = Map.of("Title", title, "Text", text);
        SseEmitter sseEmitter = mapEmitters.get(lecturerId);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(SseEmitter.event().name("latestNews").data(mapForJson));
                log.info("PostMapping for specific clients SSE Emitters : {}", mapEmitters);
                logFormListEmitters(sseEmitter);
            } catch (IOException e) {
//                emitters.remove(sseEmitter);
                mapEmitters.remove(lecturerId);
                log.info("Exception SSE Emitters : {}", mapEmitters);
            }
        }
    }

    private void sendInitEvent(SseEmitter sseEmitter) {
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logFormListEmitters(SseEmitter sseEmitter) {
        log.info("------------------All Clients-----------------");
        log.info("first SSE Emitters : {}", emitters);

        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        log.info("onCompletion remove SSE Emitters : {}", emitters);

        emitters.add(sseEmitter);
        log.info("after add SSE Emitters : {}", emitters);
    }

    private void logFormMapEmitters(String lecturerId, SseEmitter sseEmitter) {
        log.info("------------------SpecificClients-----------------");
        log.info("first SSE Emitters : {}", mapEmitters);
        mapEmitters.put(lecturerId, sseEmitter);
        log.info("map SSE Emitters : {}", mapEmitters);


        sseEmitter.onCompletion(() -> mapEmitters.remove(lecturerId));
        log.info("onCompletion remove SSE Emitters : {}", mapEmitters);

        sseEmitter.onTimeout(() -> mapEmitters.remove(lecturerId));
        log.info("onTimeout remove SSE Emitters : {}", mapEmitters);

        sseEmitter.onError((e) -> mapEmitters.remove(lecturerId));
        log.info("onError remove SSE Emitters : {}", mapEmitters);
    }
}
