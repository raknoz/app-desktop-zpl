/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_zpl.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author davidgomez
 */
public class ItemChangeListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            final Object item = event.getItem();
            // do something with object
        }
    }
}
