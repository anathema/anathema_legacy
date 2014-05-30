package net.sf.anathema.hero.equipment.display.view.personalization;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentPersonalizationProperties;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentPersonalizationView;
import net.sf.anathema.hero.equipment.display.presenter.ProxyClosure;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Closure;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class FxDialogPersonalizationView implements EquipmentPersonalizationView {
  private final MigPane content = new MigPane(fillWithoutInsets().wrapAfter(2));
  private final ITextView title;
  private final ITextView description;
  private ProxyClosure<String> onTitleChange = new ProxyClosure<>();
  private ProxyClosure<String> onDescriptionChange = new ProxyClosure<>();

  public FxDialogPersonalizationView(EquipmentPersonalizationProperties properties) {
    this.title = addField(properties.getTitleMessage(), onTitleChange::execute);
    this.description = addField(properties.getDescriptionMessage(), onDescriptionChange::execute);
  }

  public Node getNode() {
    return content;
  }

  public void setTitle(String title) {
    this.title.setText(title);
  }

  public void setDescription(String description) {
    this.description.setText(description);
  }

  public void whenTitleChanges(Closure<String> closure) {
    onTitleChange.setDelegate(closure);
  }

  public void whenDescriptionChanges(Closure<String> closure) {
    onDescriptionChange.setDelegate(closure);
  }

  private ITextView addField(String label, ObjectValueListener<String> listener) {
    ITextView textView = addEntry(label);
    textView.addTextChangedListener(listener);
    return textView;
  }

  @Override
  public ITextView addEntry(String label) {
    FxTextView textView = FxTextView.SingleLine(label);
    content.add(textView.getNode(), new CC().growX());
    return textView;
  }
}