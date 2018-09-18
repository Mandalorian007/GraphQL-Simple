package com.plugin.test.sampleplugin.daggerboilerplate;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = ResoloversModule.class)
public interface PluginRegistryComponent {
  PluginRegistry buildPluginRegistry();
}
