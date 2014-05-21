package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.character.framework.xml.ITemplateParser;
import net.sf.anathema.character.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.framework.environment.resources.ResourceFile;

public class DummyXmlTemplateRegistry<V> extends Registry<String, V> implements IXmlTemplateRegistry<V> {

  @Override
  public void setTemplateParser(ITemplateParser<V> templateParser) {
    // Nothing to do
  }

  @Override
  public V get(String id, String prefix) {
    return null;
  }

  @Override
  public V get(ResourceFile resource) {
    return null;
  }
}