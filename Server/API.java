package Server;

import Common.Commands;
import Common.User;

import java.util.HashMap;
import java.util.Map;

public class API {
    public static Map<String,Object> isUserNameExists(Map<String,Object> input){
        String username2check = (String) input.get("username");
        User user = Server.users.get(username2check);
        Boolean exists = (user != null);

        Map<String,Object> answer = new HashMap<>();
        answer.put("command", Commands.UsernameUnique);
        answer.put("answer",exists);
        return answer;
    }


    public static Map<String,Object> signUp(Map<String,Object> input){
        Map<String,Object> answer = new HashMap<>();
        User user = (User)input.get("user");
        String username = user.getUsername();

        Server.users.put(username , user);
        Database.updateDataBase();
        answer.put("command" , Commands.SingUp);
        answer.put("answer" , Boolean.TRUE);
        System.out.println(user.getUsername() + " register");
        System.out.println(user.getUsername() + " Login");

        return answer;
    }

    public static Map<String , Object> login(Map<String , Object> input){
        String username = (String)input.get("username");
        String password = (String)input.get("password");
        Map<String , Object> answer=new HashMap<>();
        answer.put("command",Commands.Login);
        answer.put("exists",!(Server.users.get(username) == null));

        User user=Server.users.get(username).Conformity(username , password);
        if(user==null){
            return answer;
        }
        answer.put("answer" , user);
        System.out.println(user.getUsername() + " : SignUp");
        return answer;
    }

    public static Map<String , Object> ForgetPass(Map<String , Object> input){
        String email = (String)input.get("email");
        String question = (String)input.get("securityQuestion");
        String answer = (String)input.get("securityAnswer");
        Map<String , Object> output = new HashMap<>();

        output.put("command" , Commands.ForgetPass);
        if(Server.users.get(email) == null){
            output.put("output" , false);
            return output;
        }
        Boolean exists = Server.users.get(email).toChangePass(email , answer , question);
        output.put("output" , exists);
        return output;
    }

    public static Map<String , Object> ChangePass(Map<String , Object> input){
        String email = (String)input.get("email");
        String newPass = (String)input.get("newPassword");
        Server.users.get(email).setPassword(newPass);
        Database.updateDataBase();
        System.out.println(email + "changed password");

        Map<String , Object> message=new HashMap<>();
        message.put("command" , Commands.ChangePass);
        message.put("message" , Boolean.TRUE);
        return message;
    }
}