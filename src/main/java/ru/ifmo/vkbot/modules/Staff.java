package ru.ifmo.vkbot.modules;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.ifmo.vkbot.VkBot;
import ru.ifmo.vkbot.structures.Message;
import ru.ifmo.vkbot.utils.PostExecutor;

/**
 *
 * @author RinesThaix
 */
public class Staff extends BotModule {
    
    public final static List<String> staff = new ArrayList();
    
    public static void loadStaff(List<Long> ids) {
        for(long uid : ids) {
            JSONArray answer = VkBot.parse(PostExecutor.buildAndGet("users.get", "uid", uid, "lang", "ru"));
            JSONObject info = (JSONObject) answer.get(0);
            String fullName = (String) info.get("first_name") + " " + (String) info.get("last_name");
            staff.add(fullName + " (https://vk.com/id" + uid + ")");
        }
    }

    public Staff(VkBot vkbot) {
        super(vkbot);
    }

    @Override
    public void handle(Message m, String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Список моих модераторов:\n");
        for(String s : staff)
            sb.append("- ").append(s).append("\n");
        getMC().sendAttached(m.getDialog(), sb.toString(), m.getMessageId());
    }

}