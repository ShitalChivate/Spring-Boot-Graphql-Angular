package com.graphqlspringdemo.resolver.root;

import java.util.List;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.graphqlspringdemo.pojo.Author;
import com.graphqlspringdemo.pojo.Post;
import com.graphqlspringdemo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.graphqlspringdemo.repository.PostRepository;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/rest/graphql")
public class Query implements GraphQLRootResolver {


	public Query(PostRepository postRepository, AuthorRepository authRepo) {
		super();
		this.postRepository = postRepository;
		this.authRepo = authRepo;
	}
	@Autowired
	private  PostRepository postRepository;
	@Autowired
	private  AuthorRepository authRepo;

	@GetMapping("/getProlist")
	public List<Post> allOrders() {
		return postRepository.findAll();
	}

	@GetMapping("/list")
	public List<Author> allAuthors() {
		return authRepo.findAll();
	}

	public Author getAuthor(String Id){
		Author a = authRepo.findById(Id);
		return a;
	}
}
