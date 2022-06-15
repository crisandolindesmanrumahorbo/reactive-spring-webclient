package com.rumahorbo.asyncwebclient.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo implements Serializable {
    private String todo;
    private boolean completed;
    private int userId;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo c = (Todo) o;
        return this.getTodo().equals(c.getTodo())
                && this.getUserId() == c.getUserId();
    }
}
