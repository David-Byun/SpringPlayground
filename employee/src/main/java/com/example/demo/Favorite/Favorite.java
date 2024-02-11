package com.example.demo.Favorite;

import com.example.demo.Room.Room;
import com.example.demo.User.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    private boolean isClicked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Builder
    public Favorite(User user, Room room, boolean isClicked) {
        this.user = user;
        if (user != null) {
            user.getFavorites().add(this);
        }
        this.room = room;
        if (room != null) {
            room.getFavorites().add(this);
        }
        this.isClicked = isClicked;
    }

    public void changeFavoriteClickStatus() {
        this.isClicked = !isClicked;
    }
}
