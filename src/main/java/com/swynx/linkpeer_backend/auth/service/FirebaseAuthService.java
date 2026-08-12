package com.swynx.linkpeer_backend.auth.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseAuthService {
    public FirebaseToken verifyToken(String token) throws ExecutionException, InterruptedException {
        return FirebaseAuth.getInstance().verifyIdTokenAsync(token).get();
    }
}
