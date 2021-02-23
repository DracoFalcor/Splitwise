package main;

import main.service.SplitwiseInterface;
import main.service.impl.SplitwiseImpl;

import java.util.*;

/**
 * @author vishnu.bhaskar
 * @created_on 02/02/21
 */
public class Application {

    public static void main(String[] args) {
        SplitwiseInterface splitwise= new SplitwiseImpl();
        Scanner scanner= new Scanner(System.in);

        splitwise.createUser("user1","u1");

        splitwise.createUser("user2","u2");

        splitwise.createUser("user3","u3");

        splitwise.createUser("user4","u4");

        splitwise.createUser("user5","u5");

        while(true)
        {
            String command=scanner.nextLine();
            if(command.equalsIgnoreCase("exit"))
                return;
            List<String> commands= Arrays.asList(command.split(" "));
            Double amount=Double.valueOf(commands.get(1));
            String payer= commands.get(0);
            Integer people= Integer.valueOf(commands.get(2));
            List<String> splitAmong=new LinkedList<>();
            for(int i=0;i<people;i++)
            {
                splitAmong.add(commands.get(3+i));
            }
            String type=commands.get(3+people);
            if(type.equalsIgnoreCase("EQUAL"))
            {
                splitwise.splitEqual(payer,splitAmong,amount);
                continue;
            }
            List<Double> splitAmount2=new LinkedList<>();
            Double temp=0D;
            for(int i=0;i<people;i++)
            {
                splitAmount2.add(Double.valueOf(commands.get(4+people+i)));
                temp+=splitAmount2.get(i);
            }

            if(type.equalsIgnoreCase("PERCENT"))
            {
                if(!temp.equals(100D))
                {
                    System.out.println("ERROR");
                    continue;
                }
                splitwise.splitPercentage(payer,splitAmong,splitAmount2,amount);
            }
            if(type.equalsIgnoreCase("EXACT")){
                if(!temp.equals(amount))
                {
                    System.out.println("ERROR");
                    continue;
                }
                splitwise.splitExact(payer,splitAmong,splitAmount2);
            }
        }
    }
}
