package Client.Model;

import Common.Commands;
import Common.Post;
import Common.User;

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

    public static ArrayList<Post> addPost(Post post){
        Map<String , Object> toSend = new HashMap<>();
        toSend.put("command" , Commands.addPost);
        toSend.put("post" , post);
        Map<String , Object> toReceive = Network.serve(toSend);
        return (ArrayList<Post>) toReceive.get("answer");
    }
}