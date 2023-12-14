package com.andreidodu.elisyshomeautomation.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EntityScan("com.andreidodu.elisyshomeautomation.model")
@EnableTransactionManagement
@EnableJpaRepositories("com.andreidodu.elisyshomeautomation.repository")
public class PersistenceConfiguration {
}
