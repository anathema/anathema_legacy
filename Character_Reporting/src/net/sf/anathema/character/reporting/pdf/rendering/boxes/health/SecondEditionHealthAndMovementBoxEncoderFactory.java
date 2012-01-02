package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class SecondEditionHealthAndMovementBoxEncoderFactory extends Identificate implements BoxContentEncoderFactory {

  public static final String ID = SecondEditionHealthAndMovementBoxEncoderFactory.class.getName();

  public SecondEditionHealthAndMovementBoxEncoderFactory() {
    super(ID);
  }

  public IBoxContentEncoder create(IResources resources) {
    return new HealthAndMovement2ndEditionEncoder(resources);
  }
}
