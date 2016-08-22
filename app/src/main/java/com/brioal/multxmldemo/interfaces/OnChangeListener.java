package com.brioal.multxmldemo.interfaces;

import com.brioal.multxmldemo.entity.ThingsEntity;

/**
 * Created by Brioal on 2016/8/22.
 */

public interface OnChangeListener {
    void add(ThingsEntity entity, int position);

    void delete(ThingsEntity entity, int position);
}
