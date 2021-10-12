package by.uladzimirkalesny;

public class CoronaDisinfectant {

    private Announcer announcer = new ConsoleAnnouncer();
    private Policemen policeman = new PolicemenImpl();

    public void startDisinfectionRoom(Room room) {
        announcer.announceMessage("Start Disinfection!");
        policeman.makePeopleLeaveRoom();
        disinfectRoom(room);
        announcer.announceMessage("End Disinfection");
    }

    private void disinfectRoom(Room room) {
        System.out.println("Processing room disinfection... Done!");
    }

}
