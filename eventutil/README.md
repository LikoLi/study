# 事件生成/修改工具

## 新建事件
1. 找到resources下的样例文件(Event.xls)
2. 删除EventstructdefExt这个Sheet中已定义好的数据, 保留title
3. 根据样例数据填充EventstructdefExt
4. 删除EventfielddefExt这个Sheet中已定义好的数据, 保留title
5. 根据样例数据填充EventfielddefExt
6. 运行EventGenerator.generate();

## 修改事件
1. 通过页面获取要修改事件的ID
2. 运行EventGenerator.getEvent(eventID); 生成xls, log中会打印文件的生成路径
3. 找到生成的文件并修改
4. 将文件重命名为Event.xls并移动到resources目录下
5. 运行EventGenerator.generate();