package com.mayur.ReactiveApplication.controller;

import com.mayur.ReactiveApplication.model.DashboardDetails;
import com.mayur.ReactiveApplication.repository.DashboardRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by MAYUR on 8/4/20.
 */
@RestController
@Api(value = "Reactive real time dashboard", description = "Reactive real time dashboard")
@RequestMapping(value = "/dashboard")
public class DashboardController {

  private DashboardRepository dashboardRepository;

  @Autowired
  public DashboardController(DashboardRepository dashboardRepository) {
    this.dashboardRepository = dashboardRepository;
  }

  @PostMapping(value = "/save")
  @ResponseStatus(HttpStatus.CREATED)
  public void postDashboardDetails(@RequestBody DashboardDetails dashboardDetails) {
    dashboardRepository.save(dashboardDetails).subscribe();
  }

  @PutMapping(value = "/update")
  @ResponseStatus(HttpStatus.OK)
  public void updateDashboardDetails(@RequestBody DashboardDetails dashboardDetails) {
    dashboardRepository.save(dashboardDetails).subscribe();
  }

  @DeleteMapping(value = "/delete")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteDashboardDetails(@RequestBody DashboardDetails dashboardDetails) {
    dashboardRepository.deleteById(dashboardDetails.getId()).subscribe();
  }

  @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<DashboardDetails> streamDashboardDetails(@RequestParam String hubName) {
    return dashboardRepository.findByHubName(hubName);
  }
 
//  @GetMapping("/stream-sse-mvc")
//  public SseEmitter streamSseMvc() {
//    SseEmitter emitter = new SseEmitter();
//    ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
//    sseMvcExecutor.execute(() -> {
//      try {
//        for (int i = 0; true; i++) {
//          SseEmitter.SseEventBuilder event = SseEmitter.event()
//              .data("SSE MVC - " + LocalTime.now().toString())
//              .id(String.valueOf(i))
//              .name("sse event - mvc");
//          emitter.send(event);
//          Thread.sleep(1000);
//        }
//      } catch (Exception ex) {
//        emitter.completeWithError(ex);
//      }
//    });
//    return emitter;
//  }
}
