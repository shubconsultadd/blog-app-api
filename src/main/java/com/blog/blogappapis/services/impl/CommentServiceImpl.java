package com.blog.blogappapis.services.impl;

import com.blog.blogappapis.entities.Comment;
import com.blog.blogappapis.entities.Post;
import com.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.blog.blogappapis.payloads.CommentDto;
import com.blog.blogappapis.payloads.CommentResponse;
import com.blog.blogappapis.payloads.PostDto;
import com.blog.blogappapis.repositories.CommentRepo;
import com.blog.blogappapis.repositories.PostRepo;
import com.blog.blogappapis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post"," Id ",postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment"," Id ",commentId));
        this.commentRepo.delete(comment);
    }

    @Override
    public CommentDto getComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment"," Id ",commentId));
        return this.modelMapper.map(comment,CommentDto.class);
    }

    // TODO
    @Override
    public CommentResponse getCommentsByPostId(Integer postId, Integer pageNumber, Integer pageSize, String sortBy, String sortValue) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post"," Id ",postId));
        Pageable pageable;
        if("desc".equalsIgnoreCase(sortValue))
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        else
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());

        Page<Comment> list = this.commentRepo.findByPostId(postId,pageable);

        List<Comment> allComments = list.getContent();
        List<CommentDto> commentDtoList = allComments.stream().map((comment -> this.modelMapper.map(comment,CommentDto.class))).collect(Collectors.toList());

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(commentDtoList);
        commentResponse.setPageNumber(list.getNumber());
        commentResponse.setPageSize(list.getSize());
        commentResponse.setTotalElements(list.getTotalElements());
        commentResponse.setTotalPages(list.getTotalPages());
        commentResponse.setLastPage(list.isLast());
        return commentResponse;
    }
}
