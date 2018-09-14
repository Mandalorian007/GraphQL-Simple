package com.plugin.test.api;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import java.util.List;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TacoPlatformApi {

  private final GraphQL graphQL;

  public TacoPlatformApi(List<GraphQLResolver<?>> resolvers) {
    GraphQLSchema graphQLSchema = SchemaParser.newParser()
        .file("user-story-1.graphqls")
        .resolvers(resolvers)
        .build()
        .makeExecutableSchema();

    this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  @PostMapping("/graphql")
  public Object graphQlRequest(@RequestBody String requestString) {
    JSONObject jsonRequest = new JSONObject(requestString);
    ExecutionResult result = graphQL.execute(jsonRequest.getString("query"));

    List<GraphQLError> errors = result.getErrors();
    if(errors != null && !errors.isEmpty()) {
      return errors;
    }

    return result.getData();
  }
}
