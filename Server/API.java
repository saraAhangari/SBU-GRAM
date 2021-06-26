package Server;

import Common.Commands;
import Common.Comment;
import Common.Post;
import Common.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
        Database.getInstance().updateDataBase();
        System.out.println("action : login");
        System.out.println("[" + username + "] : has logged in");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String,Object> signUp(Map<String,Object> input){
        Map<String,Object> message = new HashMap<>();
        User user = (User)input.get("user");
        String username = user.getUsername();

        Server.users.put(username , user);
        Server.Profiles.add(user);
        message.put("command" , Commands.SingUp);
        message.put("answer" , Boolean.TRUE);
        Database.getInstance().updateDataBase();
        System.out.println("[" + username +"] : register ");
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
        System.out.println("action : Forget Password");
        System.out.println("[" + username + "] : changed password");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String , Object> addPost(Map<String , Object> input){
        Map<String , Object> message = new HashMap<>();
        User user = (User) input.get("user");
        Post post = (Post)input.get("post");

        Server.users.get(user.getUsername()).addPost(post);
        Server.posts.add(post);
        for (int i = 0; i <Server.posts.size() ; i++) {
            if (Server.posts.get(i).equals(post)){
                Server.posts.get(i).setPublisher(user);
                break;
            }
        }
        message.put("command" , Commands.addPost);
        message.put("answer" , Boolean.TRUE);
        Database.getInstance().updateDataBase();
        System.out.println("[" + user + "]" + " publish");
        System.out.println("message : " +"["+ post.title + "] [" + post.writer +"]" );
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String,Object> addComment(Map<String,Object> input){
        Map<String , Object> message = new HashMap<>();
        Comment comment = (Comment) input.get("comment");
        Post post =(Post) input.get("post");
        for (int i = 0; i <Server.posts.size() ; i++) {
            if (Server.posts.get(i).equals(post)){
                Server.posts.get(i).addComment(comment);
                break;
            }
        }
        Database.getInstance().updateDataBase();
        message.put("command",Commands.addComment);
        message.put("post" , post);
        message.put("comment" , comment);
        message.put("answer", Boolean.TRUE);
        //System.out.println(user.getUsername() + " comment");
        //message post subject
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }


    public static Map<String , Object> addFollower(Map<String , Object> input){
        Map<String , Object> message = new HashMap<>();
        User following = (User)input.get("following");
        User follower = (User) input.get("follower");
        Server.users.get(follower.getUsername()).getFollowings().add(following);
        Server.users.get(following.getUsername()).getFollowers().add(follower);
        for (int i = 0; i <following.getPosts().size() ; i++) {
            Server.users.get(follower.getUsername()).addFollowingPosts(following.getPosts().get(i));
        }
        message.put("command" , Commands.addFollower);
        message.put("following" , following);
        message.put("follower" , follower);
        message.put("answer" , Boolean.TRUE);
        Database.getInstance().updateDataBase();
        System.out.println("action : follow");
        System.out.println("[" + follower.getUsername() + "] followed ");
        System.out.println("message : [" + following.getUsername() +"]");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String , Object> removeFollower(Map<String , Object> input){
        Map<String , Object> message = new HashMap<>();
        User following = (User)input.get("following");
        User follower = (User) input.get("follower");
        Server.users.get(follower.getUsername()).getFollowings().remove(following);
        Server.users.get(following.getUsername()).getFollowers().remove(follower);
        for (int i = 0; i <following.getPosts().size() ; i++) {
            Server.users.get(follower.getUsername()).getAllPosts().remove(following.getPosts().get(i));
        }
        message.put("command" , Commands.removeFollower);
        message.put("following" , following);
        message.put("follower" , follower);
        message.put("answer" , Boolean.TRUE);
        Database.getInstance().updateDataBase();
        System.out.println("action : unfollow");
        System.out.println("[" + follower.getUsername() + "] unfollowed ");
        System.out.println("message : [" + following.getUsername() +"]");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    //get posts return posts + reposts for profile page
    public static Map<String , Object> getPosts(Map<String , Object> input){
        Map<String , Object> message = new HashMap<>();
        User user = (User) input.get("user");
        ArrayList<Post> timeLine = Server.users.get(user.getUsername()).getPosts();
        timeLine = (ArrayList<Post>) timeLine.stream()
                .sorted((p1 , p2) ->-p1.getDateWithTime().compareTo(p2.getDateWithTime()))
                .collect(Collectors.toList());
        message.put("command" , Commands.getPosts);
        message.put("answer" , timeLine);
        Database.getInstance().updateDataBase();
        System.out.println("[" + user.getUsername() + "] get posts list");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    //post haye followings + khodesh ro return mikone
    public static Map<String , Object> getAllPosts(Map<String , Object> input){
        Map<String , Object> message = new HashMap<>();
        User user = Server.users.get((String)input.get("username"));
        ArrayList<Post> timeLine = new ArrayList<>();
        for (int i = 0; i <Server.posts.size() ; i++) {
            if (Server.posts.get(i).getPublisher().equals(user)
            || user.getFollowings().contains(Server.posts.get(i).getPublisher())){
                timeLine.add(Server.posts.get(i));
            }
        }
        timeLine = (ArrayList<Post>) timeLine.stream()
                .sorted((p1 , p2) ->-p1.getDateWithTime().compareTo(p2.getDateWithTime()))
                .collect(Collectors.toList());
        message.put("command" , Commands.getOthersPosts);
        message.put("answer" , timeLine);
        System.out.println("[" + user.getUsername() + "] get posts list");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String , Object> getComments(Map<String , Object> input){
        Map<String , Object> message = new HashMap<>();
        Post post = (Post) input.get("post");
        Vector<Comment> commentArrayList = new Vector<>();
        for (int i = 0; i <Server.posts.size() ; i++) {
            if (Server.posts.get(i).equals(post)){
                commentArrayList.addAll(Server.posts.get(i).getComments());
            }
        }
        message.put("command" , Commands.getComments);
        message.put("answer" , commentArrayList);
        Database.getInstance().updateDataBase();
        //System.out.println(user.getUsername() + " get posts list");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String , Object> getUser(Map<String , Object> input){
        Map<String , Object> message = new HashMap<>();
        User user = Server.users.get((String)input.get("username"));
        ArrayList<User> user_profile = new ArrayList<>();
        user_profile.add(user);
        if (user == null){
            return message;
        }
        message.put("command" , Commands.getUser);
        message.put("answer" , user_profile);
        Database.getInstance().updateDataBase();
        System.out.println("[" + user.getUsername() + "] get info [" + user_profile.get(0).getUsername() + "]");
        System.out.println("message : [" + user_profile.get(0).getUsername() + "]");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String,Object> Logout(Map<String,Object> input){
        Map<String,Object> message = new HashMap<>();
        String username = (String) input.get("username");
        Database.getInstance().updateDataBase();
        message.put("command",Commands.Logout);
        message.put("answer" , Boolean.TRUE);
        System.out.println("[" + username + "] log out");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String,Object> updateInfo(Map<String,Object> input){
        Map<String,Object> message = new HashMap<>();
        User user = (User) input.get("user");
        String username = user.getUsername();
        Server.users.replace(username,user);
        Database.getInstance().updateDataBase();
        message.put("command",Commands.UpdateProfile);
        message.put("answer",Boolean.TRUE);
        System.out.println("[" + username + "] : update info");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String,Object> like(Map<String,Object> input){
        Map<String,Object> message = new HashMap<>();
        Post post = (Post) input.get("post");
        User user = (User) input.get("user");
        for (int i = 0; i <Server.posts.size() ; i++) {
            if (Server.posts.get(i).equals(post)){
                Server.posts.get(i).likePost(user);
                break;
            }
        }
        Database.getInstance().updateDataBase();
        message.put("command",Commands.Like);
        message.put("answer", Boolean.TRUE);
        System.out.println("action : like");
        System.out.println("[" + user.getUsername() + "] like");
        System.out.println("message : [" + user.getUsername() + "][" + post.title + "]");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }
    public static Map<String,Object> unlike(Map<String,Object> input){
        Map<String,Object> message = new HashMap<>();
        Post post = (Post) input.get("post");
        User user = (User) input.get("user");
        for (int i = 0; i <Server.posts.size() ; i++) {
            if (Server.posts.get(i).equals(post)){
                Server.posts.get(i).unlikePost(user);
                break;
            }
        }
        Database.getInstance().updateDataBase();
        message.put("command",Commands.Unlike);
        message.put("answer", Boolean.TRUE);
        System.out.println("action : unlike");
        System.out.println("[" + user.getUsername() + "] unlike");
        System.out.println("message : [" + user.getUsername() + "][" + post.title + "]");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }
    public static Map<String,Object> Repost(Map<String,Object> input){
        Map<String,Object> message = new HashMap<>();
        Post post = (Post) input.get("post");
        User user = (User) input.get("user");
        for (int i = 0; i <Server.Profiles.size() ; i++) {
            if (Server.Profiles.get(i).equals(user)){
                Server.Profiles.get(i).addPost(post);
                break;
            }
        }
        Database.getInstance().updateDataBase();
        message.put("command",Commands.Repost);
        message.put("answer", Boolean.TRUE);
        System.out.println("action : repost");
        System.out.println("[" + user.getUsername() + "] repost");
        System.out.println("message : [" + user.getUsername() + "][" + post.title + "]");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

    public static Map<String,Object> deleteAccount(Map<String,Object> input){
        Map<String,Object> message = new HashMap<>();
        String username = (String) input.get("username");
        User user = (User) input.get("user");
        Server.users.remove(username , user);
        Database.getInstance().updateDataBase();
        message.put("command",Commands.deleteAccount);
        message.put("answer" , Boolean.TRUE);
        System.out.println("[" + username + "] delete account");
        System.out.println("time : " + LocalDateTime.now());
        return message;
    }

}