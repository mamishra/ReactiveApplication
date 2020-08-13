package com.mayur.ReactiveApplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by MAYUR on 8/4/20.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "dashboard")
public class DashboardDetails {

  @Id
  private String id;
  private String hubName;
  private int pickUpCount;
  private int deliveryCount;
  private int unsuccessfulCount;
  private List<String> activeRunsheets;

}
