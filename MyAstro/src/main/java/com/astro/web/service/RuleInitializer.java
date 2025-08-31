package com.astro.web.service;

import com.astro.web.model.Rule;
import com.astro.web.repository.RuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RuleInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RuleInitializer.class);

    private final RuleRepository ruleRepository;

    public RuleInitializer(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (ruleRepository.count() > 0) {
            logger.info("Rules already populated. Skipping data initialization.");
            return;
        }

        logger.info("Rules table is empty. Initializing rules...");

        try {
            Rule rule1 = new Rule();
            rule1.setName("NewRule1");
            rule1.setDescription("Finds planets that are in the same sign in both D1 and D9 charts (Vargottama).");
            ruleRepository.save(rule1);

            Rule rule2 = new Rule();
            rule2.setName("NewRule2");
            rule2.setDescription("Checks if the 7th lord of the D1 chart is in a specific sign in the D9 chart.");
            ruleRepository.save(rule2);

            Rule rule3 = new Rule();
            rule3.setName("NewRule3");
            rule3.setDescription("Finds planets that are in the same sign in both D1 and D9 charts (Vargottama) and prints the sign.");
            ruleRepository.save(rule3);

            Rule rule4 = new Rule();
            rule4.setName("NewRule4");
            rule4.setDescription("Determines the sign of the D1 chart based on the ascendant.");
            ruleRepository.save(rule4);

            Rule rule5 = new Rule();
            rule5.setName("NewRule5");
            rule5.setDescription("Finds Vargottama planets (planets in the same sign in D1 and D9) and prints the planet and sign.");
            ruleRepository.save(rule5);

            Rule rule6 = new Rule();
            rule6.setName("NewRule6");
            rule6.setDescription("A generic rule that can be configured with a planet, sign, and chart type (D1 or D9).");
            ruleRepository.save(rule6);

            logger.info("Successfully loaded 6 rules into the database.");
        } catch (Exception e) {
            logger.error("Failed to initialize rules", e);
        }
    }
}
