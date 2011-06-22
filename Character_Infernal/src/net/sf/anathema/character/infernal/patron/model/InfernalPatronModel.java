package net.sf.anathema.character.infernal.patron.model;

import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.infernal.patron.InfernalPatronTemplate;
import net.sf.anathema.character.infernal.patron.presenter.IInfernalPatronModel;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.lib.control.change.IChangeListener;

public class InfernalPatronModel extends AbstractAdditionalModelAdapter implements IInfernalPatronModel {

  private final ICharacterModelContext context;

  private static IFavorableDefaultTrait[] gatherYozis(ICharacterModelContext context) {
	int length = YoziType.values().length;
    IFavorableDefaultTrait[] yozis = new IFavorableDefaultTrait[length];
    for (int i = 0; i != length; i++)
    	yozis[i] = (IFavorableDefaultTrait) context.getTraitCollection().getTrait(YoziType.values()[i]);
    
    return yozis;
  }

  private final IFavorableDefaultTrait[] allYozis;
  private final InfernalPatronTemplate template;

  public InfernalPatronModel(InfernalPatronTemplate template, ICharacterModelContext context) {
    this.template = template;
    this.context = context;
    allYozis = gatherYozis(context);
  }

  public String getTemplateId() {
    return template.getId();
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Concept;
  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

	@Override
	public IFavorableDefaultTrait[] getAllYozis() {
		return allYozis;
	}

	@Override
	public String getFavoredYozi()
	{
		for (IFavorableDefaultTrait trait : allYozis)
			if (trait.getFavorization().isFavored())
				return trait.getType().getId();
		return null;
	}
	
	public void setFavoredYozi(String yozi)
	{
		IFavorableDefaultTrait trait = (IFavorableDefaultTrait) context.getTraitCollection().
			getTrait(YoziType.valueOf(yozi));
		trait.getFavorization().setFavored(true);
	}
	 
	 public ICharacterModelContext getContext()
	 {
		 return context;
	 }

	@Override
	public void addChangeListener(IChangeListener listener) {
		// TODO Auto-generated method stub
		
	}
}