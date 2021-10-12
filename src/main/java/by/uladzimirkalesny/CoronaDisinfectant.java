package by.uladzimirkalesny;

public class CoronaDisinfectant {

    private Announcer announcer = ObjectFactory.getObjectFactoryInstance().createObject(Announcer.class);
    private Policemen policeman = ObjectFactory.getObjectFactoryInstance().createObject(Policemen.class);

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
