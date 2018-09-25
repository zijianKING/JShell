// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: songzhif
// UT Student #: 1004359026
// Author: Zhifei Song
//
// Student2:
// UTORID user_name: xuxinzhe
// UT Student #: 1004050661
// Author: Xinzheng Xu
//
// Student3:
// UTORID user_name: wangq150
// UT Student #: 1004193419
// Author: Qingtian Wang
//
// Student4:
// UTORID user_name: wangz442
// UT Student #: 1004154960
// Author: Zijian Wang
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package test;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import command.Curl;
import command.Mkdir;
import fileSystem.ControllableFile;
import fileSystem.ManagementOfContainerKernel;

class MkdirTest {

  class MockControllableFile extends ControllableFile {

  }

  class MockFileSystem extends ManagementOfContainerKernel {
    Map<String, String> map;

    public MockFileSystem() {
      map = new HashMap<String, String>();
    }

    public MockControllableFile createFileUnderWD(String fileName,
        String content) {
      map.put(fileName, content);
      // System.out.println(map.get("dog"));
      return null;
    }

    public boolean checkFileExist(String fileName) {
      return map.containsKey(fileName);
    }

    public String getFileContent(String fileName) {
      return map.get(fileName);
    }
  }

  // MockFileSystem mock;
  private ManagementOfContainerKernel mock;
  private Mkdir mkdirCommand = new Mkdir();
  // private MockFileSystem mock;

  @BeforeEach
  void setUp() throws Exception {
    mock = new ManagementOfContainerKernel();
  }

  @Test
  void testExecute1() {
    String inputURL = "a";
    mkdirCommand.execute(mock, new Object[] {inputURL});
    boolean expectation = true;
    boolean actual = mock.getWorkingDir().getFileNames(mock).contains("a");
    assertEquals(expectation, actual);
  }

  @Test
  void testExecute2() {
    String inputURL = "1";
    mkdirCommand.execute(mock, new Object[] {inputURL});
    boolean expectation = true;
    boolean actual = mock.getWorkingDir().getFileNames(mock).contains("1");
    assertEquals(expectation, actual);
  }

  @Test
  void testExecute3() {
    String inputURL = "a.txt";
    mkdirCommand.execute(mock, new Object[] {inputURL});
    boolean expectation = true;
    boolean actual = mock.getWorkingDir().getFileNames(mock).contains("a.txt");
    assertEquals(expectation, actual);
  }

  @Test
  void testExecute4() {
    mkdirCommand.execute(mock, new Object[] {"a.txt", "b", "c"});
    boolean expectation = true;
    boolean actual = mock.getWorkingDir().getFileNames(mock).contains("a.txt");
    assertEquals(expectation, actual);
  }

  @Test
  void testExecute5() {
    mkdirCommand.execute(mock, new Object[] {"a.txt", "b", "c"});
    boolean expectation = true;
    boolean actual = mock.getWorkingDir().getFileNames(mock).contains("b");
    assertEquals(expectation, actual);
  }

  @Test
  void testExecute6() {
    mkdirCommand.execute(mock, new Object[] {"a.txt", "b", "c"});
    boolean expectation = true;

    boolean actual = mock.getWorkingDir().getFileNames(mock).contains("a.txt")
        && mock.getWorkingDir().getFileNames(mock).contains("b")
        && mock.getWorkingDir().getFileNames(mock).contains("c");
    assertEquals(expectation, actual);
  }

  @Test
  void testCheckArgFormat1() {
    boolean expectation = true;
    boolean actual = mkdirCommand.checkArgFormat(new String[] {""});
    assertEquals(expectation, actual);
  }

  @Test
  void testCheckArgFormat2() {
    boolean expectation = false;
    boolean actual = mkdirCommand.checkArgFormat(new String[] {});
    assertEquals(expectation, actual);
  }

  @Test
  void testCheckArgFormat3() {
    boolean expectation = true;
    boolean actual = mkdirCommand.checkArgFormat(new String[] {"", ""});
    assertEquals(expectation, actual);
  }

  @Test
  void testCheckArgFormat4() {
    boolean expectation = true;
    boolean actual = mkdirCommand.checkArgFormat(new String[] {"", "", ""});
    assertEquals(expectation, actual);
  }

}
