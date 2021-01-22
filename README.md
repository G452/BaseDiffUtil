# BaseDiffUtil
对于DiffUtil的简单封装示例
一、集成步骤
1、项目的build中

     allprojects {
    repositories {
      ……
      ……
        maven { url 'https://jitpack.io' }
    }
    }
    
2、主model的build中    

    implementation 'com.github.G452:BaseDiffUtil:1.0.0'
 
     
3、最后Sync Now搞定     

二、使用步骤可查看app model中的示例
