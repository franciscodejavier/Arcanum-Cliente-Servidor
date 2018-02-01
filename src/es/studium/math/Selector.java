/*
 * Decompiled with CFR 0_123.
 */
package es.studium.math;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class Selector
extends FocusAdapter {
    @Override
    public void focusLost(FocusEvent evt) {
    }

    @Override
    public void focusGained(FocusEvent evt) {
        Object o = evt.getSource();
        if (o instanceof JTextField) {
            JTextField txt = (JTextField)o;
            txt.setSelectionStart(0);
            txt.setSelectionEnd(txt.getText().length());
        }
    }
}

