package net.sf.anathema.acceptance.fixture.character.template;

public class CheckEditionFixture extends AbstractTemplateColumnFixture {

  public String getEdition() {
    return getTemplate().getEdition().getId();
  }
}