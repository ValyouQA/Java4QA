package ru.stqa.pft.rest;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class IsIssueClosed extends TestBase {



  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("b31e382ca8445202e66b03aaf31508a3","");
  }

  @Test
  public void IssueIsClosed() throws IOException {
    skipIfNotFixed(520);
  }


  @Test
  public void IssueIsNotClosed() throws IOException {
    skipIfNotFixed(521);

  }
}