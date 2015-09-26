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

import blue.lapis.pore.Pore;
import blue.lapis.pore.converter.wrapper.WrapperConverter;
import blue.lapis.pore.util.PoreText;
import blue.lapis.pore.util.PoreWrapper;

import org.bukkit.BanEntry;
import org.spongepowered.api.service.ban.BanService;
import org.spongepowered.api.util.ban.Ban;
import org.spongepowered.api.util.ban.BanTypes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class PoreBanEntry extends PoreWrapper<Ban> implements BanEntry {

    public static PoreBanEntry of(Ban handle) {
        return WrapperConverter.of(PoreBanEntry.class, handle);
    }

    private String target;
    private String source;
    private String reason;
    private Date created;
    private Date expiration;

    private final BanService banService;

    protected PoreBanEntry(Ban handle, BanService banService) {
        super(handle);
        this.banService = banService;
        if (handle.getType() == BanTypes.IP) {
            this.target = ((Ban.Ip) handle).getAddress().getCanonicalHostName();
        } else {
            this.target = ((Ban.Profile) handle).getProfile().getName();
        }
        if (handle.getBanSource().isPresent()) {
            this.source = PoreText.convert(handle.getBanSource().get());
        } else {
            this.source = null;
        }
        this.reason = PoreText.convert(handle.getReason());
        this.created = Date.from(handle.getCreationDate());
        if (handle.getExpirationDate().isPresent()) {
            this.expiration = Date.from(handle.getExpirationDate().get());
        } else {
            this.expiration = null;
        }
    }

    @Override
    public String getTarget() {
        return this.target;
    }

    @Override
    public Date getCreated() {
        return this.created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @SuppressWarnings("deprecation")
    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public Date getExpiration() {
        return this.expiration;
    }

    @Override
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public String getReason() {
        return this.reason;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public void save() {
        Ban.Builder builder = Ban.builder();

        if (this.getHandle().getType() == BanTypes.IP) {
            if (Pore.getGame().getServer().getPlayer(this.target).isPresent()) {
                builder.profile(Pore.getGame().getServer().getPlayer(this.target).get().getProfile());
            }
        } else {
            try {
                builder.address(InetAddress.getByName(this.target));
            } catch (UnknownHostException e) {
                // if this happens someone somewhere will cry
            }
        }
        if (reason != null) {
            builder.reason(PoreText.convert(this.reason));
        }
        if (expiration != null) {
            builder.expirationDate(this.expiration.toInstant());
        }
        if (source != null) {
            if (Pore.getGame().getServer().getPlayer(this.source).isPresent()) {
                builder.source(Pore.getGame().getServer().getPlayer(this.source).get());
            }
        }
        this.banService.addBan(builder.build());
    }
}
