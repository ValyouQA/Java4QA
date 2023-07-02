package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("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");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("ValyouQA", "Java4QA")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build()))
      System.out.println(new RepoCommit.Smart(commit).message());
  }
}