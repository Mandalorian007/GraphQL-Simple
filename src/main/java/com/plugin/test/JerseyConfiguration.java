package com.plugin.test;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfiguration extends ResourceConfig {

  public JerseyConfiguration() {
    packages("com.plugin.test.api");
  }
}