package Server;

import Common.Commands;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable{
    private Socket userSocket;
    private ObjectOutputStream socketOut;
    private ObjectInputStream socketIn;
    public Boolean clientOnline = true;

    public ClientHandler(Socket socket){
        try {
            this.userSocket = socket;
            this.socketIn = new ObjectInputStream(socket.getInputStream());
            this.socketOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(clientOnline){
            Map<String,Object> input;
            try{
                input = (Map<String,Object>) socketIn.readObject();
                Map<String,Object> answer = null;
                Commands command = (Commands) input.get("command");
                switch(command){
                    case UsernameUnique:
                        answer = API.isUserNameExists(input);
                        break;
                    case Login:
                        answer = API.login(input);
                        break;
                    case SingUp:
                        answer = API.signUp(input);
                        break;
                    case ForgetPass :
                        answer = API.ForgetPass(input);
                        break;
                    case addPost:
                        answer = API.addPost(input);
                        break;
                    case getPosts:
                        answer = API.getPosts(input);
                        break;
                    case getUser:
                        answer = API.getUser(input);
                        break;
                    case UpdateProfile:
                        answer = API.updateInfo(input);
                        break;
                    case addFollower:
                        answer = API.addFollower(input);
                        break;
                    case getselfPosts:
                        answer = API.getselfPosts(input);
                        break;
                }
                socketOut.writeObject(answer);
                socketOut.flush();
            } catch(ClassCastException | ClassNotFoundException | IOException e){
                e.printStackTrace();
            }

        }
        // after logout!
        try{
            socketIn.close();
            socketOut.close();
            userSocket.close();
        }catch(IOException e){}

    }
}
