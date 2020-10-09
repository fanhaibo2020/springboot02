package com.example.designPattern.responsibilityChain;

/**
 * @ClassName CollegeApprover
 * @Author fhb
 * @Date 2020/5/10 0:22
 * @Version 1.0
 **/
public class CollegeApprover extends Approver{
    public CollegeApprover(String name){
        super(name); //父类的构造
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if(purchaseRequest.getPrice()<=10000&&purchaseRequest.getPrice()>5000){
            System.out.println("请求编号id="+purchaseRequest.getId()+"被"+this.name+"处理");
        }else{
            approver.processRequest(purchaseRequest);
        }
    }
}
