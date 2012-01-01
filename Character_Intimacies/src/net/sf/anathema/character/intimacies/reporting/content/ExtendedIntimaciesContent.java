package net.sf.anathema.character.intimacies.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class ExtendedIntimaciesContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public ExtendedIntimaciesContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Intimacies"; //$NON-NLS-1$
  }

  public int getTraitMaxValue() {
    return character.getTraitCollection().getTrait(VirtueType.Conviction).getCurrentValue();
  }

  public List<NamedValue> getPrintIntimacies() {
    List<NamedValue> printIntimacies = new ArrayList<NamedValue>();
    for (final IIntimacy intimacy : getModel().getEntries()) {
      if (!isPrintZeroIntimacies() && intimacy.getTrait().getCurrentValue() == 0) {
        continue;
      }
      printIntimacies.add(new PrintIntimacy(intimacy));
    }
    return printIntimacies;
  }

  private IIntimaciesModel getModel() {
    IIntimaciesAdditionalModel additionalModel = (IIntimaciesAdditionalModel) character.getAdditionalModel(IntimaciesTemplate.ID);
    return additionalModel.getIntimaciesModel();
  }

  private boolean isPrintZeroIntimacies() {
    return AnathemaCharacterPreferences.getDefaultPreferences().printZeroIntimacies();
  }
}
