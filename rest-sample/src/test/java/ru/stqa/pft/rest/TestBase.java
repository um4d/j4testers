package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TestBase {

    protected int createIssue(Issue issue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", issue.getSubject()))
                .bodyForm(new BasicNameValuePair("description", issue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    protected Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public void skipifNotFixed(int id) throws IOException {
        if (isIssueOpen(id)) {
            throw new SkipException("Ignored because of issue " + id);
        }
    }

    protected Issue getIssue(int id) throws IOException {
        String json = getExecutor().execute(Request.Get(
                String.format("http://bugify.stqa.ru/api/issues/%s.json", id)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issueInSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        return issueInSet.iterator().next();
    }

    public boolean isIssueOpen(int id) throws IOException {
        int state = getIssue(id).getState();
        return !(state == 3);
    }
}
