package com.plugin.test.sampleplugin.daggerboilerplate;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.plugin.test.sampleplugin.domain.LinkRepository;
import com.plugin.test.sampleplugin.resolvers.Query;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class PluginModule {

  @Provides
  Query providesQuery() {
    return new Query(new LinkRepository());
  }

  @Provides @IntoSet
  GraphQLResolver<?> provideGraphQLResolver(Query query) {
    return query;
  }

}
