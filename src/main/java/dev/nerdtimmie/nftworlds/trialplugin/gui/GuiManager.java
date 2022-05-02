package dev.nerdtimmie.nftworlds.trialplugin.gui;

import ninja.amp.ampmenus.menus.ItemMenu;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * Just a place to keep all possible future GUI's on an id.
 */
public class GuiManager {

    private final HashMap<Integer, ItemMenu> itemMenuHashMap;

    /**
     * Create the gui manager.
     */
    public GuiManager() {
        this.itemMenuHashMap = new HashMap<>();
    }

    /**
     * Add a GUI to the manager
     * @param id The id the GUI should correspond
     * @param itemMenu The GUI you want to add
     * @param overwrite Should it overwrite existing GUI's
     */
    public void addGui(Integer id, ItemMenu itemMenu, Boolean overwrite) {

        //Checks if the id is free. If not skip because we chose to not overwrite any existing GUI's
        if (!overwrite && itemMenuHashMap.containsKey(id)) {
            return;
        }

        itemMenuHashMap.put(id, itemMenu);
    }

    /**
     * Add a GUI to the manager. The methode by default does not overwrite existing GUI's
     * @param id The id the GUI should correspond
     * @param itemMenu The GUI you want to add
     */
    public void addGui(Integer id, ItemMenu itemMenu) {
        addGui(id, itemMenu, false);
    }

    /**
     * Removes a GUI from the manager.
     * @param id The id of the GUI that should be removed.
     */
    public void removeGui(Integer id){
        itemMenuHashMap.remove(id);
    }

    /**
     * Get a GUI based on its ID.
     * @param id The ID of the requested GUI.
     * @return The requested GUI. Can be null if ID is invalid.
     */
    public @Nullable ItemMenu getGui(Integer id){
        return itemMenuHashMap.get(id);
    }
}
