package file.engine.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public abstract class Plugin {
    private static final ConcurrentLinkedQueue<String> resultQueue = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<String[]> messageQueue = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<Object[]> eventQueue = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<Object[]> replaceEventHandlerQueue = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<String> restoreReplacedEventQueue = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<Object[]> addEventListenerQueue = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<String[]> removeEventListenerQueue = new ConcurrentLinkedQueue<>();
    private static final int API_VERSION = 8;

    //Interface
    public abstract void textChanged(String text);

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

    public abstract void searchBarVisible(String showingMode);

    public abstract void configsChanged(Map<String, Object> configs);

    public abstract void eventProcessed(Class<?> c, Object eventInstance);

    public abstract void openSettings();

    /*---------------------------------------------------------------------------------------------------------*/
    /*                                              以下为可使用方法                                              */
    /*---------------------------------------------------------------------------------------------------------*/

    /**
     * 恢复File-Engine的事件处理器，或者取消注册插件事件
     *
     * @param classFullName 事件类全限定名
     */
    public static void restoreFileEngineEventHandler(String classFullName) {
        restoreReplacedEventQueue.add(classFullName);
    }

    /**
     * 替换File-Engine对应事件的事件处理器，同时也可以注册插件事件
     *
     * @param classFullName 事件类全限定名
     * @param handler       事件处理器
     */
    public static void registerFileEngineEventHandler(String classFullName, BiConsumer<Class<?>, Object> handler) {
        Object[] objects = new Object[2];
        objects[0] = classFullName;
        objects[1] = handler;
        replaceEventHandlerQueue.add(objects);
    }

    /**
     * 添加事件监听器，类名可以为File-Engine中的事件，也可以是插件自定义事件
     *
     * @param classFullName 事件类全限定名
     * @param listenerName  监听器名
     * @param listener      监听器
     */
    public static void registerFileEngineEventListener(String classFullName, String listenerName, BiConsumer<Class<?>, Object> listener) {
        Object[] objects = new Object[3];
        objects[0] = classFullName;
        objects[1] = listenerName;
        objects[2] = listener;
        addEventListenerQueue.add(objects);
    }

    /**
     * 删除注册的事件监听器
     *
     * @param classFullName 事件类全限定名
     * @param listenerName  监听器名
     */
    public static void removeFileEngineEventListener(String classFullName, String listenerName) {
        removeEventListenerQueue.add(new String[]{classFullName, listenerName});
    }

    /**
     * 推送结果到File-Engine
     *
     * @param result 结果
     */
    public static void addToResultQueue(String result) {
        resultQueue.add(result);
    }

    /**
     * 在任务通知栏显示通知
     *
     * @param caption 标题
     * @param message 信息
     */
    public static void displayMessage(String caption, String message) {
        String[] messages = new String[]{caption, message};
        messageQueue.add(messages);
    }

    /**
     * 向File-Engine发送事件
     *
     * @param event 事件
     */
    public static void sendEventToFileEngine(Event event) {
        Class<? extends Event> eventClass = event.getClass();
        sendEventToFileEngine(eventClass.getName(), event.getBlock(), event.getCallback(), event.getErrorHandler(), event, eventClass);
    }

    /**
     * 向File-Engine发送事件
     *
     * @param eventFullClassPath 事件类全限定名
     * @param params             事件类实例化所需参数
     */
    public static void sendEventToFileEngine(String eventFullClassPath, Object... params) {
        Object[] event = new Object[2];
        event[0] = eventFullClassPath;
        event[1] = params;
        eventQueue.add(event);
    }

    /**
     * 检查事件是否符合check规则，在使用File-Engine的内置事件时应该先使用该方法检查参数
     * 由于某些版本的事件可能会有细微差别，所以应该先进行检查事件类中的Field，确保兼容性
     * <p>
     * 推荐在loadPlugin时就对插件所有要使用的事件进行检查
     *
     * @param fileEngineEventName file-engine内置类的全限定名
     * @param fieldNameTypeMap    检查Field的类型
     *                            map中key为Field名称，value为Field类型
     * @see PluginMain#loadPlugin(Map)
     */
    public static void checkEvent(String fileEngineEventName, Map<String, Class<?>> fieldNameTypeMap) {
        Class<?> aClass;
        try {
            aClass = Class.forName(fileEngineEventName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Field[] declaredFields = aClass.getDeclaredFields();
        HashMap<String, Class<?>> fields = new HashMap<>();
        for (Field declaredField : declaredFields) {
            fields.put(declaredField.getName(), declaredField.getType());
        }
        for (Map.Entry<String, Class<?>> entry : fieldNameTypeMap.entrySet()) {
            String k = entry.getKey();
            Class<?> v = entry.getValue();
            if (!fields.containsKey(k)) {
                throw new RuntimeException("check event failed. Missing Field: " + k);
            }
            Class<?> realType = fields.get(k);
            if (!v.isAssignableFrom(realType)) {
                throw new RuntimeException("check event type failed. Field name: " + k + "  assert type: " + v + "  real type: " + realType);
            }
        }
    }


    /*--------------------------------------------------------------------------------------------------------------*/
    /*                                            以下为接口方法,请勿删除                                               */
    /*--------------------------------------------------------------------------------------------------------------*/
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

    protected String[] _pollFromRemoveListenerQueue() {
        return removeEventListenerQueue.poll();
    }

    protected Object[] _pollFromEventListenerQueue() {
        return addEventListenerQueue.poll();
    }

    /**
     * Do Not Remove, this is used for File-Engine to get message from the plugin.
     * You can show message using "displayMessage(String caption, String message)"
     *
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
     *
     * @return result
     * @see #addToResultQueue(String)
     */
    @SuppressWarnings("unused")
    public String pollFromResultQueue() {
        return _pollFromResultQueue();
    }

    /**
     * Do Not Remove, this is used for File-Engine to check the API version.
     *
     * @return Api version
     */
    @SuppressWarnings("unused")
    public int getApiVersion() {
        return _getApiVersion();
    }

    /**
     * Do Not Remove, this is used for File-Engine to clear results to prepare for the next time.
     *
     * @see #addToResultQueue(String)
     * @see #pollFromResultQueue()
     */
    @SuppressWarnings("unused")
    public void clearResultQueue() {
        _clearResultQueue();
    }

    /**
     * Do Not Remove, this is used for File-Engine to poll the event that send from the plugin.
     * The object array contains two parts.
     * object[0] contains the fully-qualified name of class.
     * object[1] contains the params that the event need to build an instance.
     * To send an event to File-Engine
     *
     * @return Event
     * @see #sendEventToFileEngine(String, Object...)
     * @see #sendEventToFileEngine(Event)
     */
    @SuppressWarnings("unused")
    public Object[] pollFromEventQueue() {
        return _pollFromEventQueue();
    }

    /**
     * Do Not Remove, this is used for File-Engine to replace the handler which the plugin is registered.
     * The object array contains two parts.
     * object[0] contains the fully-qualified name of class.
     * object[1] contains a consumer to hande the event.
     *
     * @return Event handler
     * @see #registerFileEngineEventHandler(String, BiConsumer)
     */
    @SuppressWarnings("unused")
    public Object[] pollFromEventHandlerQueue() {
        return _pollEventHandlerQueue();
    }

    /**
     * Do Not Remove, this is used for File-Engine to restore the handler which the plugin is registered.
     *
     * @return Event class fully-qualified name
     * @see #restoreFileEngineEventHandler(String)
     */
    @SuppressWarnings("unused")
    public String restoreFileEngineEventHandler() {
        return _pollFromRestoreQueue();
    }

    /**
     * Do Not Remove, this is used for File-Engine to add an event listener for this plugin.
     * The object array contains two parts.
     * object[0] contains the fully-qualified name of class.
     * object[1] contains a consumer to execute when the event is finished.
     *
     * @return Event listener
     */
    @SuppressWarnings("unused")
    public Object[] pollFromEventListenerQueue() {
        return _pollFromEventListenerQueue();
    }

    /**
     * Do Not Remove, this is used to remove a plugin registered event listener.
     *
     * @return Event class fully-qualified name
     */
    @SuppressWarnings("unused")
    public String[] removeFileEngineEventListener() {
        return _pollFromRemoveListenerQueue();
    }
}
