/*
 * Copyright 2015 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.nimingban.client.ac.data;

import android.graphics.Color;
import android.os.Parcel;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import com.hippo.nimingban.client.data.ACSite;
import com.hippo.nimingban.client.data.Reply;
import com.hippo.nimingban.client.data.Site;
import com.hippo.text.Html;

public class ACReply extends Reply {

    public String id = "";
    public String img = "";
    public String ext = "";
    public String now = "";
    public String userid = "";
    public String name = "";
    public String email = "";
    public String title = "";
    public String content = "";
    public String sage = "";
    public String admin = "";

    private Site mSite;

    String mPostId;
    private long mTime;
    private CharSequence mUser;
    private CharSequence mContent;
    private String mThumbKey;
    private String mImageKey;
    private String mThumbUrl;
    private String mImageUrl;

    @Override
    public String toString() {
        return "id = " + id + ", img = " + img + ", ext = " + ext + ", now = " + now +
                ", userid = " + userid + ", name = " + name + ", email = " + email +
                ", title = " + title + ", content = " + content + ", admin = " + admin;
    }

    @Override
    public void generate(Site site) {
        mSite = site;

        mTime = ACPost.parseTime(now);

        if ("1".equals(admin)) {
            Spannable spannable = new SpannableString(userid);
            spannable.setSpan(new ForegroundColorSpan(Color.RED), 0, userid.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mUser = spannable;
        } else {
            mUser = ACItemUtils.handleUser(Html.fromHtml(userid), getNMBPostId(), getNMBId());
        }

        mContent = ACItemUtils.generateContent(content, sage, title, name);

        if (!TextUtils.isEmpty(img)) {
            String ext2 = ext;
            if (".jpe".equals(ext2)) {
                ext2 = ".jpeg";
            }
            String key = img + ext2;
            mThumbKey = "thumb/" + key;
            mImageKey = "image/" + key;
            ACSite acSite = ACSite.getInstance();
            mThumbUrl = acSite.getPictureUrl(mThumbKey);
            mImageUrl = acSite.getPictureUrl(mImageKey);
        }
    }

    @Override
    public Site getNMBSite() {
        return mSite;
    }

    @Override
    public String getNMBId() {
        return id;
    }

    @Override
    public String getNMBPostId() {
        return mPostId;
    }

    @Override
    public long getNMBTime() {
        return mTime;
    }

    @Override
    public CharSequence getNMBDisplayUsername() {
        return mUser;
    }

    @Override
    public CharSequence getNMBDisplayContent() {
        return mContent;
    }

    @Override
    public String getNMBThumbKey() {
        return mThumbKey;
    }

    @Override
    public String getNMBImageKey() {
        return mImageKey;
    }

    @Override
    public String getNMBThumbUrl() {
        return mThumbUrl;
    }

    @Override
    public String getNMBImageUrl() {
        return mImageUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.img);
        dest.writeString(this.ext);
        dest.writeString(this.now);
        dest.writeString(this.userid);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.admin);
        dest.writeInt(this.mSite.getId());
    }

    public ACReply() {
    }

    protected ACReply(Parcel in) {
        this.id = in.readString();
        this.img = in.readString();
        this.ext = in.readString();
        this.now = in.readString();
        this.userid = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.admin = in.readString();
        this.mSite = Site.fromId(in.readInt());
    }

    public static final Creator<ACReply> CREATOR = new Creator<ACReply>() {

        @Override
        public ACReply createFromParcel(Parcel source) {
            return new ACReply(source);
        }

        @Override
        public ACReply[] newArray(int size) {
            return new ACReply[size];
        }
    };
}
