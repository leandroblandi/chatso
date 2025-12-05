package org.chatso.usersservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserEntity {

    @Id
    private String uuid;

    @Column(unique = true, nullable = false, updatable = false)
    private String username;
    private String displayName;
    private String biography;
    private String avatarUrl;

    @Builder.Default
    private boolean active = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
