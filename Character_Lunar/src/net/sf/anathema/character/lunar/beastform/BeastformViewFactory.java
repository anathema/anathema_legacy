package net.sf.anathema.character.lunar.beastform;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.lunar.beastform.model.FirstEditionBeastformModel;
import net.sf.anathema.character.lunar.beastform.model.SecondEditionBeastformModel;
import net.sf.anathema.character.lunar.beastform.presenter.FirstEditionBeastformPresenter;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformView;
import net.sf.anathema.character.lunar.beastform.presenter.SecondEditionBeastformPresenter;
import net.sf.anathema.character.lunar.beastform.view.FirstEditionBeastformView;
import net.sf.anathema.character.lunar.beastform.view.IBeastformViewProperties;
import net.sf.anathema.character.lunar.beastform.view.SecondEditionBeastformView;
import net.sf.anathema.character.mutations.view.IMutationsView;
import net.sf.anathema.character.mutations.view.IMutationsViewProperties;
import net.sf.anathema.character.mutations.view.MutationsView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class BeastformViewFactory implements IAdditionalViewFactory {

  public IView createView(final IAdditionalModel model, final IResources resources, ICharacterType type) {
    MarkerIntValueDisplayFactory intValueDisplayFactory = new MarkerIntValueDisplayFactory(resources, type);
    IBeastformViewProperties properties;
    
    IBeastformView view = null;
    if (model instanceof FirstEditionBeastformModel)
    {
    	properties = new IBeastformViewProperties() {
        	public String getCharmString() {
                return resources.getString("Lunar.DeadlyBeastmanTransformation"); //$NON-NLS-1$
              }

              public String getAttributesString() {
                return resources.getString("Lunar.DeadlyBeastmanTransformation.Attributes.Label"); //$NON-NLS-1$
              }

              public String getGiftsString() {
                return resources.getString("Lunar.DeadlyBeastmanTransformation.Gifts.Label"); //$NON-NLS-1$
              }

				@Override
				public String getDBTBoxString() {
					return null;
				}
	
				@Override
				public String getSpiritFormBoxString() {
					return null;
				}

				@Override
				public String getSpiritFormBoxInitialString() {
					return null;
				}
            };
    	view = new FirstEditionBeastformView(intValueDisplayFactory, properties); 
        new FirstEditionBeastformPresenter(resources, view, (IBeastformModel) model).initPresentation();	
    }
    if (model instanceof SecondEditionBeastformModel)
    {
    	final SecondEditionBeastformModel secondmodel = (SecondEditionBeastformModel)model;
    	properties = new IBeastformViewProperties() {
        	public String getCharmString() {
                return resources.getString("Lunar.DeadlyBeastmanTransformation"); //$NON-NLS-1$
              }

              public String getAttributesString() {
                return resources.getString("Lunar.DeadlyBeastmanTransformation.Attributes.Label"); //$NON-NLS-1$
              }

              public String getGiftsString() {
                return resources.getString("Lunar.DeadlyBeastmanTransformation.Gifts.Label_2nd"); //$NON-NLS-1$
              }

			@Override
			public String getDBTBoxString() {
				return resources.getString("Lunar.DeadlyBeastmanTransformation");
			}

			@Override
			public String getSpiritFormBoxString() {
				return resources.getString("Lunar.SpiritForm");
			}

			@Override
			public String getSpiritFormBoxInitialString() {
				String shape = secondmodel.getSpiritForm();
				return shape.equals("") ? resources.getString("Lunar.SpiritForm.EnterShape") : shape;
			}
            };
    	view = new SecondEditionBeastformView(intValueDisplayFactory, properties);
    	IMutationsView mutationView = new MutationsView(new IMutationsViewProperties()
    	{
			@Override
			public String getMutationsString() {
				return resources.getString("Mutations.Label");
			}
    		
    	});
        new SecondEditionBeastformPresenter(resources, view,
        		(IBeastformModel) model,
        		secondmodel.getMutationModel(),
        		mutationView).initPresentation();
    }
    
    return view;
  }
}