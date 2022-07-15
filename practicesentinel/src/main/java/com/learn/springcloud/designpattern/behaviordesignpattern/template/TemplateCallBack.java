package com.learn.springcloud.designpattern.behaviordesignpattern.template;

// 回调也是一种模板  JDBCTempalte , 给监听器传入方法
public class TemplateCallBack {
    
    public void doSomethin(TemplateCallBack callBack){
        callBack.callBack();
    }

    private void callBack() {
    }

}
