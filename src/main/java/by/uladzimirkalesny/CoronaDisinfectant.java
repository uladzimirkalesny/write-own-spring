package by.uladzimirkalesny;

public class CoronaDisinfectant {

    @InjectByType
    private Announcer announcer;

    @InjectByType
    private Policemen policeman;

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
