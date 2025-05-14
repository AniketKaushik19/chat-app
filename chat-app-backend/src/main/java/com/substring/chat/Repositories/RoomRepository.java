package com.substring.chat.Repositories;
import com.substring.chat.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RoomRepository extends MongoRepository<Room,String> {
Room findByRoomId(String roomId);
//  get room using roomId

}
