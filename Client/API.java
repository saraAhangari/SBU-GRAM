package Client;

import java.util.HashMap;
import java.util.Map;
import Common.Commands;

public class API {
    public static boolean isUserNameExists(String username2check){
        Map<String,Object> toSend = new HashMap<>();
        Map<String,Object> received;
        Object answer;
        toSend.put("command", Commands.UsernameUnique);
        toSend.put("username",username2check);
        received = ClientNetworker.serve(toSend);
        answer = received.get("answer");
        return (boolean) answer;
    }

    public static boolean signUp(String username , String password) {
        Map<String, Object> toSend = new HashMap<>();
        Map<String, Object> received;
        Object answer;
        if (isUserNameExists(username))
            return false;
        else {
            toSend.put("command", Commands.SingUp);
            toSend.put("username", username);
            toSend.put("password", password);
            received = ClientNetworker.serve(toSend);
            answer = received.get("answer");
            return (boolean) answer;
        }
    }

    public static boolean login(String username2check , String pass2check){
        Map<String,Object> toSend = new HashMap<>();
        Map<String,Object> received;
        Object answer;
        if (!isUserNameExists(username2check))
            return false;
        else {
            toSend.put("command", Commands.Login);
            toSend.put("username", username2check);
            toSend.put("password", pass2check);
            received = ClientNetworker.serve(toSend);
            answer = received.get("answer");
            return (boolean) answer;
        }
    }
}
