package com.springboot.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	// create blog post rest api
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}

	// get all posts rest api
	@GetMapping
	public List<PostDto> getAllPosts() {
		return postService.getAllPosts();
	}

	// get post by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	// update post by id rest api
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {

		PostDto postResponse = postService.updatePost(postDto, id);

		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	// delete post rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deletePost(@PathVariable(name = "id") long id) {
		postService.deletePostById(id);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Post entity deleted successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
