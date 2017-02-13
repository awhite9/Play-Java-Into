package models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class LatinStuff
{
    private Long userId;
    private Long id;
    private String title;
    private String body;

    public Long getUserId()
    {
        return userId;
    }

    @JsonSetter("userId")
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    public Long getId()
    {
        return id;
    }
    @JsonSetter("id")
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getTitle()
    {
        return title;
    }
    @JsonSetter("title")
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getBody()
    {
        return body;
    }
    @JsonSetter("body")
    public void setBody(String body)
    {
        this.body=body;
    }
    @JsonAnySetter()
    public void extraValue(String value)
    {
        System.out.println("value: " + value);
    }
}

