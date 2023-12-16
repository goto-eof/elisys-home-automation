package com.andreidodu.elisyshomeautomation.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.andreidodu.elisyshomeautomation.entity")
@EnableTransactionManagement
@EnableJpaRepositories("com.andreidodu.elisyshomeautomation.repository")
public class PersistenceConfiguration {

}
