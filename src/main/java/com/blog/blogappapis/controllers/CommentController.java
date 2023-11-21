package com.blog.blogappapis.controllers;

import com.blog.blogappapis.config.AppConstants;
import com.blog.blogappapis.payloads.ApiResponse;
import com.blog.blogappapis.payloads.CommentDto;
import com.blog.blogappapis.payloads.CommentResponse;
import com.blog.blogappapis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
        CommentDto savedComment = this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentDto> getComment(
            @PathVariable Integer commentId,
            @RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value="sort",defaultValue = AppConstants.SORT_VALUE, required = false) String sortValue
                                                 ){
        CommentDto commentDto = this.commentService.getComment(commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted Successfully!",true),HttpStatus.OK);
    }

    @GetMapping("/comments/{postId}")
    public ResponseEntity<CommentResponse> getCommentsByPostId(
            @PathVariable Integer postId,
            @RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value="sort",defaultValue = AppConstants.SORT_VALUE, required = false) String sortValue
    ){
        CommentResponse commentResponse = this.commentService.getCommentsByPostId(postId,pageNumber,pageSize,sortBy,sortValue);
        return new ResponseEntity<>(commentResponse,HttpStatus.OK);
    }
}
