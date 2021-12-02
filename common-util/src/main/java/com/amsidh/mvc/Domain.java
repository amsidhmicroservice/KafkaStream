package com.amsidh.mvc;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Domain {
    private String domain;
    private String create_date;
    private String update_date;
    private String country;
    private boolean isDead;
    private String A;
    private String NS;
    private String CNAME;
    private String MX;
    private String TXT;
}
