package main.util;

import main.DTOs.User;

import java.util.Arrays;
import java.util.List;

/**
 * @author vishnu.bhaskar
 * @created_on 02/02/21
 */
public class SplitwiseHelper {
    static String delimiter="##";
    public static String keygen(User u1,User u2)
    {
        return u1.getId()+delimiter+u2.getId();
    }
    public static List<String> decodeKey(String key)
    {
        return Arrays.asList(key.split(delimiter));
    }
}
