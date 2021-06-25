package Client.Model;

import Common.Commands;
import Common.Post;
import Common.User;
import Server.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public static boolean addPost(String username , Post post){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.addPost);
        toSend.put("username" , username);
        toSend.put("post" , post);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (boolean) toReceive.get("answer");
    }

    public static boolean addFollower(User follower , User following){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.addFollower);
        toSend.put("following" , following);
        toSend.put("follower" , follower);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (boolean) toReceive.get("answer");
    }

    public static ArrayList<Post> getPosts(String username){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.getPosts);
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
}