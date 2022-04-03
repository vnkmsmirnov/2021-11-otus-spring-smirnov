package ru.otus.library.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.lang.NonNull;
import ru.otus.library.jpa.model.BookEntity;
import ru.otus.library.mongo.model.BookDocument;
import ru.otus.library.service.migration.LibraryMigrationService;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobConfig {

    private static final int CHUNK_SIZE = 5;
    public static final String IMPORT_LIBRARY_JOB_NAME = "importBookJob";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoOperations mongoOperations;
    private final EntityManagerFactory entityManagerFactory;

    @StepScope
    @Bean
    public MongoItemReader<BookDocument> reader() {
        return new MongoItemReaderBuilder<BookDocument>()
                .name("book")
                .template(mongoOperations)
                .jsonQuery("{}")
                .targetType(BookDocument.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<BookDocument, BookEntity> processor(LibraryMigrationService migrationService) {
        return migrationService::prepareBook;
    }

    @StepScope
    @Bean
    public JpaItemWriter<BookEntity> writer() {
        return new JpaItemWriterBuilder<BookEntity>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Job importLibraryJob(Step fromMongoToPostgres) {
        return jobBuilderFactory.get(IMPORT_LIBRARY_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(fromMongoToPostgres)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        log.info("Start migration");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        log.info("End migration");
                    }
                })
                .build();
    }

    @Bean
    public Step fromMongoToPostgres(ItemReader<BookDocument> reader, JpaItemWriter<BookEntity> writer,
                                    ItemProcessor<BookDocument, BookEntity> itemProcessor) {
        return stepBuilderFactory.get("step1")
                .<BookDocument, BookEntity>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }
}
