package com.graphqlspringdemo;

import com.coxautodev.graphql.tools.SchemaParser;
import com.graphqlspringdemo.repository.AuthorRepository;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import org.jetbrains.annotations.NotNull;
import com.graphqlspringdemo.repository.PostRepository;
import com.graphqlspringdemo.resolver.ql.AuthorResolver;
import com.graphqlspringdemo.resolver.ql.PostResolver;
import com.graphqlspringdemo.resolver.root.Mutation;
import com.graphqlspringdemo.resolver.root.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*",maxAge = 3600)
public class GraphQLEntryPoint extends SimpleGraphQLServlet {

	public GraphQLEntryPoint(PostRepository postRepository, AuthorRepository authRepository) {
		super(buildSchema(postRepository,authRepository));
	}

	@NotNull
	private static GraphQLSchema buildSchema(PostRepository postRepository, AuthorRepository authRepository ) {
		return SchemaParser
				.newParser()
				.file("schema.graphqls")
				.resolvers(
						new Query(postRepository,authRepository),
						new Mutation(authRepository,postRepository ),
						new PostResolver(authRepository),
						new AuthorResolver(postRepository))
				.build()
				.makeExecutableSchema();
	}
}
