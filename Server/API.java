package Server;

import Client.Model.Main;
import Common.Commands;
import Common.Post;
import Common.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class API {
    public static Map<String,Object> isUserNameExists(Map<String,Object> input){
        Map<String,Object> message = new HashMap<>();
        String username2Check = (String) input.get("username");
        User user = Server.users.get(username2Check);
        boolean exists;

        if (user != null)
            exists = true;
        else
            exists = false;

        message.put("answer" , exists);
        message.put("command" , Commands.UsernameUnique);

        return message;
    }

    public static Map<String , Object> login(Map<String , Object> input){
        Map<String , Object> message=new HashMap<>();
        User user;
        String username = (String)input.get("username");
        String password = (String)input.get("password");
        boolean exists;
        if (Server.users.get(username) != null)
            exists = true;
        else
            exists = false;
        message.put("command",Commands.Login);
        message.put("exists",exists);

        user = Server.users.get(username).Submission(username , password);
        if(user==null){
            return message;
        }
        message.put("answer" , user);
        System.out.println("[" + username + "] : has logged in");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String,Object> signUp(Map<String,Object> input){
        Map<String,Object> message = new HashMap<>();
        User user = (User)input.get("user");
        String username = user.getUsername();

        Server.users.put(username , user);
        message.put("command" , Commands.SingUp);
        message.put("answer" , Boolean.TRUE);
        Database.getInstance().updateDataBase();
        System.out.println("[" + username +"] : register " + user.getProfileImagePath());
        System.out.println("time : " + LocalDateTime.now());

        return message;
    }

    public static Map<String , Object> ForgetPass(Map<String , Object> input){
        Map<String , Object> message = new HashMap<>();
        String username = (String)input.get("username");
        String newPassword = (String)input.get("newPassword");

        Server.users.get(username).setPassword(newPassword);
        message.put("command" , Commands.ForgetPass);
        message.put("username" , username);
        message.put("newPassword" , newPassword);
        message.put("answer" , Boolean.TRUE);
        Database.getInstance().updateDataBase();
        System.out.println("[" + username + "] : changed password");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String , Object> addPost(Map<String , Object> input){
        Map<String , Object> message = new HashMap<>();
        String username = (String)input.get("username");
        Post post = (Post)input.get("post");

        Server.users.get(username).addPost(post);
        Server.Posts.add(post);
        message.put("command" , Commands.addPost);
        message.put("post" , post);
        message.put("username" , username);
        message.put("answer" , Boolean.TRUE);
        Database.getInstance().updateDataBase();
        System.out.println(username + " publish");
        System.out.println("message : " +"["+ post.title + "] [" + post.writer +"]" );
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String , Object> getPosts(Map<String , Object> input){
        Map<String , Object> message = new HashMap<>();
        User user = Server.users.get((String)input.get("username"));
        ArrayList<Post> timeLine = new ArrayList<>(user.getPosts());
        if(user.getFollowings() != null){
            user.getFollowings().stream().map(s -> Server.users.get(s).getPosts()).forEach(timeLine::addAll);
        }
        message.put("command" , Commands.getPosts);
        message.put("answer" , timeLine);
        Database.getInstance().updateDataBase();
        System.out.println(user.getUsername() + " get posts list");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

}