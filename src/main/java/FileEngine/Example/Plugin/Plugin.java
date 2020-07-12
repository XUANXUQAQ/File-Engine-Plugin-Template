/**
 * The Interface of File-Engine Plugin.
 */
package FileEngine.Example.Plugin;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Plugin {
    private final ConcurrentLinkedQueue<String> resultQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<String[]> messageQueue = new ConcurrentLinkedQueue<>();
    private static final int API_VERSION = 1;

    public void addToResultQueue(String result) {
        resultQueue.add(result);
    }

    public void displayMessage(String caption, String message) {
        String[] messages = new String[]{caption, message};
        messageQueue.add(messages);
    }

    protected int _getApiVersion() {
        return API_VERSION;
    }
    protected String _pollFromResultQueue() {
        return resultQueue.poll();
    }
    protected String[] _getMessage() {
        return messageQueue.poll();
    }


    //Interface
    public abstract void textChanged(String text);

    public abstract void loadPlugin();

    public abstract void unloadPlugin();

    public abstract void keyReleased(KeyEvent e, String result);

    public abstract void keyPressed(KeyEvent e, String result);

    public abstract void keyTyped(KeyEvent e, String result);

    public abstract void mousePressed(MouseEvent e, String result);

    public abstract void mouseReleased(MouseEvent e, String result);

    public abstract ImageIcon getPluginIcon();

    public abstract String getOfficialSite();

    public abstract String getVersion();

    public abstract String getDescription();

    public abstract boolean isLatest();

    public abstract String getUpdateURL();

    public abstract void showResultOnLabel(String result, JLabel label, boolean isChosen);
}
