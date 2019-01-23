package a.cool.groupproject.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupBean implements Parcelable {

    private int id;
    private String groupName;
    private String groupAvatar;
    private boolean isSelected;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupAvatar() {
        return groupAvatar;
    }

    public void setGroupAvatar(String groupAvatar) {
        this.groupAvatar = groupAvatar;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GroupBean{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", groupAvatar='" + groupAvatar + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }


    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.groupName);
        dest.writeString(this.groupAvatar);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    public GroupBean() {}

    protected GroupBean(Parcel in) {
        this.id = in.readInt();
        this.groupName = in.readString();
        this.groupAvatar = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<GroupBean> CREATOR = new Creator<GroupBean>() {
        @Override
        public GroupBean createFromParcel(Parcel source) {return new GroupBean(source);}

        @Override
        public GroupBean[] newArray(int size) {return new GroupBean[size];}
    };
}

