package com.amsidh.mvc;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DomainList {
  private List<Domain> domains;
}
