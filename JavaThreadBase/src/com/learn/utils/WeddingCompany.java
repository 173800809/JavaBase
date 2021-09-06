package com.learn.utils;

public class WeddingCompany implements Marry{

    private Marry target;

    public WeddingCompany(Marry target){
        this.target = target;
    }

    @Override
    public void HappyMarry(){
        before();
        this.target.HappyMarry();
        after();
    }

    private void before() {
        System.out.println("结婚之前布置现场");
    }

    private void after(){
        System.out.println("结婚之后付尾款");
    }
}
