package Server;

import Common.Commands;

import java.io.EOFException;
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
            Map<String,Object> income = null;
            try{
                income = (Map<String,Object>) socketIn.readObject();
                Map<String,Object> answer = null;
                Commands command = (Commands) income.get("command");
                switch(command){
                    case UsernameUnique:
                        answer = API.isUserNameFound(income);
                        break;
                    case Login:
                        answer = API.login(income);
                        break;
                    case SingUp:
                        answer = API.signUp(income);
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
