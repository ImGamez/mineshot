/*
** 2014 January 21
**
** The author disclaims copyright to this source code.  In place of
** a legal notice, here is a blessing:
**    May you do good and not evil.
**    May you find forgiveness for yourself and forgive others.
**    May you share freely, never taking more than you give.
*/
package info.ata4.minecraft.mineshot.client.util;

import java.io.File;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

/**
 * Simple and easy chat log utility class.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class ChatUtils {
    
    private static final Minecraft MC = Minecraft.getMinecraft();
    
    private ChatUtils() {
    }
    
    public static void print(String msg, TextFormatting format, Object... args) {
        if (MC.ingameGUI == null) {
            return;
        }
        
        GuiNewChat chat = MC.ingameGUI.getChatGUI();
        TextComponentTranslation ret = new TextComponentTranslation(msg, args);
        ret.getStyle().setColor(format);
        
        chat.printChatMessage(ret);
    }
    
    public static void print(String msg, Object... args) {
        print(msg, null, args);
    }
    
    public static void printFileLink(String msg, File file) {
        TextComponentString text = new TextComponentString(file.getName());
        String path;
        
        try {
            path = file.getAbsoluteFile().getCanonicalPath();
        } catch (IOException ex) {
            path = file.getAbsolutePath();
        }
        
        text.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, path));
        text.getStyle().setUnderlined(true);
        
        print(msg, text);
    }
}
