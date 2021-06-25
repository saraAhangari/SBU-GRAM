package Common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Post implements Serializable {
    public String writer;
    public String title;
    public String description;
    public int like;
    public int repost;
    private final LocalDateTime dateWithTime;
    public ArrayList<User> likers = new ArrayList<>();
    private final Map<String , Post> comments = new ConcurrentHashMap<>();
    public byte[] image;

    public void likePost(User liker) {
        if (!likers.contains(liker)){
            likers.add(liker);
            like++;
        }
    }

    public void unlikePost(User unliker) {
        if (likers.contains(unliker)){
            likers.remove(unliker);
            like--;
        }
    }

    public void setReposts(User reposter , Post post) {
        if (!reposter.getPosts().contains(post)) {
            reposter.addPost(post);
            repost++;
        }
    }

    public void addComment(String comment) {
        comments.put(comment , this);
    }

    public Post() {
        this.dateWithTime=LocalDateTime.now();
        like = 0;
        repost = 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getRepost() {
        return repost;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LocalDateTime getDateWithTime() {
        return dateWithTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return writer.equals(post.writer) && title.equals(post.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(writer, title);
    }


    public int compareTo(Object o) {
        return this.getDateWithTime().compareTo(((Post) o).getDateWithTime());
    }
}