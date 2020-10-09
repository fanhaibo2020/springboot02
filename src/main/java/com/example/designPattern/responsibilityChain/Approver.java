package com.example.designPattern.responsibilityChain;

/**
 * @ClassName Approver
 * @Author fhb
 * @Date 2020/5/10 0:03
 * @Version 1.0
 **/
public abstract class Approver {
    Approver approver; //下一个处理者
    String name; //名字

    public Approver(String name){
        this.name = name;
    }

    //下一个处理者
    public void setApprover(Approver approver){
        this.approver = approver;
    }

    //处理审批请求的方法，得到一个方法，处理是子类去完成的，因此该方法为抽象
    public abstract void processRequest(PurchaseRequest purchaseRequest);

}
