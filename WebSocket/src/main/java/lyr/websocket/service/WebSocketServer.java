package lyr.websocket.service;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName WebSocketServer
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/15 17:18
 * @Version 1.0
 **/

@ServerEndpoint(value = "/websocket/{sid}")
@Component
public class WebSocketServer {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    private static Map<String,CopyOnWriteArraySet<Session>> webSocketMap = new HashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {
        this.session = session;
        addOnlineCount();           //在线数加1
        System.out.println("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        this.sid=sid;
        addSession(session,sid);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("sid") String sid) {
        this.session = session;
        removeSession(session,sid);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("sid") String sid) {
        System.out.println("收到来自窗口"+sid+"的信息:"+message);
        this.session = session;
        try {
            sendMessageBySid(message,sid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error, @PathParam("sid") String sid) {
        this.session =session;
        removeSession(session, sid);
        error.printStackTrace();
    }
    /**
     * 推送消息到对应的sid
     */
    public void sendMessageBySid(String message,String sid) throws IOException {
        CopyOnWriteArraySet<Session> array = webSocketMap.get(sid);
        if(array !=null && array.size()>0){
            for (Session item : array) {
                item.getBasicRemote().sendText(message);
            }
        }
    }


    /**
     * 群发自定义消息
     * */
    public void sendAllMessage(String message) throws IOException {
        if(webSocketMap.size()>0){
            for (String sid : webSocketMap.keySet()) {
                sendMessageBySid(message,sid);
            }
        }
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    private static synchronized void addSession(Session session, String sid) {
        CopyOnWriteArraySet<Session> array = webSocketMap.get(sid);
        if (array == null || array.size() == 0) {
            array = new CopyOnWriteArraySet<>();
        }
        array.add(session);
        webSocketMap.put(sid, array);
    }

    private static synchronized void removeSession(Session session, String sid) {
        if (webSocketMap.size() > 0) {
            CopyOnWriteArraySet<Session> array = webSocketMap.get(sid);
            if (array != null && array.size() > 0) {
                array.remove(session);
//                webSocketMap.put(sid, array);
            }
        }
        subOnlineCount();           //在线数减1
        System.out.println(""+sid+"连接关闭！当前在线人数为" + getOnlineCount());
    }
}
