package services.mocks

import models.BankAccount
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import services.ManageAccountService
import org.mockito.ArgumentMatchers

trait MockManageAccountService extends MockitoSugar {

  val mockManageAccountService = mock[ManageAccountService]

  def mockDeposit(bankAccount: BankAccount, amount: Double)(response: BankAccount) =
    when(mockManageAccountService.deposit(
      ArgumentMatchers.eq(bankAccount),
      ArgumentMatchers.eq(amount)
    )).thenReturn(response)

}
