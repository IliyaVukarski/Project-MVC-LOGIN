package app.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class HashingServiceImp implements  HashingService {

    @Override
    public String hash(String password) {
        return DigestUtils.sha256Hex(password);
    }
}