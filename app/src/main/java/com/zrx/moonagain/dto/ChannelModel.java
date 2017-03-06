package com.zrx.moonagain.dto;

/**
 * Created by Schnee on 2017/2/16.
 */

public class ChannelModel {

    private int id;

    private String typename;

    private String description;

    private int sortrank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSortrank() {
        return sortrank;
    }

    public void setSortrank(int sortrank) {
        this.sortrank = sortrank;
    }
}
