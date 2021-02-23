package main.service.impl;

import main.DTOs.User;
import main.service.SplitwiseInterface;
import main.util.SplitwiseHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vishnu.bhaskar
 * @created_on 02/02/21
 */
public class SplitwiseImpl implements SplitwiseInterface {
    Map<String, User> userMap=new HashMap<>();
    Map<String, User>idMap=new HashMap<>();

    Map<String, Double> valueMap= new HashMap<>();

    @Override
    public void splitEqual(String payer, List<String> splitAmong, Double amount) {
        for (String id:splitAmong) {
            if(id==payer)
                continue;
            String key=SplitwiseHelper.keygen(idMap.get(id),idMap.get(payer));
            Double totalAmount=amount/splitAmong.size();
            if(valueMap.containsKey(key))
                totalAmount+=valueMap.get(key);
            valueMap.put(key,totalAmount);
            this.amountOwed(idMap.get(id),idMap.get(payer));

        }
    }

    @Override
    public void splitExact(String payer, List<String> splitAmong, List<Double> exactAmount) {

        for (int i=0; i< splitAmong.size();i++) {
            if(splitAmong.get(i)==payer)
                continue;

            String key=SplitwiseHelper.keygen(idMap.get(splitAmong.get(i)),idMap.get(payer));
            Double totalAmount=exactAmount.get(i);
            if(valueMap.containsKey(key))
                totalAmount+=valueMap.get(key);
            valueMap.put(key,totalAmount);
            this.amountOwed(idMap.get(splitAmong.get(i)),idMap.get(payer));
        }
    }

    @Override
    public void splitPercentage(String payer, List<String> splitAmong, List<Double> percentages,Double amount) {
        for (int i=0; i< splitAmong.size();i++) {
            if(splitAmong.get(i)==payer)
                continue;

            String key=SplitwiseHelper.keygen(idMap.get(splitAmong.get(i)),idMap.get(payer));
            Double totalAmount=amount*percentages.get(i)/100;
            if(valueMap.containsKey(key))
                totalAmount+=valueMap.get(key);
            valueMap.put(key,totalAmount);
            this.amountOwed(idMap.get(splitAmong.get(i)),idMap.get(payer));
        }
    }

    @Override
    public void showAll() {
        for (Map.Entry<String, Double> entry:valueMap.entrySet()) {
            List<String> usersList= SplitwiseHelper.decodeKey(entry.getKey());
            amountOwed(idMap.get(usersList.get(0)),idMap.get(usersList.get(1)));
        }
    }

    @Override
    public void createUser(String user, String id) {
        if(userMap.containsKey(user))
        {
            System.out.println("user already exists");
            return;
        }
        if(userMap.containsKey(id))
        {
            System.out.println("id already exists");
            return;
        }
        User user1=new User(id,user);
        userMap.put(user,user1);
        idMap.put(id,user1);
    }
    private void amountOwed(User owes,User lender)
    {
        String key1= SplitwiseHelper.keygen(owes,lender);
        String key2=SplitwiseHelper.keygen(lender,owes);
        Double amount=0D;
        if(!valueMap.containsKey(key1))
            return;
        amount=valueMap.get(key1);
        if(valueMap.containsKey(key2))
            amount-=valueMap.get(key2);
        if(amount>0)
            System.out.println(owes.getName()+" owes "+lender.getName()+": "+ amount.toString());
    }
}
