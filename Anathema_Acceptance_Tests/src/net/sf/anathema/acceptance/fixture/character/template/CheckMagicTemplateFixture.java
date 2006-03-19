package net.sf.anathema.acceptance.fixture.character.template;

public class CheckMagicTemplateFixture extends AbstractTemplateColumnFixture {

  public String getFavoringTraitType() {
    return getTemplate().getMagicTemplate().getFavoringTraitType().getId();
  }
}