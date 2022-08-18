# Getting Started

首先，下载插件模板到你的工作文件夹，你只需要实现PluginMain类中对应的接口即可。



下面的代码展示了如何在搜索框的字符串改变时将字符串打印出来。你可以查看Javadoc找到关于更多方法和接口的细节。

```java
public class PluginMain extends Plugin {
    @Override
    public void textChanged(String text) {
        System.out.println(text);
    }

    @Override
    public void loadPlugin(Map<String, Object> configs) {
        System.out.println("plugin loaded");
    }

    @Override
    public void unloadPlugin() {
        System.out.println("plugin unloaded");
    }
}
```

你需要在src/main/resources/PluginInfo.json中定义插件的接口类，插件的名称，插件的标识符。

## 插件的文件名称必须与PluginInfo.json中的name相同

## 插件的标识符用于在搜索框中输入不同的标识符来切换不同的插件。

```json
{
  "className" : "file.engine.example.PluginMain",
  "identifier" : "example",
  "name" : "ExamplePlugin"
}
```


