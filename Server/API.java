package Server;

import Common.Commands;
import Common.User;
import java.util.HashMap;
import java.util.Map;

public class API {
    public static Map<String,Object> isUserNameFound(Map<String,Object> input){
        String username2check = (String) input.get("username");
        User user = Server.users.get(username2check);
        Boolean exists = (user != null);

        Map<String,Object> answer = new HashMap<>();
        answer.put("answer",exists);
        answer.put("command",Commands.UsernameUnique);

        return answer;
    }

    public static Map<String,Object> signUp(Map<String,Object> input){
        String username2check = (String) input.get("username");
        User user = Server.users.get(username2check);
        Boolean exists = (user != null);

        Database.updateDataBase();
        Map<String,Object> answer = new HashMap<>();
        answer.put("answer",exists);
        answer.put("command",Commands.SingUp);

        return answer;
    }

    public static Map<String , Object> login(Map<String , Object> input){
        String username = (String)input.get("username");
        String password = (String)input.get("password");
        Map<String , Object> answer=new HashMap<>();

        User user=Server.users.get(username);
        answer.put("command" , Commands.Login);
        answer.put("exists" , (user!=null));
        if(user==null){
            return answer;
        }
        answer.put("answer" , user);

        return answer;
    }
}
