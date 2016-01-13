/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.view;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.android.internal.util.Preconditions.checkNotNull;

/**
 * A group of {@link KeyboardShortcutInfo}.
 */
public final class KeyboardShortcutGroup implements Parcelable {
    private final CharSequence mLabel;
    private final List<KeyboardShortcutInfo> mItems;

    /**
     * @param label The title to be used for this group, or null if there is none.
     * @param items The set of items to be included.
     */
    public KeyboardShortcutGroup(@Nullable CharSequence label,
            @NonNull List<KeyboardShortcutInfo> items) {
        mLabel = label;
        mItems = new ArrayList<>(checkNotNull(items));
    }

    /**
     * @param label The title to be used for this group, or null if there is none.
     */
    public KeyboardShortcutGroup(@Nullable CharSequence label) {
        this(label, Collections.<KeyboardShortcutInfo>emptyList());
    }

    private KeyboardShortcutGroup(Parcel source) {
        mItems = new ArrayList<>();
        mLabel = source.readCharSequence();
        source.readTypedList(mItems, KeyboardShortcutInfo.CREATOR);
    }

    /**
     * Returns the label to be used to describe this group.
     */
    public CharSequence getLabel() {
        return mLabel;
    }

    /**
     * Returns the list of items included in this group.
     */
    public List<KeyboardShortcutInfo> getItems() {
        return mItems;
    }

    /**
     * Adds an item to the existing list.
     *
     * @param item The item to be added.
     */
    public void addItem(KeyboardShortcutInfo item) {
        mItems.add(item);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeCharSequence(mLabel);
        dest.writeTypedList(mItems);
    }

    public static final Creator<KeyboardShortcutGroup> CREATOR =
            new Creator<KeyboardShortcutGroup>() {
        public KeyboardShortcutGroup createFromParcel(Parcel source) {
            return new KeyboardShortcutGroup(source);
        }
        public KeyboardShortcutGroup[] newArray(int size) {
            return new KeyboardShortcutGroup[size];
        }
    };
}