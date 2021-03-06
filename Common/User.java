package Common;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String password;
    private String firstname;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String birthDate;
    private String SecurityQuestion;
    private String SecurityAnswer;
    private String photoPath;
    private byte[] profileImage ;
    private ArrayList<Post> posts = new ArrayList<>(); //post haye khodesh
    private ArrayList<Post> allPosts = new ArrayList<>(); //post haye followings
    private ArrayList<User> followers = new ArrayList<>();
    private ArrayList<User> followings = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    public User(){
    }

    public ArrayList<Post> getAllPosts() {
        return allPosts;
    }

    public void addFollowingPosts(Post followingPost) {
        allPosts.add(followingPost);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public byte[] getProfilePhoto() {
        return profileImage;
    }
    public void setProfilePhoto(byte[] profilePhoto) {
        this.profileImage = profilePhoto;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSecurityQuestion() {
        return SecurityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        SecurityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return SecurityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        SecurityAnswer = securityAnswer;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }


    public ArrayList<User> getFollowings() {
        return followings;
    }


    public User Submission(String username , String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return this;
        }
        return null;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        try {
            return this.username.equals(((User)obj).getUsername());
        } catch (Exception e) {
            return false;
        }
    }
}