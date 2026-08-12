package com.swynx.linkpeer_backend.auth.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    public FirebaseApp firebaseApp() throws IOException{
        String credentialsPAth=System.getenv("FIREBASE_CREDENTIALS_PATH");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(
                        GoogleCredentials.fromStream(
                                new FileInputStream(credentialsPAth)
                        )
                )
                .build();

        return FirebaseApp.initializeApp(options);
    }

}
