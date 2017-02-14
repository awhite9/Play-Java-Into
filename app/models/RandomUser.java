package models;


import java.util.ArrayList;
import java.util.List;

public class RandomUser
{
    public List<Results> results = new ArrayList();
    private Info info;

    public List<Results> getResults()
    {
        return results;
    }

    public void setResults (List<Results> results)
    {
        this.results = results;
    }
    public Info getInfo()
    {
        return info;
    }
    public void setInfo(Info info)
    {
        this.info=info;
    }
}
