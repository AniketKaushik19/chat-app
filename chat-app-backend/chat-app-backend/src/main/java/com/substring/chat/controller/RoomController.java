package com.substring.chat.controller;

import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.substring.chat.Repositories.RoomRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    private RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId){
        System.out.println(roomId);
        if(roomRepository.findByRoomId(roomId)!=null){
            //if room is already exists
            return ResponseEntity.badRequest().body("Room Already exists.");
         }
        //create new room
        Room room=new Room();
        roomId = roomId.replace("\"", "").trim();  // Removes extra double quotes
        room.setRoomId(roomId);
        Room savedRoom=roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    //get room:join
     @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId){
        Room room =roomRepository.findByRoomId(roomId);
        if(room!=null){
            return  ResponseEntity.ok(room);
        }
        
        return  ResponseEntity.badRequest().body("Room not Found!");
    }

    //get messages of room
    @GetMapping("/{roomId}/messages")
    public  ResponseEntity<List<Message>>getMessage(
        @PathVariable String roomId,
        @RequestParam(value="page",defaultValue = "0",required = false) int page,
        @RequestParam(value = "size",defaultValue = "20" ,required=false) int size
        )
       {
           Room room=roomRepository.findByRoomId(roomId);
           if(room!=null){
               //get message
               //pagination
               List<Message> messages=room.getMessages();
               int start=Math.max(0,messages.size()-(page+1)*size);
               int end=Math.min(messages.size(),start+size);
               List<Message> paginatedMessage=messages.subList(start,end);
               return ResponseEntity.ok(messages);
           }

//
           return ResponseEntity.badRequest().build();
    }
}
