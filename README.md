# SpaceStation3D - 3D 空间站可视化项目

一个基于 Java 和 LWJGL 开发的 3D 空间站场景渲染项目，展示了复杂的太空环境和各种空间对象。

> **⚠️ 学术诚信声明**: 为遵守学术政策，本仓库的源代码已被移除。如需查看完整实现，请联系作者。
> 
## Demo

### 🎥 演示视频
![演示GIF](Space.gif)

*完整的交互演示*
[视频](https://t.bilibili.com/1090972936470265858?share_source=pc_native)


## 📋 项目概览

本项目是一个计算机图形学课程项目，实现了一个完整的 3D 太空场景，包含多个空间站、宇航员、飞船和太阳系行星等对象。用户可以通过鼠标和键盘交互来浏览这个虚拟的太空世界。

## ✨ 主要功能

### 🌌 3D 对象渲染
- **空间站 (SpaceStation)**: 主要的空间结构，带有太阳能板和复杂的几何体
- **宇航员 (Astronaut)**: 动态的人形角色，支持动画
- **飞船 (Spaceship)**: 可移动的太空载具
- **太空电梯 (SpaceElevator)**: 大型结构建筑
- **采矿站 (SpaceMiningStation)**: 工业设施
- **太空探测器 (SpaceProbe)**: 小型探测设备
- **行星系统**: 包含太阳系各大行星的纹理渲染



### 🎨 视觉效果
- **纹理映射**: 丰富的材质纹理，包括金属、岩石、行星表面等
- **天空盒**: 360度星空背景环境
- **光照系统**: 真实的光照和阴影效果
- **动画系统**: 平滑的对象动画和相机运动

## 🛠️ 技术栈

- **编程语言**: Java
- **图形库**: LWJGL (Lightweight Java Game Library)
- **图形 API**: OpenGL
- **构建工具**: IntelliJ IDEA 项目

## 📁 项目结构

```
Project1/
├── src/                          # 源代码目录
│   ├── MainWindow.java          # 主程序入口和渲染循环
│   ├── GraphicsObjects/         # 图形基础对象
│   │   ├── Arcball.java        # 弧球控制器
│   │   ├── Point4f.java        # 4D 点类
│   │   ├── Vector4f.java       # 4D 向量类
│   │   ├── quat.java           # 四元数类
│   │   └── Utils.java          # 工具类
│   └── objects3D/              # 3D 对象类
│       ├── SpaceStation.java   # 空间站
│       ├── Astronaut.java      # 宇航员
│       ├── Spaceship.java      # 飞船
│       ├── SpaceElevator.java  # 太空电梯
│       ├── SpaceMiningStation.java # 采矿站
│       ├── SpaceProbe.java     # 太空探测器
│       ├── Human.java          # 人形角色
│       ├── Sphere.java         # 球体
│       ├── Cube.java           # 立方体
│       ├── Cylinder.java       # 圆柱体
│       ├── TexSphere.java      # 纹理球体
│       ├── TexCube.java        # 纹理立方体
│       ├── TexCylinder.java    # 纹理圆柱体
│       ├── Grid.java           # 网格
│       └── Meteor.java         # 陨石
├── res/                        # 资源文件目录
│   ├── *.jpg                   # 行星纹理 (太阳、水星、金星等)
│   ├── *.png                   # 各种材质纹理
│   └── PereaBeach1/           # 天空盒纹理
├── LWJGL/                      # LWJGL 库文件
├── bin/                        # 编译后的类文件
└── 本地库文件 (*.dll, *.dylib) # 平台相关的本地库
```

## 🚀 运行环境

### 系统要求
- **操作系统**: Windows, macOS, Linux
- **Java 版本**: Java 8 或更高版本
- **图形卡**: 支持 OpenGL 的显卡



## 📦 安装和运行

### 使用 IDE 运行 (推荐)
1. 在 IntelliJ IDEA 中打开项目
2. 配置项目 SDK 为 Java 8+
3. 将 LWJGL 目录添加到项目库
4. 在 VM options 中添加: `-Djava.library.path=.`
5. 运行 `MainWindow.main()`

## 🎮 操作指南

### 基本控制
- **鼠标左键拖拽**: 旋转视角
- **WASD 键**: 移动相机位置
- **O 键**: 切换自动相机巡航模式
- **G 键**: 显示/隐藏坐标网格
- **ESC 键**: 退出程序

### 观察模式
- **自由视角**: 默认模式，可自由控制相机
- **自动巡航**: 相机自动围绕场景旋转，提供电影般的观看体验



## 📄 许可证

### 学术诚信声明
```
本项目是计算机图形学课程的原创作业。

⚠️ 重要提醒:
- 本项目仅用于展示和学术交流目的
- 严禁直接复制用于类似课程作业
- 请遵守所在学校的学术诚信政策
- 如有疑问，请咨询相关课程教师

This project was developed as original coursework for a Computer Graphics class.
Please do not copy directly if you are taking a similar course.
```

## 📞 联系方式

如有问题或建议或者需要帮助，请发邮件[Le Liu](le.liu1@ucdconnect.ie)

---

*最后更新时间: 2025年7月18日*
