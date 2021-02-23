package main.service;

import main.DTOs.User;

import java.util.List;

/**
 * @author vishnu.bhaskar
 * @created_on 02/02/21
 */
public interface SplitwiseInterface {

    void splitEqual(String payer, List<String> splitAmong,Double amount);

    void splitExact(String payer, List<String> splitAmong,List<Double> exactAmount);

    void splitPercentage(String payer,List<String> splitAmong,List<Double> percentages,Double amount);

    void showAll();

    void createUser(String user,String id);
}
