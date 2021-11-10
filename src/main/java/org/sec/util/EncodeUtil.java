package org.sec.util;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("all")
public class EncodeUtil {
    public static String encryption(String str, int offset) {
        char c;
        StringBuilder str1 = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char) (((c - 'a') + offset) % 26 + 'a');
            } else if (c >= 'A' && c <= 'Z') {
                c = (char) (((c - 'A') + offset) % 26 + 'A');
            } else if (c >= '0' && c <= '9') {
                c = (char) (((c - '0') + offset) % 10 + '0');
            } else {
                str1 = new StringBuilder(str);
                break;
            }
            str1.append(c);
        }
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(str1.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static String dec(String str, int offset) {
        try {
            byte[] code = new sun.misc.BASE64Decoder().decodeBuffer(str);
            str = new String(code);
            char c;
            StringBuilder str1 = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                c = str.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    c = (char) (((c - 'a') - offset + 26) % 26 + 'a');
                } else if (c >= 'A' && c <= 'Z') {
                    c = (char) (((c - 'A') - offset + 26) % 26 + 'A');
                } else if (c >= '0' && c <= '9') {
                    c = (char) (((c - '0') - offset + 10) % 10 + '0');
                } else {
                    str1 = new StringBuilder(str);
                    break;
                }
                str1.append(c);
            }
            String result = str1.toString();
            result = result.replace("\\\"","\"");
            result = result.replace("\\n","\n");
            return result;
        } catch (Exception ignored) {
            return "";
        }
    }
}
