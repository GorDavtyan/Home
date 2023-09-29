package roomRegistation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The `RegistrationRoom` class provides a way to create and add different types of hotel rooms.
 * It implements the Serializable interface for object serialization.
 */
public class RegistrationRoom implements Serializable {


    /**
     * Create and return a hotel room based on the provided index.
     *
     * @param index The index representing the type of room to create.
     * @return A new `Room` object with the specified attributes, or null if the index is invalid.
     */
    public Room add(int index) {
        Room room;
        if (index == 1) {
            Room.RoomBuilder roomBuilder = new Room.RoomBuilder(RoomType.SINGLE);
            room = roomBuilder
                    .hasSingleBed(true)
                    .hasBathRoom(true)
                    .hasTV(true)
                    .hasCloset(true)
                    .getPrice(30)
                    .build();
            return room;
        } else if (index == 2) {
            Room.RoomBuilder roomBuilder = new Room.RoomBuilder(RoomType.DOUBLE);
            room = roomBuilder
                    .hasDoubleBed(true)
                    .hasBathRoom(true)
                    .hasTV(true)
                    .hasCloset(true)
                    .getPrice(35)
                    .build();
            return room;
        } else if (index == 3) {
            Room.RoomBuilder roomBuilder = new Room.RoomBuilder(RoomType.DELUXE);
            room = roomBuilder
                    .hasMiniBar(true)
                    .hasBathRoom(true)
                    .hasKingSize(true)
                    .hasSitingArea(true)
                    .getPrice(55)
                    .build();
            return room;
        }
        return null;
    }
}
