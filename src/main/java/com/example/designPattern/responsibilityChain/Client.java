package com.example.designPattern.responsibilityChain;

/**
 * @ClassName Client
 * @Author fhb
 * @Date 2020/5/10 0:23
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        //创建一个请求
        PurchaseRequest PurchaseRequest = new PurchaseRequest(1,6000,1);
        //创建相关的审批人
        DepartmentApprover  departmentApprover= new DepartmentApprover("张系主任");
        CollegeApprover  collegeApprover= new CollegeApprover("王院长");

        //需要将各个审批级别的下一个设置好(处理人要构成一个环状,即一定要找到一个人去处理)
        departmentApprover.setApprover(collegeApprover);

        departmentApprover.processRequest(PurchaseRequest);
    }
}
