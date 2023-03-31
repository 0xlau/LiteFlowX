# LiteFlowX Changelog

## [Unreleased]

## [1.2.0] - 2023-04-01
- 新增 支持AND()、OR()、NOT()逻辑表达式
- 兼容 IDEA-2023.* 版本

## [1.1.6] - 2023-03-16
- 新增 CATCH().DO() 表达式

## [1.1.5] - 2023-02-01
- 修复 ITERATOR 关键字组件图标无法正常显示问题
- 修复 .el.xml 文件中 chain 标签无法识别 id 属性问题

## [1.1.4] - 2023-01-18
- 新增 #I6AY1G 对 ITERATOR 关键字的支持
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I6AY1G

## [1.1.3] - 2022-12-11
- 新增 #I65E2V SWITCH表达式的DEFAULT用法
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I65E2V
- 新增 #I65E31 脚本组件对Python语言以及Lua语言的支持
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I65E31
- 兼容 IDEA-2022.3 版本

## [1.1.2] - 2022-11-23
- 修复 #I5X8XV com/intellij/psi/impl/source/PsiClassImpl.isInheritor must not be null 的问题
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5X8XV
- 修复 com.intellij.psi.impl.source.xml.XmlElementContentGroupImpl cannot be cast to class com.intellij.psi.xml.XmlTag 的问题

## [1.1.1] - 2022-10-24
- 新增 #I5VMP4 在xml中新增对groovy以及javascript语法的支持
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5VMP4
- 修复 #I5WQME 无法识别多行注释问题
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5WQME

## [1.1.0] - 2022-10-09
- 新增 #I5TUH1 LiteFlow 2.9.0 的 data 属性特性的适配
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5TUH1
- 新增 #I5TU26 LiteFlow 2.9.0 循环组件语法的适配
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5TU26
- 新增 #I5U36H LiteFlow 2.9.0 版本中EL对*.xml检测生效
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5U36H
- 新增 #I5UA9R LiteFlow 2.9.0 重构后的声明式组件的适配
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5UA9R
- 新增 #I5TU3E 细分化Component类型的特性
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5TU3E
- 修复 #I5Q35D ELF语法无法正常解析注释的问题
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5Q35D
- 修复 #I5U3QH 核心源码不应该显示组件图标的问题
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5U3QH
- 修复 #I5O84M 不能正常识别node的XML标签的问题
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5O84M
- 优化 插件内核源码架构
- 优化 插件内的所有图标，简洁清爽！
- 优化 LiteFlowTool 工具箱的副标题显示内容
- 修复 LiteFlowTool 工具箱双击无法跳转对应Chain的问题

## [1.0.4] - 2022-08-25
- 新增 #I5NWWJ 支持 LiteFlow v2.8.5 的IF关键字特性
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5NWWJ
- 修复 SWITCH() 允许输入多个表达式的问题


## [1.0.3] - 2022-08-02
- 新增 #I5K13V 支持 liteflow v2.8.3 的替补组件特性
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5K13V
- 新增 #I5JQXV 支持 liteflow v2.8.3 的组件名包装
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5JQXV

## [1.0.2] - 2022-07-21
- 新增 #I5HPMC 支持EL中的注释
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5HPMC
- 新增 #I5H8RL 新增 IDEA-222.* 的支持
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5H8RL
- 新增 识别括号并高亮
- 新增 自动缩进
- 修复 抛出 java.lang.NullPointerException 异常的问题

## [1.0.1] - 2022-07-14
- 修复 因旧表达式存在而导致的报错
- 修复 因qualifiedName参数为null而导致的IllegalArgumentException
- 优化 部分代码的对Null值得判断

## [1.0.0] - 2022-07-10
- 适配 LiteFlow 2.8.x 的特性
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5GD4S

## [0.1.1] - 2022-06-15
- 修复 #I5BW1N 关于java代码编辑器中的chain错误跳转问题
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5BW1N
- 修复 #I5BPP6 修复组件多层继承而无法正确跳转问题
  - https://gitee.com/liupeiqiang/LiteFlowX/issues/I5BPP6 

## [0.1.0] - 2022-06-09
### 新增
- 特性 #I5B89P Xml文件中支持Ctrl+鼠标左键点击表达式直接跳转到定义
### 增强
- 适配 liteFlow 2.7.x 版本
- LiteFlowTool内扫描的不足1的元素分类不显示
### 修复
- 修复 #I5A2TT 解决LiteFlowTool刷新元素时卡顿问题
- 修复 #I5B85N 不能够正常识别组件问题

## [0.0.2] - 2022-05-30
### 新增
- 新增对yml、properties、beanXml的rule-source文件跳转的支持
### 修复
- 修复Component或LitefloeComponent注解value为表达式不能识别跳转问题

## [0.0.1] - 2022-05-25
### 新增
- 支持 Java 代码跳转到 Chain
- 支持 LiteFlowTool 工具中显示元素位置
- 支持 IDEA 2017.1 以后的版本
### 更改
- 图标全部更换使用 svg 图标文件
### 修复
- 解决了插件不稳定的部分情况

## [0.0.1-alpha.3.1] - 2022-05-23
### Added
- Added LiteFlowTool Windows
- Added file icon processing, able to identify xml files and component files
- Added recognition of Slot files
### Changed
- Modify the style of XML intellisense
- Re-optimize the code structure and add comments to important classes
- Temporarily remove support for JSON files
- Using the LiteFlow native Parser for parsing expression
- LineMarker icon modification
### Bug fixed
- Some components could not be found
- Fix the problem of not being able to autocomplete

## [0.0.1-alpha.3] - 2022-05-23
### Added
- Added LiteFlowTool Windows
- Added file icon processing, able to identify xml files and component files
- Added recognition of Slot files
### Changed
- Modify the style of XML intellisense
- Re-optimize the code structure and add comments to important classes
- Temporarily remove support for JSON files
- Using the LiteFlow native Parser for parsing expression
- LineMarker icon modification
### Bug fixed
- Some components could not be found

## [0.0.1-alpha.2.1] - 2022-05-16
### Changed
- Change plugin icon

## [0.0.1-alpha.2] - 2022-05-16
### Enhanced
- Compatible with IDEA Community Edition

## [0.0.1-alpha] - 2022-05-12
### Added
- Identify components, nodes and chains.
- Component and XmlTag can jump back and forth.
- Component and JsonObject can jump back and forth.
- Smart Prompts and AutoComplete
