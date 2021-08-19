package com.billo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Set;

@Entity
@Table(name = "tbluser")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

   @JsonIgnore
   @Id
   @Column(name = "userno", length = 50, unique = true)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int userno;

   @Column(name = "userid", length = 50, unique = true)
   private String userid;

   @Column(name = "username", length = 50, unique = true)
   private String username;

   @JsonIgnore
   @Column(name = "password", length = 100)
   private String password;

   @Column(name = "nickname", length = 50)
   private String nickname;

   @Column(name = "phone", length = 50)
   private String phone;

   @Column(name = "email", length = 50)
   private String email;

   @Column(name = "zipcode", length = 50)
   private String zipcode;

   @Column(name = "mainaddress", length = 50)
   private String mainaddress;

   @Column(name = "subaddress", length = 50)
   private String subaddress;

   @Column(name = "useyn", length = 50)
   private String useyn;

   @Column(name = "regdate", length = 50)
   private String regdate;

   @Column(name = "chagedate", length = 50)
   private String chagedate;

   @Column(name = "logindate", length = 50)
   private String logindate;

   @ManyToMany
   @JoinTable(
           name = "tblauthority",
           joinColumns = {@JoinColumn(name = "userno", referencedColumnName = "userno")},
           inverseJoinColumns = {@JoinColumn(name = "authorityname", referencedColumnName = "authorityname")})
   private Set<Authority> authorities;
}
