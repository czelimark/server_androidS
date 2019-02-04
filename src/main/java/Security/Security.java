package Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.ArrayList;

public class Security
{
    public static String secret="secret";
    private static ArrayList<String> tokens= new ArrayList<>();
    public static String createToken(String secret,String issuer)
    {
        try
        {
            Algorithm algorithm= Algorithm.HMAC256(secret);
            String token= JWT.create().withIssuer(issuer).sign(algorithm);
            return  token;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static void useToken(String token)
    {
        tokens.add(token);
    }

    public static boolean checkToken(String token)
    {
        return tokens.contains(token);
    }

}
