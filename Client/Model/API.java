package Client.Model;

import Common.Commands;
import Common.Comment;
import Common.Post;
import Common.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class API {
    public static boolean isUserNameExists(String username2check){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.UsernameUnique);
        toSend.put("username",username2check);
        Map<String,Object> toReceive = Network.serve(toSend);
        return (boolean) toReceive.get("answer");
    }

    public static User login(String username , String password){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.Login);
        toSend.put("username",username);
        toSend.put("password",password);
        Map<String,Object> toReceive = Network.serve(toSend);
        return (User)toReceive.get("answer");
    }

    public static Boolean signUp(User user){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.SingUp);
        toSend.put("user", user);
        Map<String,Object> toReceive = Network.serve(toSend);
        return (Boolean) toReceive.get("answer");
    }

    public static boolean ForgetPass(String username , String newPassword){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.ForgetPass);
        toSend.put("username" , username);
        toSend.put("newPassword" , newPassword);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (boolean)toReceive.get("answer");
    }

    public static boolean addPost(User user , Post post){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.addPost);
        toSend.put("user" , user);
        toSend.put("post" , post);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (boolean) toReceive.get("answer");
    }

    public static Boolean addComment(Post post , Comment comment){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.addComment);
        toSend.put("post" , post);
        toSend.put("comment" , comment);
        Map<String,Object> toReceive = Network.serve(toSend);
        return (Boolean) toReceive.get("answer");
    }

    public static Vector<Comment> getComments(Post post){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.getComments);
        toSend.put("post" , post);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (Vector<Comment>)toReceive.get("answer");
    }

    public static boolean addFollower(User follower , User following){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.addFollower);
        toSend.put("following" , following);
        toSend.put("follower" , follower);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (boolean) toReceive.get("answer");
    }

    public static boolean removeFollower(User follower , User following){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.removeFollower);
        toSend.put("following" , following);
        toSend.put("follower" , follower);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (boolean) toReceive.get("answer");
    }

    public static ArrayList<Post> getPosts(User user){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.getPosts);
        toSend.put("user" , user);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (ArrayList<Post>)toReceive.get("answer");
    }
    public static ArrayList<Post> getAllPosts(String username) {
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.getOthersPosts);
        toSend.put("username" , username);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (ArrayList<Post>)toReceive.get("answer");
    }

    public static ArrayList<User> getUser(String username){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.getUser);
        toSend.put("username" , username);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (ArrayList<User>)toReceive.get("answer");
    }


    public static Boolean Logout(String username){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.Logout);
        toSend.put("username" , username);
        Map<String,Object> toReceive = Network.serve(toSend);
        if ( toReceive.get("answer") == null ) return false;
        return (Boolean) toReceive.get("answer");
    }

    public static Boolean updateInfo(User user){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.UpdateProfile);
        toSend.put("user", user);
        Map<String,Object> toReceive = Network.serve(toSend);
        if ( toReceive.get("answer") == null ) return false;
        return (Boolean) toReceive.get("answer");
    }

    public static Boolean like(Post post , User user){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.Like);
        toSend.put("post" , post);
        toSend.put("user" , user);
        Map<String,Object> toReceive = Network.serve(toSend);
        return (Boolean) toReceive.get("answer");
    }

    public static Boolean unlike(Post post , User user){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.Unlike);
        toSend.put("post" , post);
        toSend.put("user" , user);
        Map<String,Object> toReceive = Network.serve(toSend);
        return (Boolean) toReceive.get("answer");
    }

    public static Boolean Repost(Post post , User user){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.Repost);
        toSend.put("post" , post);
        toSend.put("user" , user);
        Map<String,Object> toReceive = Network.serve(toSend);
        return (Boolean) toReceive.get("answer");
    }

    public static Boolean deleteAccount(User user , String username){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Commands.deleteAccount);
        toSend.put("username" , username);
        toSend.put("user" , user);
        Map<String,Object> toReceive = Network.serve(toSend);
        return (Boolean) toReceive.get("answer");
    }

    public static Integer getFollowerNumber(String username){
        Map<String , Object> toSend= new HashMap<>();
        toSend.put("command" , Commands.FollowerNumber);
        toSend.put("username" , username);
        Map<String , Object> received= Network.serve(toSend);
        return (Integer) received.get("answer");
    }

    public static Integer getFollowingNumber(String username) {
        Map<String , Object> toSend= new HashMap<>();
        toSend.put("command" , Commands.FollowingNumber);
        toSend.put("username" , username);
        Map<String , Object> received= Network.serve(toSend);
        return (Integer) received.get("answer");
    }


}