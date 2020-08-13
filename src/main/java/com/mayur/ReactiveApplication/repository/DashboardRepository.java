package com.mayur.ReactiveApplication.repository;

import com.mayur.ReactiveApplication.model.DashboardDetails;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Created by MAYUR on 8/4/20.
 */
@Repository
public interface DashboardRepository extends ReactiveMongoRepository<DashboardDetails,String> {

  //@Tailable
  Flux<DashboardDetails> findWithTailableCursorById(String id);

  @Tailable
  Flux<DashboardDetails> findByHubName(String hubName);
}
