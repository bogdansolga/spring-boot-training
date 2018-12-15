package net.safedata.springboot.training.d01.s05.service;

import net.safedata.springboot.training.d01.s05.RunProfiles;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(RunProfiles.DEV)
public class DevFileSavingService implements FileSavingService {

    @Override
    public void saveFile(String fileName) {
        System.out.println("Saving the file locally...");
    }
}
