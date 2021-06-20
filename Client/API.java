package Client;

import java.util.HashMap;
import java.util.Map;
import Common.*;


public class API {
    public static boolean isUserNameExists(String username2check){
        Map<String,Object> message = new HashMap<>();
        message.put("command", Commands.UsernameUnique);
        message.put("username",username2check);
        Map<String,Object> received = ClientNetworker.serve(message);
        return (boolean) received.get("answer");
    }


    public static Boolean signUp(User user){
        Map<String,Object> message = new HashMap<>();
        message.put("command", Commands.SingUp);
        message.put("user", user);
        Map<String,Object> received = ClientNetworker.serve(message);
        if (received.get("message") == null){
            return null;
        }
        return (Boolean) received.get("answer");
    }

    public static User login(String username , String password){
        Map<String,Object> message = new HashMap<>();
        message.put("command", Commands.Login);
        message.put("username",username);
        message.put("password",password);
        Map<String,Object> received = ClientNetworker.serve(message);
        if ( received.get("message") == null ){
            return null;
        }
        return (User)received.get("answer");
    }

    public static boolean ForgetPass(String username , String secQuestion , String secAnswer){
        Map<String , Object> message =new HashMap<>();
        message.put("command" , Commands.ForgetPass);
        message.put("username" , username);
        message.put("secQuestion" , secQuestion);
        message.put("secAnswer" , secAnswer);
        Map<String , Object> received = ClientNetworker.serve(message);
        return (boolean) received.get("answer");
    }

    public static boolean changePassword(String username , String newPass){
        Map<String , Object> message = new HashMap<>();
        message.put("command" , Commands.ChangePass);
        message.put("username" , username);
        message.put("newPass" , newPass);
        Map<String , Object> received = ClientNetworker.serve(message);
        return (boolean)received.get("answer");
    }
}
