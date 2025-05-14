package com.substring.chat.controller;


import com.substring.chat.Repositories.RoomRepository;
import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.playload.MessageRequest;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import java.lang.Exception;
import java.time.LocalDateTime;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class chatController {
    private RoomRepository roomRepository;

    public chatController(RoomRepository roomRepository){
        this.roomRepository=roomRepository;
    }

//    for sending and receiving messsages
    @MessageMapping("sendMessage/{roomId}")
    @SendTo("topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request) throws Exception {
        Room room =roomRepository.findByRoomId(request.getRoomId());

        System.out.println(roomId+request.getContent()+request.getSenderName()+request.getRoomId());
        Message message=new Message(request.getSenderName(),request.getContent());
               message.setContent(request.getContent());
               message.setSender(request.getSenderName());
               message.setTimestamp(LocalDateTime.now());
               System.out.println();
               if(room!=null){
                 room.getMessages().add(message);
                 roomRepository.save(room);
            System.out.println(room);

               }
               else {
                   throw new RuntimeException("Room not found");
               }
               return message;
    }
}
