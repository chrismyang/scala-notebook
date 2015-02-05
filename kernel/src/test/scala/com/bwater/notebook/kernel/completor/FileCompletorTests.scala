package com.bwater.notebook.kernel.completor

import com.bwater.notebook.Match
import org.scalatest.{Matchers, FunSuite}

class FileCompletorTests extends FunSuite with Matchers {
  test("foo") {
    val completor = makeCompletor(
      "adirectory/",
      "abc",
      "aaa"
    )

    assert(completor.complete("a") === ("a", Seq(Match("adirectory/"), Match("abc"), Match("aaa"))))
    assert(completor.complete("ab") === ("ab", Seq(Match("abc"))))
  }

  test("bar") {
    val completor = makeCompletor(
      "directory/abc",
      "directory/aaa"
    )

    assert(completor.complete("dir") === ("dir", Seq(Match("directory/"))))
    assert(completor.complete("directory/a") === ("a", Seq(Match("abc"), Match("aaa"))))
  }

  private def makeCompletor(fileState: String*): FileCompletor = new FileCompletor {
    protected override def listFiles = fileState
  }
}
