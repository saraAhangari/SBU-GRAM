package Common;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Post implements Serializable {
    private final String writer = null;
    private final String title = null;
    private final String description= null;
    private final Image image= null;

    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getDescription() {
        return description;
    }

    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        if(o==null || getClass()!=o.getClass()){
            return false;
        }
        Post post=(Post) o;
        return (Objects.equals(getTitle() , ((Post) o).getTitle()) && Objects.equals(getDescription() , ((Post) o).getDescription()));
    }

    public void setTitle(String s) {
    }

    public void setDescription(String s) {
    }

    public void setWriter(String s) {
    }
}
