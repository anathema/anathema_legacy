package net.sf.anathema.character.lunar.reporting.rendering.health;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.SecondEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.SecondEditionHealthAndMovementTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionLunarHealthAndMovementEncoder extends SecondEditionHealthAndMovementEncoder {

  public SecondEditionLunarHealthAndMovementEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont);
  }

  @Override
  protected final ITableEncoder createTableEncoder() {
    return new SecondEditionHealthAndMovementTableEncoder(getResources(), getBaseFont()) {
      protected IGenericTraitCollection getTraits(IGenericCharacter character) {
        return ((IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID)).getBeastTraitCollection();
      }
    };
  }

}
