package com.billo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tblauthority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

   @Id
   @Column(name = "authorityno", length = 50, unique = true)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long authorityno;

   @Column(name = "userno", length = 50)
   private Long userno;

   @Column(name = "authorityname", length = 50)
   private String authorityname;
}
