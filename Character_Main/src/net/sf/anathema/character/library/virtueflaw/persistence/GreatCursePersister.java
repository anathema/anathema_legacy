package net.sf.anathema.character.library.virtueflaw.persistence;

import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.virtueflaw.model.DescriptiveVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.model.VirtueFlawModel;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.HeroModelPersisterCollected;
import net.sf.anathema.lib.util.Identifier;

@HeroModelPersisterCollected
public class GreatCursePersister extends AbstractModelJsonPersister<VirtueFlawPto, VirtueFlawModel> {

  public GreatCursePersister() {
    super("greatCurse", VirtueFlawPto.class);
  }

  @Override
  protected void fillModel(VirtueFlawModel model, VirtueFlawPto pto) {
    Trait limitTrait = model.getVirtueFlaw().getLimitTrait();
    limitTrait.setUncheckedCreationValue(pto.limit.creationValue);
    if (pto.limit.experienceValue != null) {
      limitTrait.setUncheckedExperiencedValue(pto.limit.experienceValue);
    }
    if (pto.rootVirtue != null) {
      model.getVirtueFlaw().setRoot(VirtueType.valueOf(pto.rootVirtue));
    }
    if (model.getVirtueFlaw() instanceof  DescriptiveVirtueFlaw) {
      DescriptiveVirtueFlaw virtueFlaw = (DescriptiveVirtueFlaw) model.getVirtueFlaw();
      virtueFlaw.getLimitBreak().setText(pto.limitBreak);
      virtueFlaw.getDescription().setText(pto.description);
    }
    model.getVirtueFlaw().getName().setText(pto.name);
  }

  @Override
  protected VirtueFlawPto createPto(VirtueFlawModel heroModel) {
    VirtueFlawPto pto = new VirtueFlawPto();
    pto.name = heroModel.getVirtueFlaw().getName().getText();
    pto.limit.creationValue = heroModel.getVirtueFlaw().getLimitTrait().getCreationValue();
    int experienceValue = heroModel.getVirtueFlaw().getLimitTrait().getExperiencedValue();
    if (experienceValue >= 0) {
      pto.limit.experienceValue = experienceValue;
    }
    if (heroModel.getVirtueFlaw().getRoot() != null) {
      pto.rootVirtue = heroModel.getVirtueFlaw().getRoot().getId();
    }
    if (heroModel.getVirtueFlaw() instanceof  DescriptiveVirtueFlaw) {
      DescriptiveVirtueFlaw virtueFlaw = (DescriptiveVirtueFlaw) heroModel.getVirtueFlaw();
      pto.limitBreak = virtueFlaw.getLimitBreak().getText();
      pto.description = virtueFlaw.getDescription().getText();
    }
    return pto;
  }

  @Override
  public Identifier getModelId() {
    return VirtueFlawModel.ID;
  }
}