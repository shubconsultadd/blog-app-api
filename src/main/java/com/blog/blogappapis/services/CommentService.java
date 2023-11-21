package com.blog.blogappapis.services;

import com.blog.blogappapis.payloads.CommentDto;
import com.blog.blogappapis.payloads.CommentResponse;
import com.blog.blogappapis.payloads.PostDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);

    CommentDto getComment(Integer commentId);

    CommentResponse getCommentsByPostId(Integer postId, Integer pageNumber, Integer pageSize, String sortBy, String sortValue);
}
