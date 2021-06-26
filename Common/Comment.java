package Common;

import java.io.Serializable;

public class Comment implements Serializable {
    public String text;
    public User user;

    public Comment() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
