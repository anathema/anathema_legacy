package net.sf.anathema.character.infernal.reporting.rendering;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.PreferredHeight;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class YoziListEncoderFactory extends AbstractEncoderFactory {

  public YoziListEncoderFactory() {
    super(EncoderIds.YOZI_LIST);
    setPreferredHeight(new PreferredHeight() {
      @Override
      public float getValue(EncodingMetrics metrics, float width) {
        return new YoziListEncoder().getPreferredHeight();
      }
    });
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new YoziListEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isOfType(CharacterType.INFERNAL);
  }
}
