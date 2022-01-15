package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dto.Comment;
import ru.otus.library.mapping.CommentMapper;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.CommentRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentMapper commentMapper;

    @Override
    public Comment get(Long id) {
        var optEntity = commentRepository.findById(id);
        if (optEntity.isPresent()) {
            return commentMapper.fromEntity(optEntity.get());
        }
        return Comment.builder().build();
    }

    @Transactional
    @Override
    public List<Comment> getByBookId(Long id) {
        var optBook = bookRepository.findById(id);
        return optBook.map(bookEntity -> bookEntity.getComments()
                .stream()
                .map(commentMapper::fromEntity)
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long save(Comment comment) {
        return commentRepository.save(commentMapper.toEntity(comment));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
