package net.sf.anathema.character.generic.dummy.template;

import net.sf.anathema.character.generic.framework.xml.ITemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.registry.Registry;

public class DummyXmlTemplateRegistry<V> extends Registry<String, V> implements IXmlTemplateRegistry<V>{

  public void setTemplateParser(ITemplateParser<V> templateParser) {
    // Nothing to do
  }
  
  public V get(String id, String prefix) {
    return null;
  }
}