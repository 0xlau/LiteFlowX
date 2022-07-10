# LiteFlowX Changelog

## [Unreleased]

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
