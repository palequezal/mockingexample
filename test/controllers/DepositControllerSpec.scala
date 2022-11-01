package controllers

import models.BankAccount
import org.scalatest.BeforeAndAfterEach
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.{JsValue, Json}
import play.api.test._
import play.api.test.Helpers._
import services.mocks.{MockAccountService, MockManageAccountService}

class DepositControllerSpec extends PlaySpec
  with GuiceOneAppPerTest
  with Injecting
  with MockAccountService
  with MockManageAccountService {

  val controller = new DepositController(stubControllerComponents(), mockAccountService, mockManageAccountService)

  val testAccountNumber = "1"
  val testAccount = BankAccount(testAccountNumber, 1)
  val testAmount = 1
  val validJson = Json.obj("amount" -> testAmount)
  val invalidJson = Json.obj()

  def testRequest(json: JsValue) = FakeRequest().withMethod("POST").withBody(json)

  "deposit" when {
    "the json is in the correct format" when {
      "the requested account exists" must {
        "return OK with the account details" in {
          val updatedAccount = testAccount.copy(bankBalance = testAccount.bankBalance + testAmount)

          mockGetAccount(testAccountNumber)(Some(testAccount))
          mockDeposit(testAccount, testAmount)(updatedAccount)

          val request = testRequest(validJson)
          val result = controller.deposit(testAccountNumber)(request)

          status(result) mustBe OK
          contentAsString(result) mustBe updatedAccount.bankBalance.toString
        }
      }
      "the requested account doesn't exist" must {
        "return NOT_FOUND" in {
          mockGetAccount(testAccountNumber)(None)
          val request = testRequest(validJson)

          val result = controller.deposit(testAccountNumber)(request)

          status(result) mustBe NOT_FOUND
        }
      }
    }
    "the json isn't in the correct format" must {
      "return BAD_REQUEST" in {
        val request = testRequest(invalidJson)

        val result = controller.deposit(testAccountNumber)(request)

        status(result) mustBe BAD_REQUEST
      }
    }
  }

}
