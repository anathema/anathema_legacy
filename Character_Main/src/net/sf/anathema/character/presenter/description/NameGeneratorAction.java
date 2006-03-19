package net.sf.anathema.character.presenter.description;

import java.awt.Component;

import javax.swing.Icon;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.namegenerator.domain.INameGenerator;

public class NameGeneratorAction extends SmartAction {

  private final ISimpleTextualDescription nameDescription;
  private final INameGenerator generator;

  public NameGeneratorAction(Icon icon, String tooltipText, ISimpleTextualDescription name, INameGenerator generator) {
    super(icon);
    this.nameDescription = name;
    this.generator = generator;
    setToolTipText(tooltipText);
  }

  @Override
  protected void execute(Component parentComponent) {
    String[] names = generator.createNames(1);
    nameDescription.setText(names[0]);
  }
}