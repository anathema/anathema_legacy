package net.sf.anathema.character.infernal.patron.model;

import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.infernal.patron.InfernalPatronTemplate;
import net.sf.anathema.character.infernal.patron.presenter.IInfernalPatronModel;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public class InfernalPatronModel extends AbstractAdditionalModelAdapter implements IInfernalPatronModel {

  private final ICharacterModelContext context;
  private final IFavorableDefaultTrait[] allYozis;
  private final InfernalPatronTemplate template;

  public InfernalPatronModel(InfernalPatronTemplate template, ICharacterModelContext context) {
    this.template = template;
    this.context = context;
    this.allYozis = gatherYozis();
  }

  private IFavorableDefaultTrait[] gatherYozis() {
    List<IFavorableDefaultTrait> yozis = new ArrayList<>();
    for (YoziType type : YoziType.values()) {
      yozis.add((IFavorableDefaultTrait) context.getTraitCollection().getTrait(type));
    }
    return yozis.toArray(new IFavorableDefaultTrait[yozis.size()]);
  }

  @Override
  public String getTemplateId() {
    return template.getId();
  }

  @Override
  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Concept;
  }

  @Override
  public IFavorableDefaultTrait[] getAllYozis() {
    return allYozis;
  }

  @Override
  public String getFavoredYozi() {
    for (IFavorableDefaultTrait trait : allYozis)
      if (trait.getFavorization().isFavored()) return trait.getType().getId();
    return null;
  }

  @Override
  public void setFavoredYozi(String yozi) {
    IFavorableDefaultTrait trait = (IFavorableDefaultTrait) context.getTraitCollection().
            getTrait(YoziType.valueOf(yozi));
    trait.getFavorization().setFavored(true);
  }

  @Override
  public ICharacterModelContext getContext() {
    return context;
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    // TODO Auto-generated method stub
  }
}