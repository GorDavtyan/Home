package roomRegistation;

import java.io.Serializable;

/**
 * The `Room` class represents a hotel room and its attributes. It implements the Serializable interface for object serialization.
 */
public class Room implements Serializable {
    private RoomType roomType;
    private static int ID = 0;
    private int roomID;
    private boolean isAvailable;


    private boolean singleBed;
    private boolean bathRoom;
    private boolean TV;
    private boolean closet;
    private double price;
    private boolean doubleBed;
    private boolean miniBar;
    private boolean batHub;
    private boolean kingSizeBed;
    private boolean sittingArea;

    private Room() {
    }


    /**
     * The `RoomBuilder` class is used to construct instances of the `Room` class with various attributes.
     */
    public static class RoomBuilder {

        private RoomType roomType;

        private int roomID;
        private boolean isAvailable;


        private boolean singleBed;
        private boolean bathRoom;
        private boolean TV;
        private boolean closet;
        private double price;
        private boolean doubleBed;
        private boolean miniBar;
        private boolean batHub;
        private boolean kingSizeBed;
        private boolean sittingArea;


        public RoomBuilder(RoomType roomType) {
            this.roomType = roomType;
            this.roomID = ++ID;
            this.isAvailable = true;
        }

        public RoomBuilder hasSingleBed(boolean singleBed) {
            this.singleBed = singleBed;
            return this;
        }

        public RoomBuilder hasBathRoom(boolean bathRoom) {
            this.bathRoom = bathRoom;
            return this;
        }

        public RoomBuilder hasTV(boolean TV) {
            this.TV = TV;
            return this;
        }

        public RoomBuilder hasCloset(boolean closet) {
            this.closet = closet;
            return this;
        }

        public RoomBuilder getPrice(double price) {
            this.price = price;
            return this;
        }

        public RoomBuilder hasDoubleBed(boolean doubleBed) {
            this.doubleBed = doubleBed;
            return this;
        }

        public RoomBuilder hasMiniBar(boolean miniBar) {
            this.miniBar = miniBar;
            return this;
        }

        public RoomBuilder hasButHub(boolean batHub) {
            this.batHub = batHub;
            return this;
        }

        public RoomBuilder hasKingSize(boolean kingSizeBed) {
            this.kingSizeBed = kingSizeBed;
            return this;
        }

        public RoomBuilder hasSitingArea(boolean sittingArea) {
            this.sittingArea = sittingArea;
            return this;
        }

        /**
         * Builds a new `Room` object with the configured attributes.
         *
         * @return A new `Room` object.
         */
        public Room build() {
            Room room = new Room();
            room.roomID = roomID;
            room.roomType = roomType;
            room.singleBed = singleBed;
            room.bathRoom = bathRoom;
            room.TV = TV;
            room.closet = closet;
            room.price = price;
            room.doubleBed = doubleBed;
            room.miniBar = miniBar;
            room.batHub = batHub;
            room.kingSizeBed = kingSizeBed;
            room.sittingArea = sittingArea;
            return room;
        }


    }


    public RoomType getRoomType() {
        return roomType;
    }

    public int getRoomID() {
        return roomID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void book() {
        isAvailable = false;
    }

}



