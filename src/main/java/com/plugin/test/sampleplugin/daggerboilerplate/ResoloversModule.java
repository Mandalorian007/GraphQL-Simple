package com.plugin.test.sampleplugin.daggerboilerplate;

import com.plugin.test.sampleplugin.domain.LinkRepository;
import com.plugin.test.sampleplugin.resolvers.Query;
import dagger.Module;
import dagger.Provides;

@Module
public class ResoloversModule {

  @Provides
  public Query provideQuery() {
    LinkRepository linkRepository = new LinkRepository();
    return new Query(linkRepository);
  }
}
