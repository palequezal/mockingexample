package services

import models.BankAccount
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AccountServiceSpec extends AnyWordSpec with Matchers {

  val service = new AccountService

  val testAccountNumber1 = "1"
  val testAccountNumber2 = "2"
  val testAccountNumber3 = "3"
  val testBalance1 = 1
  val testBalance2 = 2
  val testAccount1 = BankAccount(testAccountNumber1, testBalance1)
  val testAccount2 = BankAccount(testAccountNumber2, testBalance2)

  "the account service" when {
    s"called with the account number '$testAccountNumber1'" must {
      s"return account number $testAccountNumber1" in {
        val res = service.getAccount(testAccountNumber1)

        res mustBe Some(testAccount1)
      }
    }
    s"called with the account number '$testAccountNumber2'" must {
      s"return account number $testAccountNumber2" in {
        val res = service.getAccount(testAccountNumber2)

        res mustBe Some(testAccount2)
      }
    }
    s"called with the account number '$testAccountNumber3'" must {
      s"return None" in {
        val res = service.getAccount(testAccountNumber3)

        res mustBe None
      }
    }
  }

}
