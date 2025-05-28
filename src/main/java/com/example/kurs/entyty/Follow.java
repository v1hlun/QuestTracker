package com.example.kurs.entyty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "follows", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "target_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "follower_id", nullable = false)
    private Long followerId;
    
    @Column(name = "target_id", nullable = false)
    private Long targetId;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    // Можно добавить связи с таблицей Player, если нужно
    //@ManyToOne
    //@JoinColumn(name = "follower_id", insertable = false, updatable = false)
    //private Player follower;
    
    //@ManyToOne
    //@JoinColumn(name = "target_id", insertable = false, updatable = false)
    //private Player target;
} 