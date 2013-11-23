package net.sf.anathema.character.main.magic.parser.charms.special;

import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import org.dom4j.Element;

@DoNotInstantiateAutomatically
public class ConfigurableDummySpecialCharmParser implements SpecialCharmParser {
  private Element element;
  private String charmId;

  public ConfigurableDummySpecialCharmParser support(Element element) {
    this.element = element;
    return this;
  }

  public ConfigurableDummySpecialCharmParser with(String id) {
    this.charmId = id;
    return this;
  }

  @Override
  public void parse(Element charmElement, SpecialCharmDto overallDto) {
    if (charmElement.equals(element)) {
      overallDto.charmId = charmId;
    }
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.equals(this.element);
  }
}