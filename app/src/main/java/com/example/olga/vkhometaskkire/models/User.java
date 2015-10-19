package com.example.olga.vkhometaskkire.models;

/**
 * Created by Olga on 13.10.2015.
 */
public class User {
    private int id, sex;
    public static final int SEX_MAN = 0;
    public static final int SEX_WOMAN = 1;
    private String firstName, lastName, nickname, city, country, education, status, universities;
    private String[] photos;
    private boolean online, hasMobile, canWritePrivateMessage, canSeeAllPosts, canPost;
    private long birthday, lastSeen;
    private int[] friends;
    private int[] groups;
    private int[] audio;
    private int[] videos;


    public User(int id, int sex, String firstName, String lastName, String nickname,
                String city, String country, String[] photo, String education,
                String status, String universities, boolean online, boolean hasMobile,
                boolean canWritePrivateMessage, boolean canSeeAllPosts, boolean canPost,
                long birthday, long lastSeen, int[] relation, int[] videos, int[] audio, int[] groups) {
        this.id = id;
        this.sex = sex;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.city = city;
        this.country = country;
        this.photos = photo;
        this.education = education;
        this.status = status;
        this.universities = universities;
        this.online = online;
        this.hasMobile = hasMobile;
        this.canWritePrivateMessage = canWritePrivateMessage;
        this.canSeeAllPosts = canSeeAllPosts;
        this.canPost = canPost;
        this.birthday = birthday;
        this.lastSeen = lastSeen;
        this.friends = relation;
        this.videos = videos;
        this.audio = audio;
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUniversities() {
        return universities;
    }

    public void setUniversities(String universities) {
        this.universities = universities;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isHasMobile() {
        return hasMobile;
    }

    public void setHasMobile(boolean hasMobile) {
        this.hasMobile = hasMobile;
    }

    public boolean isCanWritePrivateMessage() {
        return canWritePrivateMessage;
    }

    public void setCanWritePrivateMessage(boolean canWritePrivateMessage) {
        this.canWritePrivateMessage = canWritePrivateMessage;
    }

    public boolean isCanSeeAllPosts() {
        return canSeeAllPosts;
    }

    public void setCanSeeAllPosts(boolean canSeeAllPosts) {
        this.canSeeAllPosts = canSeeAllPosts;
    }

    public boolean isCanPost() {
        return canPost;
    }

    public void setCanPost(boolean canPost) {
        this.canPost = canPost;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public int[] getFriends() {
        return friends;
    }

    public void setFriends(int[] friends) {
        this.friends = friends;
    }

    public String getName() {
        return lastName + " " + firstName;
    }

    public int[] getVideos() {
        return videos;
    }

    public void setVideos(int[] videos) {
        this.videos = videos;
    }

    public int[] getAudio() {
        return audio;
    }

    public void setAudio(int[] audio) {
        this.audio = audio;
    }


    public int[] getGroups() {
        return groups;
    }

    public void setGroups(int[] groups) {
        this.groups = groups;
    }


    public boolean isFriend(int[] friendsId) {
        for (int i : friendsId) {
            if (id == i) return true;
        }
        return false;
    }

    public boolean isMember(int[] membersId) {
        for (int i : membersId) {
            if (id == i) return true;
        }
        return false;
    }

    public boolean isMyFriend() {
        for (int i : friends) {
            if (i==1) return true;
        }
        return false;
    }
}
