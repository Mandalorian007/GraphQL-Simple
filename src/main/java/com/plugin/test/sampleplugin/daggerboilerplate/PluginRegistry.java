package com.plugin.test.sampleplugin.daggerboilerplate;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.plugin.test.sampleplugin.resolvers.Query;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class PluginRegistry {
  private final List<GraphQLResolver<?>> graphQLResolvers;

  @Inject
  public PluginRegistry(Query query) {
    graphQLResolvers = new ArrayList<>();
    graphQLResolvers.add(query);

  }

  public List<GraphQLResolver<?>> getGraphQLResolvers() {
    return graphQLResolvers;
  }
}
