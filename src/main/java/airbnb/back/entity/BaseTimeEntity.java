package airbnb.back.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseTimeEntity {

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
