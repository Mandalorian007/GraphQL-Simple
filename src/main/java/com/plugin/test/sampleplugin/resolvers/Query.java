package com.plugin.test.sampleplugin.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.plugin.test.sampleplugin.domain.Link;
import com.plugin.test.sampleplugin.domain.LinkRepository;
import java.util.List;

public class Query implements GraphQLRootResolver {

  private final LinkRepository linkRepository;

  public Query(LinkRepository linkRepository) {
    this.linkRepository = linkRepository;
  }

  public List<Link> allLinks() {
    return linkRepository.getAllLinks();
  }
}
