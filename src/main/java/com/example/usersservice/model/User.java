package com.example.usersservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private String email;

  @Column
  private String title;

  @Column
  private String firstname;

  @Column
  private String lastname;

  @Column
  private String password;

  @Column
  private String phone;

  public UserResource toUserResource() {
    return UserResource
      .builder()
      .id(this.id)
      .email(this.email)
      .title(this.title)
      .firstname(this.firstname)
      .lastname(this.lastname)
      .phone(this.phone)
      .build();
  }
}
