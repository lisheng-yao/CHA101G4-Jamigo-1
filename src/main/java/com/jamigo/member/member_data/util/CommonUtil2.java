package com.jamigo.member.member_data.util;


import com.jamigo.member.member_data.entity.MemberData;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.security.SecureRandom;
@Component
public class CommonUtil2 {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;

    public static String RandonCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            code.append(randomChar);
        }

        return code.toString();
    }

    private static String randomCode = RandonCode();

    public static void saveRandonCode(MemberData memberData, String randomCode) {
        Jedis jedis = new Jedis("localhost", 6379);
        String email = memberData.getMemberEmail();
        jedis.set(email, randomCode);
        jedis.expire(email, 30);

    }

    public static void getRandonCode(MemberData memberData, String RandonCode) {
        Jedis jedis = new Jedis("localhost", 6379);
        String email = memberData.getMemberEmail();
        String checkEmail = jedis.get(email);
    }

    ;

}

