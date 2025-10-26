package com.userauth.userauth.databaselayer;

import com.userauth.userauth.service.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final SeedService seedService;

    public DatabaseSeeder(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) {
        seedService.seedRolesAndPermissions();
        seedService.seedAdminUser();
    }
}
