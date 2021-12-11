package com.learn.springcloud.createdesignpattern;

import java.util.HashMap;

public class BuilderTest {

    public static HashMap map = new HashMap();
    private boolean isRef;
    private Class aClass;
    private Object arg;


    public boolean isRef() {
        return isRef;
    }

    private void setRef(boolean ref) {
        isRef = ref;
    }

    public Class getaClass() {
        return aClass;
    }

    private void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Object getArg() {
        return arg;
    }

    private void setArg(Object arg) {
        this.arg = arg;
    }
    public static class Builder{
        private boolean isRef;
        private Class aClass;
        private Object arg;

        public boolean isRef() {
            return isRef;
        }

        public void setRef(boolean ref) {
            isRef = ref;
        }

        public Class getaClass() {
            return aClass;
        }

        public void setaClass(Class aClass) {
            this.aClass = aClass;
        }

        public Object getArg() {
            return arg;
        }

        public void setArg(Object arg) {
            this.arg = arg;
        }


        private BuilderTest build(){
            if(isRef){
                if(null==map.get(String.valueOf(arg))){
                    throw new RuntimeException();
                }
            }else{
                if(null==arg|| null==aClass ){
                    throw new RuntimeException();
                }
            }
            BuilderTest builderTest = new BuilderTest();
            builderTest.setArg(arg);
            builderTest.setaClass(aClass);
            builderTest.setRef(isRef);
            return builderTest;
        }

    }
}
