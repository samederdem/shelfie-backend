package ie.shelf.shelfie;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.gson.GsonFactory;

public class Auth {
    public static GoogleIdToken verifyToken(String token){
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(new NetHttpTransport(), GsonFactory.getDefaultInstance());
            return verifier.verify(token);
        }
        catch(Exception e)
        {
            System.out.println(token.length());
            e.printStackTrace();
            return null;
        }
    }
    public static String getEmail (GoogleIdToken token)
    {
        Payload payload = token.getPayload();
        return payload.getEmail();
    }
}
