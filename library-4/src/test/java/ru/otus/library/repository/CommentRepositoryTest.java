package ru.otus.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.library.model.CommentDocument;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class CommentRepositoryTest {
    private static final CommentDocument SAVED_COMMENT = CommentDocument.builder()
            .id("C_TEST_ID_1")
            .text("TEST_COMMENT_1")
            .bookId("B_TEST_ID_1")
            .build();

    private static final CommentDocument EXPECTED_COMMENT = CommentDocument.builder()
            .id("C_TEST_ID_2")
            .text("TEST_COMMENT_2")
            .bookId("B_TEST_ID_1")
            .build();

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findByIdTest() {
        var result = commentRepository.findById(SAVED_COMMENT.getId());
        assertThat(result).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(SAVED_COMMENT);
    }

    @Test
    void findAllTest() {
        var result = commentRepository.findAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(Collections.singletonList(SAVED_COMMENT));
    }

    @Test
    void saveTest() {
        var result = commentRepository.save(EXPECTED_COMMENT);
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_COMMENT);
        commentRepository.deleteById(result.getId());
    }

    @Test
    void deleteByIdTest() {
        var savedComment =  commentRepository.findById(SAVED_COMMENT.getId());
        assertThat(savedComment).isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(SAVED_COMMENT);
        commentRepository.deleteById(SAVED_COMMENT.getId());
        var result = commentRepository.findById(SAVED_COMMENT.getId());
        assertThat(result)
                .isEqualTo(Optional.empty());
        commentRepository.save(savedComment.get());
    }
}
