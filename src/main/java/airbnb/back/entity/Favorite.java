package airbnb.back.entity;

import airbnb.back.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    private long id;
    private boolean isChecked;
    private String filed;
    @NonNull
    private Room room;
    @NonNull
    private User user;
}
