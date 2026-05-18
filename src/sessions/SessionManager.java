package sessions;

import images.Image;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private Map<Integer, Session> sessions;

    private Session currentSession;

    private int nextSessionId;

    public SessionManager() {

        sessions = new HashMap<>();

        nextSessionId = 1;
    }

    /**
     * Създава сесия при зареждане на изображение
     * @param image
     */
    public void createSession(Image image) {

        Session session =
                new Session(nextSessionId);

        session.addImage(image);

        sessions.put(nextSessionId, session);

        currentSession = session;

        System.out.println(
                "Session with ID "
                        + nextSessionId
                        + " started."
        );

        nextSessionId++;
    }

    /**
     * Сменя сесия
     * @param id
     */
    public void switchSession(int id) {

        if (!sessions.containsKey(id)) {

            System.out.println(
                    "Session with ID "
                            + id
                            + " does not exist."
            );

            return;
        }

        currentSession = sessions.get(id);

        System.out.println(
                "Switched to session "
                        + id
        );
    }

    public Session getCurrentSession() {

        return currentSession;
    }

}