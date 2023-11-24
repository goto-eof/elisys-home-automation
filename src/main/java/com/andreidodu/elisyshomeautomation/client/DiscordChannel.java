package com.andreidodu.elisyshomeautomation.client;

import com.andreidodu.elisyshomeautomation.dto.response.DiscordMessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${feign.discord.name}", url = "${feign.discord.base.url}")
public interface DiscordChannel {
    @RequestMapping(value = "${feign.discord.message.post}", method = RequestMethod.POST)
    void sendMessage(@RequestBody DiscordMessageDTO discordMessageDTO);
}
