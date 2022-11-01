package services.mocks

import models.BankAccount
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import services.AccountService
import org.mockito.ArgumentMatchers

trait MockAccountService extends MockitoSugar {

  val mockAccountService = mock[AccountService]

  def mockGetAccount(accountNumber: String)(response: Option[BankAccount]) =
    when(mockAccountService.getAccount(
      ArgumentMatchers.eq(accountNumber)
    )).thenReturn(response)

}
