/*
 * Pore
 * Copyright (c) 2014-2015, Lapis <https://github.com/LapisBlue>
 *
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package blue.lapis.pore.impl;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.spongepowered.api.service.ban.BanService;
import org.spongepowered.api.util.ban.Ban;

import java.util.Date;
import java.util.Set;

public class PoreBanList implements BanList {

    private final BanService banService;
    private final Type type;

    protected PoreBanList(BanService banService, Type type) {
        this.banService = banService;
        this.type = type;
    }

    @Override
    public BanEntry getBanEntry(String target) {
        throw new NotImplementedException("TODO");
    }

    @Override
    public BanEntry addBan(String target, String reason, Date expires, String source) {
        throw new NotImplementedException("TODO");
    }

    @Override
    public Set<BanEntry> getBanEntries() {
        Set<BanEntry> bans = Sets.newHashSet();

        switch (type) {
            case NAME:
                for (Ban.User ban : banService.getUserBans()) {
                    bans.add(PoreBanEntry.of(ban));
                }
                break;
            case IP:
                for (Ban.Ip ban : banService.getIpBans()) {
                    bans.add(PoreBanEntry.of(ban));
                }
                break;
        }

        return bans;
    }

    @Override
    public boolean isBanned(String target) {
        throw new NotImplementedException("TODO");
    }

    @Override
    public void pardon(String target) {
        throw new NotImplementedException("TODO");
    }
}
