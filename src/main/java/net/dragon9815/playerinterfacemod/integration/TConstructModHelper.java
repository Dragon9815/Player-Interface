package net.dragon9815.playerinterfacemod.integration;

import net.dragon9815.playerinterfacemod.integration.tconstruct.TabTrash;
import net.dragon9815.playerinterfacemod.reference.ModIDs;

import java.lang.reflect.Method;
import java.util.List;

public class TConstructModHelper extends BaseModHelper {

    public Method updateTab = null;
    public Method addTabs = null;

    @Override
    protected String getModId() {
        return ModIDs.TCONSTRUCT;
    }

    @Override
    public boolean load() throws Exception {
        try {
            Class tabRegistry = Class.forName("tconstruct.client.tabs.TabRegistry");
            Class abstractTab = Class.forName("tconstruct.client.tabs.AbstractTab");
            Method registerTab = tabRegistry.getMethod("registerTab", abstractTab);
            updateTab = tabRegistry.getMethod("updateTabValues", int.class, int.class, Class.class);
            addTabs = tabRegistry.getMethod("addTabsToList", List.class);

            registerTab.invoke(null, new TabTrash());

            return true;
        }
        catch (ReflectiveOperationException ex) {
            return false;
        }
    }
}
