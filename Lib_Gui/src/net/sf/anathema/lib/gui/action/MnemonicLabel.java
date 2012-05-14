/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.action;

public class MnemonicLabel {

  private final String plainText;
  private final Character mnemonicCharacter;

  public MnemonicLabel(final String plainText, final Character mnemonicCharacter) {
    this.plainText = plainText;
    this.mnemonicCharacter = mnemonicCharacter;
  }

  public String getPlainText() {
    return plainText;
  }

  public Character getMnemonicCharacter() {
    return mnemonicCharacter;
  }
}