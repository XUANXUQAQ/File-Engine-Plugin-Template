package FileEngine.Example.Plugin;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public abstract class Plugin {
    private final ConcurrentLinkedQueue<String> resultQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<String[]> messageQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Object[]> eventQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Object[]> replaceEventHandlerQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<String> restoreReplacedEventQueue = new ConcurrentLinkedQueue<>();
    private static final int API_VERSION = 6;

    protected void _clearResultQueue() {
        resultQueue.clear();
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

    protected Object[] _pollFromEventQueue() {
        return eventQueue.poll();
    }

    protected Object[] _pollEventHandlerQueue() {
        return replaceEventHandlerQueue.poll();
    }

    protected String _pollFromRestoreQueue() {
        return restoreReplacedEventQueue.poll();
    }

    //Interface
    public abstract void textChanged(String text);

    public abstract void loadPlugin();

    public abstract void loadPlugin(Map<String, Object> configs);

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

    public abstract boolean isLatest() throws Exception;

    public abstract String getUpdateURL();

    public abstract void showResultOnLabel(String result, JLabel label, boolean isChosen);

    public abstract String getAuthor();

    public abstract void setCurrentTheme(int defaultColor, int choseLabelColor, int borderColor);

    public abstract void searchBarVisible(String showingMode);

    public abstract void configsChanged(Map<String, Object> configs);

    public abstract void eventProcessed(Class<?> c, Object eventInstance);

    /*---------------------------------------------------------------------------------------------------------*/
    /*                                              以下为可使用方法                                              */
    /*---------------------------------------------------------------------------------------------------------*/

    /**
     * 恢复File-Engine的事件处理器
     * @param classFullName 事件类全限定名
     */
    public void restoreFileEngineEventHandler(String classFullName) {
        restoreReplacedEventQueue.add(classFullName);
    }

    /**
     * 替换File-Engine对应事件的事件处理器
     * @param classFullName 事件类全限定名
     * @param handler 事件处理器
     */
    public void registerFileEngineEventHandler(String classFullName, BiConsumer<Class<?>, Object> handler) {
        Object[] objects = new Object[2];
        objects[0] = classFullName;
        objects[1] = handler;
        replaceEventHandlerQueue.add(objects);
    }

    /**
     * 推送结果到File-Engine
     * @param result 结果
     */
    public void addToResultQueue(String result) {
        resultQueue.add(result);
    }

    /**
     * 在任务通知栏显示通知
     * @param caption 标题
     * @param message 信息
     */
    public void displayMessage(String caption, String message) {
        String[] messages = new String[]{caption, message};
        messageQueue.add(messages);
    }

    /**
     * 向File-Engine发送事件
     * @param event 事件
     */
    public void sendEventToFileEngine(Event event) {
        Class<? extends Event> eventClass = event.getClass();
        Field[] declaredFields = eventClass.getDeclaredFields();
        LinkedHashMap<String, Object> paramsMap = new LinkedHashMap<>();
        try {
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                paramsMap.put(declaredField.getType().getName() + ":" + declaredField.getName(), declaredField.get(event));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sendEventToFileEngine(Event.class.getName(), event.getBlock(), event.getCallback(), event.getErrorHandler(), paramsMap);
    }

    /**
     * 向File-Engine发送事件
     * @param eventFullClassPath 事件类全限定名
     * @param params 事件类实例化所需参数
     */
    public void sendEventToFileEngine(String eventFullClassPath, Object... params) {
        Object[] event = new Object[2];
        event[0] = eventFullClassPath;
        event[1] = params;
        eventQueue.add(event);
    }
}
