package file.engine.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.function.BiConsumer;

public class PluginMain extends Plugin {

    /**
     * 当用户修改FIle-Engine的设置后，将调用此函数。
     *
     * @param configs configs
     */
    @Override
    public void configsChanged(Map<String, Object> configs) {

    }

    /**
     * 当搜索栏文本更改时，将调用此函数。
     *
     * @param text Example : 当在搜索框输入 "&gt;examplePlugin TEST" 时, 参数text值为 "TEST"
     */
    @Override
    public void textChanged(String text) {

    }

    /**
     * 启动File-Engine时，将调用该函数。
     * 您可以在此处初始化插件
     */
    @Override
    public void loadPlugin(Map<String, Object> configs) throws RuntimeException {

    }

    /**
     * 当File-Engine关闭时，将调用该函数。
     */
    @Override
    public void unloadPlugin() {

    }

    /**
     * 当File-Engine进入插件模式后，每释放一次键盘该方法将会执行一次。有关按键释放事件的定义，请参见 swing KeyEvent 的类描述。
     * 请注意：由于上下键用于切换显示结果，所以按下键盘上下键不会触发该方法(key code 38和40不会触发)。
     *
     * @param e      KeyEvent, Which key on the keyboard is released.
     * @param result Currently selected content.
     */
    @Override
    public void keyReleased(KeyEvent e, String result) {

    }

    /**
     * 当File-Engine进入插件模式后，每按下一次键盘该方法将会执行一次。有关按键按下事件的定义，请参见 swing KeyEvent 的类描述。
     * 请注意：由于上下键用于切换显示结果，所以按下键盘上下键不会触发该方法(key code 38和40不会触发)。
     *
     * @param e      KeyEvent, Which key on the keyboard is pressed.
     * @param result Currently selected content.
     */
    @Override
    public void keyPressed(KeyEvent e, String result) {

    }

    /**
     * 当File-Engine进入插件模式后，每按下和释放一次键盘该方法将会执行一次。有关按键事件的定义，请参见 swing KeyEvent 的类描述。
     * 请注意：由于上下键用于切换显示结果，所以按下键盘上下键不会触发该方法(key code 38和40不会触发)。
     *
     * @param e      KeyEvent, Which key on the keyboard is pressed.
     * @param result Currently selected content.
     */
    @Override
    public void keyTyped(KeyEvent e, String result) {

    }

    /**
     * 当File-Engine进入插件模式后，鼠标每点击窗口以下该方法将会执行一次。有关鼠标事件的定义，请参见swing MouseEvent的类描述。
     *
     * @param e      Mouse event
     * @param result Currently selected content.
     */
    @Override
    public void mousePressed(MouseEvent e, String result) {

    }

    /**
     * 当File-Engine进入插件模式后，鼠标每释放一次该方法将会执行一次。有关鼠标事件的定义，请参见swing MouseEvent的类描述。
     *
     * @param e      Mouse event
     * @param result Currently selected content
     */
    @Override
    public void mouseReleased(MouseEvent e, String result) {

    }

    /**
     * 当File-Engine的搜索框被打开该方法将会被调用一次。并不需要进入插件模式。
     *
     * @param showingMode 显示模式
     *                    <p>
     *                    目前File-Engine有两种模式：普通显示和贴靠资源管理器显示，对应的showingMode为 NORMAL_SHOWING， EXPLORER_ATTACH
     */
    @Override
    public void searchBarVisible(String showingMode) {

    }

    /**
     * 获取插件图标。它可以是png，jpg。
     * 尽量让图标变小，否则会占用太多内存。
     *
     * @return icon
     */
    @Override
    public ImageIcon getPluginIcon() {
        return null;
    }

    /**
     * 获取插件的官方网站。
     *
     * @return official site
     */
    @Override
    public String getOfficialSite() {
        return null;
    }

    /**
     * 获取插件版本信息
     *
     * @return version
     */
    @Override
    public String getVersion() {
        return null;
    }

    /**
     * 获取插件描述，插件的介绍信息以及使用方法。插件的介绍信息将会显示在设置界面。
     * 只需在外部写入描述，并将其粘贴然后return。
     *
     * @return description
     */
    @Override
    public String getDescription() {
        return null;
    }

    /**
     * 检查当前版本是否为最新版本。
     *
     * @return true or false
     * @see #getUpdateURL()
     */
    @Override
    @SuppressWarnings({"unused", "RedundantThrows"})
    public boolean isLatest() throws Exception {
        return false;
    }

    /**
     * 返回下载插件jar文件的url
     * 当**isLatest**返回false，将会弹出窗口询问用户是否更新，如果用户点击确定，则会调用该方法来下载新版本的插件。
     *
     * @return download url
     * @see #isLatest()
     */
    @Override
    public String getUpdateURL() {
        return null;
    }

    /**
     * 当插件返回了结果到File-Engine，File-Engine尝试显示时，将会调用该方法。
     *
     * @param result   current selected content.
     * @param label    需要显示的JLabel.
     * @param isChosen 如果当前label是目前被用户选中的，您应该将标签设置为不同的背景，背景颜色可以通过loadPlugins和configsChanged方法的参数获得。
     *                 <p>
     *                 您只能设置JLabel的图标、文本和背景，请不要设置其他属性，如border，name以及其他
     */
    @Override
    public void showResultOnLabel(String result, JLabel label, boolean isChosen) {

    }

    /**
     * 获取插件的作者名，将会显示在插件的设置界面。
     *
     * @return author name
     */
    @Override
    public String getAuthor() {
        return null;
    }


    /**
     * File-Engine中拥有一个事件处理系统，每一个事件被处理，该方法将会被调用一次。你可以在此处监听主程序当前处理了什么事件。也可以通过该接口实现多个插件互相通信。
     * 你可以通过 sendEventToFileEngine() 方法发送事件
     *
     * @param c             事件类
     * @param eventInstance 事件实例
     * @see #sendEventToFileEngine(String, Object...)
     * @see #sendEventToFileEngine(Event)
     * <p>
     * --------------------------
     * ------- deprecated -------
     * --------------------------
     * 现在可以通过registerFileEngineEventListener()函数直接对事件进行监听，不再需要该接口。但该接口仍然可以使用。
     * @see #registerFileEngineEventListener(String, String, BiConsumer)
     * @see #removeFileEngineEventListener(String, String)
     */
    @Override
    @Deprecated
    public void eventProcessed(Class<?> c, Object eventInstance) {

    }

    /**
     * 当在设置中插件页面点击设置按钮时将会触发该方法
     */
    @Override
    public void openSettings() {

    }
}
