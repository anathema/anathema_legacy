package net.sf.anathema;

import org.java.plugin.boot.Application;
import org.java.plugin.boot.ApplicationPlugin;
import org.java.plugin.util.ExtendedProperties;

public class AnathemaApplicationPlugin extends ApplicationPlugin {

  @Override
  protected Application initApplication(ExtendedProperties config, String[] args) throws Exception {
    return new Anathema();
  }

  @Override
  protected void doStart() throws Exception {
    // TODO Auto-generated method stub
  }

  @Override
  protected void doStop() throws Exception {
    // TODO Auto-generated method stub
  }
}