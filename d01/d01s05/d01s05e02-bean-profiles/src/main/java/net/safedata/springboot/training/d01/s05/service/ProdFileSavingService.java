package net.safedata.springboot.training.d01.s05.service;

import net.safedata.springboot.training.d01.s05.config.Profiles;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(Profiles.PROD)
public class ProdFileSavingService implements FileSavingService {

    @Override
    public void saveFile(String fileName) {
        System.out.println("Saving the file on an SFTP server...");
    }
}
