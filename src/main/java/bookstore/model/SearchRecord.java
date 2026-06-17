package bookstore.model;

import java.util.Date;

public class SearchRecord {

    private String keyword;
    private Date searchDate;

    public SearchRecord(String keyword, Date searchDate) {

        this.keyword = keyword;
        this.searchDate = searchDate;
    }

    public String getKeyword() {

        return keyword;
    }

    public Date getSearchDate() {

        return searchDate;
    }

    public void saveSearch() {

        System.out.println("Search Saved: " + keyword);
    }
}