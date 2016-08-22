package com.brioal.multxmldemo.entity;

/**Main RecyclerView Item Entity
 * Created by Brioal on 2016/8/22.
 */

public class ThingsEntity {
    private String mLabel;
    private String mTitle;

    public ThingsEntity(String label, String title) {
        mLabel = label;
        mTitle = title;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
