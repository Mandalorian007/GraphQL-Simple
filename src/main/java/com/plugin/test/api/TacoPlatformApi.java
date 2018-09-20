package com.plugin.test.api;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plugin.test.sampleplugin.daggerboilerplate.DaggerPluginRegistryComponent;
import com.plugin.test.sampleplugin.daggerboilerplate.PluginRegistryComponent;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/graphql")
public class TacoPlatformApi {

  private final GraphQL graphQL;
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Inject
  public TacoPlatformApi() {
    //Dagger Boilerplate
    PluginRegistryComponent pluginRegistryComponent = DaggerPluginRegistryComponent.create();

    Set<GraphQLResolver<?>> graphQLResolvers = pluginRegistryComponent.getGraphQLResolvers();
    //Dagger Boilerplate

    GraphQLSchema graphQLSchema = SchemaParser.newParser()
        .file("user-story-1.graphqls")
        .resolvers(graphQLResolvers.stream().collect(Collectors.toList()))
        .build()
        .makeExecutableSchema();

    this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  @POST
  public Response helloWorld(String requestString) throws Exception {
    JSONObject jsonRequest = new JSONObject(requestString);
    ExecutionResult result = graphQL.execute(jsonRequest.getString("query"));

    List<GraphQLError> errors = result.getErrors();
    if(errors != null && !errors.isEmpty()) {
      return Response.ok(objectMapper.writeValueAsString(errors)).build();
    }

    return Response.ok(objectMapper.writeValueAsString(result.getData())).build();
  }
}
