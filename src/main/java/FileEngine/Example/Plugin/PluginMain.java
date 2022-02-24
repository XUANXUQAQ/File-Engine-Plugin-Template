package FileEngine.Example.Plugin;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Map;

public class PluginMain extends Plugin {

    /**
     * Do Not Remove, this is used for File-Engine to get message from the plugin.
     * You can show message using "displayMessage(String caption, String message)"
     * @return String[2], the first string is caption, the second string is message.
     * @see #displayMessage(String, String)
     */
    @SuppressWarnings("unused")
    public String[] getMessage() {
        return _getMessage();
    }

    /**
     * Do Not Remove, this is used for File-Engine to get results from the plugin
     * You can add result using "addToResultQueue(String result)".
     * @see #addToResultQueue(String)
     * @return result
     */
    @SuppressWarnings("unused")
    public String pollFromResultQueue() {
        return _pollFromResultQueue();
    }

    /**
     * Do Not Remove, this is used for File-Engine to check the API version.
     * @return Api version
     */
    @SuppressWarnings("unused")
    public int getApiVersion() {
        return _getApiVersion();
    }

    /**
     * Do Not Remove, this is used for File-Engine to clear results to prepare for the next time.
     * @see #addToResultQueue(String)
     * @see #pollFromResultQueue()
     */
    @SuppressWarnings("unused")
    public void clearResultQueue() {
        _clearResultQueue();
    }

    /**
     * Do Not Remove
     * @return Event
     */
    @SuppressWarnings("unused")
    public Object[] pollFromEventQueue() {
        return _pollFromEventQueue();
    }

    /**
     * Deprecated
     * You should load the theme by loadPlugin and update the theme by configsChanged
     *
     * @see #loadPlugin(Map)
     * @see #configsChanged(Map)
     * This is used for File-Engine to tell the plugin the current Theme settings.
     * This function will be called when the plugin is being loaded.
     * You can use them on method showResultOnLabel(String, JLabel, boolean).
     * When the label is chosen by user, you could set the label background as chosenLabelColor.
     * When the label isn't chosen by user, you could set the label background as defaultColor.
     * You can save the color and use it at function showResultOnLabel(String, JLabel, boolean)
     * @see #showResultOnLabel(String, JLabel, boolean)
     * @param defaultColor This is the color's RGB code. When the label isn't chosen, it will be shown as this color.
     * @param choseLabelColor This is the color's RGB code. When the label is chosen, it will be shown as this color.
     * @param borderColor This is the border color of File-Engine, it is deprecated, you should not set labels' border in plugin.
     *                    However, you can still know the border color through this parameter.
     */
    @Override
    @Deprecated
    public void setCurrentTheme(int defaultColor, int choseLabelColor, int borderColor) {

    }

    /**
     * When the configuration file of File-Engine is updated, this function will be called.
     * @param configs configs
     */
    @Override
    public void configsChanged(Map<String, Object> configs) {

    }

    /**
     * When the search bar textChanged, this function will be called.
     * @param text
     * Example : When you input "&gt;examplePlugin TEST" to the search bar, the param will be "TEST"
     */
    @Override
    public void textChanged(String text) {

    }

    /**
     * When File-Engine is starting, the function will be called.
     * You can initialize your plugin here
     */
    @Override
    public void loadPlugin(Map<String, Object> configs) {

    }

    /**
     * Deprecated
     * You should use loadPlugin with configs
     *
     * @see #loadPlugin(Map)
     * When File-Engine is starting, the function will be called.
     * You can initialize your plugin here
     */
    @Override
    @Deprecated
    public void loadPlugin() {

    }

    /**
     * When File-Engine is closing, the function will be called.
     */
    @Override
    public void unloadPlugin() {

    }

    /**
     * Invoked when a key has been released.See the class description for the swing KeyEvent for a definition of a key released event.
     * Notice : Up and down keys will not be included (key code 38 and 40 will not be included).
     * @param e KeyEvent, Which key on the keyboard is released.
     * @param result Currently selected content.
     */
    @Override
    public void keyReleased(KeyEvent e, String result) {

    }

    /**
     * Invoked when a key has been pressed. See the class description for the swing KeyEvent for a definition of a key pressed event.
     * Notice : Up and down keys will not be included (key code 38 and 40 will not be included).
     * @param e KeyEvent, Which key on the keyboard is pressed.
     * @param result Currently selected content.
     */
    @Override
    public void keyPressed(KeyEvent e, String result) {

    }

    /**
     * Invoked when a key has been typed.See the class description for the swing KeyEvent for a definition of a key typed event.
     * Notice : Up and down keys will not be included (key code 38 and 40 will not be included).
     * @param e KeyEvent, Which key on the keyboard is pressed.
     * @param result Currently selected content.
     */
    @Override
    public void keyTyped(KeyEvent e, String result) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     * @param e Mouse event
     * @param result Currently selected content.
     */
    @Override
    public void mousePressed(MouseEvent e, String result) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     * @param e Mouse event
     * @param result Currently selected content
     */
    @Override
    public void mouseReleased(MouseEvent e, String result) {

    }

    @Override
    public void searchBarVisible(String showingMode) {

    }

    /**
     * Get the plugin Icon. It can be the png, jpg.
     * Make the icon small, or it will occupy too much memory.
     * @return icon
     */
    @Override
    public ImageIcon getPluginIcon() {
        return null;
    }

    /**
     * Get the official site of the plugin.
     * @return official site
     */
    @Override
    public String getOfficialSite() {
        return null;
    }

    /**
     * Get the plugin version.
     * @return version
     */
    @Override
    public String getVersion() {
        return null;
    }

    /**
     * Get the description of the plugin.
     * Just write the description outside, and paste it to the return value.
     * @return description
     */
    @Override
    public String getDescription() {
        return null;
    }

    /**
     * Check if the current version is the latest.
     * @return true or false
     * @see #getUpdateURL()
     */
    @Override
    @SuppressWarnings({"unused", "RedundantThrows"})
    public boolean isLatest() throws Exception {
        return false;
    }

    /**
     * Get the plugin download url.
     * Invoke when the isLatest() returns false;
     * @see #isLatest()
     * @return download url
     */
    @Override
    public String getUpdateURL() {
        return null;
    }

    /**
     * Show the content to the GUI.
     * @param result current selected content.
     * @param label The label to be displayed.
     * @param isChosen If the label is being selected.
     *                 If so, you are supposed to set the label at a different background.
     *
     * You can only set the icon, text and background of this label, please do not set other attributes, such as borders...
     */
    @Override
    public void showResultOnLabel(String result, JLabel label, boolean isChosen) {

    }

    @Override
    public String getAuthor() {
        return null;
    }


    /**
     * Broadcast the current processing event.
     * You can send an event to File-Engine by sendEventToFileEngine(String eventFullClassPath, Object... params)
     * @see #sendEventToFileEngine(String, Object...)
     * @param c event class
     * @param eventInstance event instance
     */
    @Override
    public void eventProcessed(Class<?> c, Object eventInstance) {

    }
}
