package eu.timepit.refined

import eu.timepit.refined.TestUtils._
import eu.timepit.refined.numeric._
import org.scalacheck.Prop._
import org.scalacheck.Properties
import shapeless.nat._

class NumericValidateSpec extends Properties("NumericValidate") {

  property("Less.isValid") = forAll { (d: Double) =>
    isValid[Less[1.0]](d) ?= (d < 1.0)
  }

  property("Less.showExpr") = secure {
    showExpr[Less[1.1]](0.1) ?= "(0.1 < 1.1)"
  }

  property("Less.Nat.isValid") = forAll { (i: Int) =>
    isValid[Less[_5]](i) ?= (i < 5)
  }

  property("Less.Nat.showExpr") = secure {
    showExpr[Less[_5]](0) ?= "(0 < 5)"
  }

  property("LessEqual.Nat.isValid") = forAll { (i: Int) =>
    isValid[LessEqual[_5]](i) ?= (i <= 5)
  }

  property("LessEqual.Nat.showExpr") = secure {
    showExpr[LessEqual[_5]](0) ?= "!(0 > 5)"
  }

  property("Less.Nat ~= Less.Int") = forAll { (i: Int) =>
    showResult[Less[_5]](i) ?= showResult[Less[5]](i)
  }

  property("Greater.isValid") = forAll { (d: Double) =>
    isValid[Greater[1.0]](d) ?= (d > 1.0)
  }

  property("Greater.showExpr") = secure {
    showExpr[Greater[1.1]](0.1) ?= "(0.1 > 1.1)"
  }

  property("Greater.Nat.isValid") = forAll { (i: Int) =>
    isValid[Greater[_5]](i) ?= (i > 5)
  }

  property("Greater.Nat.showExpr") = secure {
    showExpr[Greater[_5]](0) ?= "(0 > 5)"
  }

  property("GreaterEqual.Nat.isValid") = forAll { (i: Int) =>
    isValid[GreaterEqual[_5]](i) ?= (i >= 5)
  }

  property("GreaterEqual.Nat.showExpr") = secure {
    showExpr[GreaterEqual[_5]](0) ?= "!(0 < 5)"
  }

  property("Greater.Nat ~= Greater.Int") = forAll { (i: Int) =>
    showResult[Greater[_5]](i) ?= showResult[Greater[5]](i)
  }

  property("Modulo.isValid - Nat - Byte") = forAll { (b: Byte) =>
    isValid[Modulo[_2, _0]](b) ?= (b % 2 == 0)
  }

  property("Modulo.isValid - Nat - Short") = forAll { (s: Short) =>
    isValid[Modulo[_2, _0]](s) ?= (s % 2 == 0)
  }

  property("Modulo.isValid - Nat - Int") = forAll { (i: Int) =>
    isValid[Modulo[_2, _0]](i) ?= (i % 2 == 0)
  }

  property("Modulo.isValid - Wit - Int") = forAll { (i: Int) =>
    isValid[Modulo[2, 0]](i) ?= (i % 2 == 0)
  }

  property("Modulo.isValid - Nat - Long") = forAll { (l: Long) =>
    isValid[Modulo[_2, _0]](l) ?= (l % 2 == 0)
  }

  property("Modulo.isValid - Wit - Long") = forAll { (l: Long) =>
    isValid[Modulo[2L, 0L]](l) ?= (l % 2 == 0)
  }

  property("Modulo.showExpr") = secure {
    showExpr[Modulo[2, 0]](4) ?= s"(${4} % ${2} == ${0})"
  }

  property("Modulo.Nat.isValid") = forAll { (i: Int) =>
    isValid[Modulo[_2, _0]](i) ?= (i % 2 == 0)
  }

  property("Modulo.Nat.showExpr") = secure {
    showExpr[Modulo[_2, _0]](4) ?= "(4 % 2 == 0)"
  }

  property("Modulo.Nat ~= Modulo.Int") = forAll { (i: Int) =>
    showResult[Modulo[_5, _2]](i) ?= showResult[Modulo[5, 2]](i)
  }

  property("Divisible.Nat.isValid") = forAll { (i: Int) =>
    isValid[Divisible[_2]](i) ?= (i % 2 == 0)
  }

  property("Divisible.Int.isValid") = forAll { (i: Int) =>
    isValid[Divisible[2]](i) ?= (i % 2 == 0)
  }

  property("Divisible.Nat.showExpr") = secure {
    showExpr[Divisible[_2]](4) ?= "(4 % 2 == 0)"
  }

  property("Divisible.Int.showExpr") = secure {
    showExpr[Divisible[2]](4) ?= "(4 % 2 == 0)"
  }

  property("NonDivisible.isValid") = forAll { (i: Int) =>
    isValid[NonDivisible[_2]](i) ?= (i % 2 != 0)
  }

  property("NonDivisible.showExpr") = secure {
    showExpr[NonDivisible[_2]](4) ?= "!(4 % 2 == 0)"
  }

  property("Even.isValid") = forAll { (i: Int) =>
    isValid[Even](i) ?= (i % 2 == 0)
  }

  property("Even.showExpr") = secure {
    showExpr[Even](4) ?= "(4 % 2 == 0)"
  }

  property("Odd.isValid") = forAll { (i: Int) =>
    isValid[Odd](i) ?= (i % 2 != 0)
  }

  property("Odd.showExpr") = secure {
    showExpr[Odd](4) ?= "!(4 % 2 == 0)"
  }

  property("Interval.Open.isValid") = forAll { (d: Double) =>
    isValid[Interval.Open[_0, _1]](d) ?= (d > 0.0 && d < 1.0)
  }

  property("Interval.Open.showExpr") = secure {
    val s = showExpr[Interval.Open[_0, _1]](0.5)
    (s ?= "((0.5 > 0) && (0.5 < 1))") || (s ?= "((0.5 > 0.0) && (0.5 < 1.0))")
  }

  property("Interval.OpenClosed.isValid") = forAll { (d: Double) =>
    isValid[Interval.OpenClosed[_0, _1]](d) ?= (d > 0.0 && d <= 1.0)
  }

  property("Interval.OpenClosed.showExpr") = secure {
    val s = showExpr[Interval.OpenClosed[_0, _1]](0.5)
    (s ?= "((0.5 > 0) && !(0.5 > 1))") || (s ?= "((0.5 > 0.0) && !(0.5 > 1.0))")
  }

  property("Interval.ClosedOpen.isValid") = forAll { (d: Double) =>
    isValid[Interval.ClosedOpen[_0, _1]](d) ?= (d >= 0.0 && d < 1.0)
  }

  property("Interval.ClosedOpen.showExpr") = secure {
    val s = showExpr[Interval.ClosedOpen[_0, _1]](0.5)
    (s ?= "(!(0.5 < 0) && (0.5 < 1))") || (s ?= "(!(0.5 < 0.0) && (0.5 < 1.0))")
  }

  property("Interval.Closed.isValid") = forAll { (d: Double) =>
    isValid[Interval.Closed[_0, _1]](d) ?= (d >= 0.0 && d <= 1.0)
  }

  property("Interval.Closed.showExpr") = secure {
    val s = showExpr[Interval.Closed[_0, _1]](0.5)
    (s ?= "(!(0.5 < 0) && !(0.5 > 1))") || (s ?= "(!(0.5 < 0.0) && !(0.5 > 1.0))")
  }
}
