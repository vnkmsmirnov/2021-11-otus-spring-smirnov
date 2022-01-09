package ru.otus.library.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.library.model.CommentEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CommentRepositoryTest {
    private static final CommentEntity SAVED_COMMENT = CommentEntity.builder()
            .id(1L)
            .text("TEST_COMMENT_1")
            .bookId(1L)
            .build();

    private static final CommentEntity EXPECTED_COMMENT = CommentEntity.builder()
            .id(2L)
            .text("TEST_COMMENT_2")
            .bookId(1L)
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
    void findByBookIdTest() {
        var result = commentRepository.findByBookId(SAVED_COMMENT.getId());
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(SAVED_COMMENT);
    }

    @Test
    void findAllTest() {
        var result = commentRepository.findAll();
        assertThat(result)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(SAVED_COMMENT);
    }

    @Test
    void saveTest() {
        var result = commentRepository.save(EXPECTED_COMMENT);
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(EXPECTED_COMMENT);
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
    }
}
