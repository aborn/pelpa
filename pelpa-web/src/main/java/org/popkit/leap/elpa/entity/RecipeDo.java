package org.popkit.leap.elpa.entity;

import org.apache.commons.lang3.StringUtils;
import org.popkit.leap.elpa.annotation.Record;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-05-14:07:14
 */
public class RecipeDo {
    private String pkgName;
    private String fetcher;
    private String repo;

    private int dls;

    @Record(key = "version-regexp")
    private String versionRegexp;   // version-regexp

    private String files;

    private String url;

    private long lastCommit;

    private String shortInfo;

    private String type;

    private List<DepsItem> depsItemList;

    public String getPkgName() {
        return pkgName;
    }

    public FetcherEnum getFetcherEnum() {
        return FetcherEnum.getFetcher(this.fetcher);
    }

    @Override
    public String toString() {
        return "RecipeDo{" +
                "pkgName='" + pkgName + '\'' +
                ", fetcher='" + fetcher + '\'' +
                ", repo='" + repo + '\'' +
                ", dls=" + dls +
                ", versionRegexp='" + versionRegexp + '\'' +
                ", files='" + files + '\'' +
                ", url='" + url + '\'' +
                ", lastCommit=" + lastCommit +
                ", shortInfo='" + shortInfo + '\'' +
                ", type='" + type + '\'' +
                ", depsItemList=" + depsItemList +
                '}';
    }

    public List<String> getFileList() {
        if (StringUtils.isNotBlank(this.files)) {
            if (this.files.contains("(") || this.files.contains(")")) {
                // TODO: 6/5/16  like geiser
                return Arrays.asList(this.files.replaceAll("\\(", "").replaceAll("\\)", "").split("\\s+"));
            } else {
                return Arrays.asList(this.files.split("\\s+"));
            }
        } else {
            return new ArrayList<String>();
        }
    }

    public void update(String key, String value) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Record record = field.getAnnotation(Record.class);
            if (record != null && StringUtils.isNotBlank(record.key())
                    && record.key().equals(key)) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, this, value);
            }

            if (field.getName().equals(key)) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, this, value);
            }
        }
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getFetcher() {
        return fetcher;
    }

    public void setFetcher(String fetcher) {
        this.fetcher = fetcher;
    }

    public String getRepo() {
        if (this.repo != null &&
                this.repo.startsWith("\"")
                && this.repo.endsWith("\"")) {
            return this.repo.substring(1, this.repo.length() - 1);
        }
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getVersionRegexp() {
        return versionRegexp;
    }

    public void setVersionRegexp(String versionRegexp) {
        this.versionRegexp = versionRegexp;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getLastCommit() {
        return lastCommit;
    }

    public void setLastCommit(long lastCommit) {
        this.lastCommit = lastCommit;
    }

    public int getDls() {
        return dls;
    }

    public void setDls(int dls) {
        this.dls = dls;
    }

    public List<DepsItem> getDepsItemList() {
        return depsItemList;
    }

    public void setDepsItemList(List<DepsItem> depsItemList) {
        this.depsItemList = depsItemList;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
