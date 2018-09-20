package com.plugin.test.sampleplugin.daggerboilerplate;

import com.coxautodev.graphql.tools.GraphQLResolver;
import dagger.Component;
import java.util.Set;
import javax.inject.Singleton;

@Singleton
@Component(modules = PluginModule.class)
public interface PluginRegistryComponent {
  Set<GraphQLResolver<?>> getGraphQLResolvers();
}
