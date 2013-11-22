package net.sf.anathema.hero.concept.persistence.description;

import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.lib.util.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class DescriptionPersister extends AbstractModelJsonPersister<DescriptionPto, HeroDescription> {

  public DescriptionPersister() {
    super("description", DescriptionPto.class);
  }

  @Override
  public Identifier getModelId() {
    return HeroDescription.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, HeroDescription description, DescriptionPto pto) {
    description.getName().setText(pto.name);
    description.getPlayer().setText(pto.player);
    description.getCharacterization().setText(pto.characterization);
    description.getPhysicalDescription().setText(pto.physicals);
    description.getNotes().setText(pto.notes);
    description.getConcept().setText(pto.concept);
    description.getEyes().setText(pto.eyes);
    description.getHair().setText(pto.hair);
    description.getSex().setText(pto.sex);
    description.getSkin().setText(pto.skin);
    description.getAnima().setText(pto.anima);
  }

  @Override
  protected DescriptionPto saveModelToPto(HeroDescription description) {
    DescriptionPto pto = new DescriptionPto();
    pto.name = description.getName().getText();
    pto.player = description.getPlayer().getText();
    pto.characterization = description.getCharacterization().getText();
    pto.physicals = description.getPhysicalDescription().getText();
    pto.notes = description.getNotes().getText();
    pto.concept = description.getConcept().getText();
    pto.eyes = description.getEyes().getText();
    pto.hair = description.getHair().getText();
    pto.sex = description.getSex().getText();
    pto.skin = description.getSkin().getText();
    pto.anima = description.getAnima().getText();
    return pto;
  }
}
