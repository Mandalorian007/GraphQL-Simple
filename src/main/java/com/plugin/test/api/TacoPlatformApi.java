package com.plugin.test.api;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plugin.test.sampleplugin.domain.LinkRepository;
import com.plugin.test.sampleplugin.resolvers.Query;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/graphql")
public class TacoPlatformApi {

  private final GraphQL graphQL;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public TacoPlatformApi() {
    List<GraphQLResolver<?>> resolvers = new ArrayList<>();

    LinkRepository linkRepository = new LinkRepository();
    Query query = new Query(linkRepository);

    resolvers.add(query);

    GraphQLSchema graphQLSchema = SchemaParser.newParser()
        .file("user-story-1.graphqls")
        .resolvers(resolvers)
        .build()
        .makeExecutableSchema();

    this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  @POST
  public Response helloWorld(String requestString) throws Exception {
    System.out.println(requestString);
    JSONObject jsonRequest = new JSONObject(requestString);
    ExecutionResult result = graphQL.execute(jsonRequest.getString("query"));

    List<GraphQLError> errors = result.getErrors();
    if(errors != null && !errors.isEmpty()) {
      return Response.ok(objectMapper.writeValueAsString(errors)).build();
    }

    return Response.ok(objectMapper.writeValueAsString(result.getData())).build();
  }
}
