package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.*;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(0000001);
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project pr : projects) {
            System.out.println(pr.getName());
        }
    }
    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(0000002);
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("test issue").withDescription("test issue description")
                .witjProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
