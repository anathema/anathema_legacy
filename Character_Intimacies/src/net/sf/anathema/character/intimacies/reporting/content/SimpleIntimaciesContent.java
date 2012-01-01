package net.sf.anathema.character.intimacies.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.intimacies.IIntimaciesAdditionalModel;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.presenter.IIntimaciesModel;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ListSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleIntimaciesContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private IGenericCharacter character;

  public SimpleIntimaciesContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Intimacies"; //$NON-NLS-1$
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printIntimacies = new ArrayList<String>();
    for (Iterator<IIntimacy> intimacies = getModel().getEntries().iterator(); intimacies.hasNext(); ) {
      IIntimacy intimacy = intimacies.next();
      String text = intimacy.getName();
      if (!intimacy.isComplete()) {
        text +=
          " (" + intimacy.getTrait().getCurrentValue() + "/" + intimacy.getTrait().getMaximalValue() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
      printIntimacies.add(text);
    }
    return printIntimacies;
  }

  private IIntimaciesModel getModel() {
    IIntimaciesAdditionalModel additionalModel = (IIntimaciesAdditionalModel) character.getAdditionalModel(IntimaciesTemplate.ID);
    return additionalModel.getIntimaciesModel();
  }
}
