# MyTool

#### 介绍
viewBinding 关于 activity,Adapter,Dialog,Fragment 的封装 以及包含一个BaseUtils的一个工具类

#### 暂停维护 转到 https://github.com/luoye000/BaseApp

#### 安装教程

#### 项目：build


```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
#### App：build

```
android {
    ...
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    buildFeatures {viewBinding true}

}

dependencies {
   implementation 'com.github.luoye000:MyTool:v1.0.3'
}
```
#### 使用说明

1.  混淆 -keep class  包名.databinding.* {*;}



